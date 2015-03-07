package com.salarycap.dal;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.skife.jdbi.v2.DBI;

import com.salarycap.dao.ContractOverviewDao;
import com.salarycap.dao.DeadMoneyDao;
import com.salarycap.dao.PlayerDao;
import com.salarycap.dao.TeamDao;
import com.salarycap.dao.YearlyContractDao;
import com.salarycap.models.ContractOverview;
import com.salarycap.models.DeadMoney;
import com.salarycap.models.Player;
import com.salarycap.models.Team;
import com.salarycap.models.YearlyContract;
import com.salarycap.service.ContractOverviewService;
import com.salarycap.utilities.RosterUtilities;

public class DataSourceLayer {
	private DBI jdbi;
	private PlayerDao playerDao;
	private ContractOverviewDao contractOverviewDao;
	private YearlyContractDao yearlyContractDao;
	private DeadMoneyDao deadMoneyDao;
	private TeamDao teamDao;
	private SalaryCapDal salaryCapDal;
	private ContractOverviewService contractOverviewService;
	private static final Logger logger = Logger.getLogger(DataSourceLayer.class);
	
	public DataSourceLayer(SalaryCapDal salaryCapDal, ContractOverviewService contractOverviewService){
		this.salaryCapDal = salaryCapDal;
		this.jdbi = getJdbiInstance();
		playerDao = jdbi.onDemand(PlayerDao.class);
		contractOverviewDao = jdbi.onDemand(ContractOverviewDao.class);
		yearlyContractDao = jdbi.onDemand(YearlyContractDao.class);
		deadMoneyDao = jdbi.onDemand(DeadMoneyDao.class);
		teamDao = jdbi.onDemand(TeamDao.class);
		this.contractOverviewService = contractOverviewService;
	}
	
	public void addPlayersToDb(){	
		for (Player p : salaryCapDal.getAllPlayers()) {
			try{
				playerDao.insert(p.getId(), p.getAccrued(), p.getDateOfBirth().toString(), p.getName(), p.getNotes(), p.getCollege(),
					p.getDraftPick(), p.getDraftRound(), p.getDraftYear(), p.getHeight(), p.getOriginalTeamId(),
					p.getWeight());
			}
			catch(Exception e){
				logger.error("Unable to insert " + p.toString() +"(" + e.toString() +")");
			}				
		}
	}
	
	public void addTeamsToDb(){
		for (Team t : salaryCapDal.getAllTeams()) {
			try{
				teamDao.insert(t.getId(), t.getName());
			}
			catch(Exception e){
				logger.error("Unable to insert " + t.toString() +"(" + e.toString() +")");
			}
		}
	}
	
	public void addDeadMoneysToDb() {
		for (DeadMoney d : salaryCapDal.getAllDeadMoneys()) {
			try{
				deadMoneyDao.insert(d.getId(), d.getDeadMoney(), d.getYear(), d.getPlayer(), d.getTeam());
			}
			catch(Exception e){
				logger.error("Unable to insert " + d.toString() +"(" + e.toString() +")");
			}			
		}
	}
	
	public void addContractOverviewsToDb() {
		for (ContractOverview c : salaryCapDal.getAllContractOverviews()) {
			try{
				contractOverviewDao.insert(c.getId(), c.getPlayer(), c.getTeam(), c.getAveragePerYear(), c.getFreeAgentYear(),
					c.getGuarantee(), c.getPosition(), c.getRole(), c.getStatus(), c.getTotal(), c.getYears());
			}
			catch(Exception e){
				logger.error("Unable to insert " + c.toString() +"(" + e.toString() +")");
			}				
		}
	}

	public void addYearlyContractsToDb() {
		for (YearlyContract c : salaryCapDal.getAllYearlyContracts()) {
			if(validContractYear(c)){
				try{
					yearlyContractDao.insert(c.getId(), c.getPlayer(), c.getTeam(), c.getYear(), c.getBaseSalary(), c.getCapCharge(),
						c.getCapSavings(), c.getDeadMoney(), c.getGuaranteedBaseSalary(), c.getNotes(), c.getOptionBonus(),
						c.getSigningBonus(), c.getRosterBonus(), c.getWorkoutBonus(), c.getTeamName(), c.getRole(),
						c.getStatus(), c.getPosition());
					}
					catch(Exception e){
						logger.error("Unable to insert " + c.toString() +"(" + e.toString() +")");
					}
			}
			else{
				logger.info(c.toString() +"---> Not added to yearly contract List. Reason: Contract expires prior to this year.");
			}
		}
	}	
	
	private static DBI getJdbiInstance(){
		DataSource ds = new DataSource();
		ds.setUrl("jdbc:mysql://127.0.0.1:3306/salarycap_dev");
		ds.setPassword("Raven$1996");
		ds.setUsername("lfallon");
		ds.setDriverClassName("com.mysql.jdbc.Driver");	
        DBI dbi = new DBI(ds);	
        return dbi;
	}

	public void clearDatabase() {
		deadMoneyDao.deleteAll();
		contractOverviewDao.deleteAll();
		yearlyContractDao.deleteAll();			
		playerDao.deleteAll();
		//teamDao.deleteAll();
	}
	
	private Boolean validContractYear(YearlyContract yearlyContract){
		Integer freeAgentYear = RosterUtilities.getFreeAgentYearAsInt(contractOverviewService.getByPlayer(yearlyContract.getPlayer()).getFreeAgentYear());
		try{
			if(yearlyContract.getYear() >= freeAgentYear){
				return false;
			}
			else{
				return true;
			}
		}
		catch(Exception e){
			return false;
		}		
	}

}
