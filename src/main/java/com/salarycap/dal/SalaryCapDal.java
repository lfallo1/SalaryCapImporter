package com.salarycap.dal;

import java.util.ArrayList;
import java.util.List;

import com.salarycap.models.ContractOverview;
import com.salarycap.models.DeadMoney;
import com.salarycap.models.Player;
import com.salarycap.models.Team;
import com.salarycap.models.YearlyContract;

public class SalaryCapDal {
	private List<Player> players = new ArrayList<>();
	private List<ContractOverview> contractOverviews = new ArrayList<>();
	private List<YearlyContract> yearlyContracts = new ArrayList<>();
	private List<Team> teams = new ArrayList<>();
	private List<DeadMoney> deadMoneys = new ArrayList<>();
	
	public SalaryCapDal(){}
	
	public List<Player> getAllPlayers() {
		return players;
	}

	public List<ContractOverview> getAllContractOverviews() {
		return contractOverviews;
	}
	
	public List<YearlyContract> getAllYearlyContracts(){
		return yearlyContracts;
	}
	
	public List<Team> getAllTeams(){
		return teams;
	}
	
	public List<DeadMoney> getAllDeadMoneys(){
		return deadMoneys;
	}
	
	public Integer addPlayer(Player player){
		this.players.add(player);
		return players.get(players.size()-1).getId();
	}

	public Integer addContractOverview(ContractOverview contractOverview){
		this.contractOverviews.add(contractOverview);
		return contractOverviews.get(contractOverviews.size()-1).getId();
	}
	
	public Integer addTeam(Team team){
		this.teams.add(team);
		return teams.get(teams.size()-1).getId();
	}

	public Integer addYearlyContract(YearlyContract yearlyContract) {
		this.yearlyContracts.add(yearlyContract);
		return yearlyContracts.get(yearlyContracts.size()-1).getId();
	}

	public Integer addDeadMoney(DeadMoney deadMoney) {
		this.deadMoneys.add(deadMoney);
		return deadMoneys.get(deadMoneys.size()-1).getId();
	}
}
