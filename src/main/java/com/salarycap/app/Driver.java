package com.salarycap.app;

import org.json.JSONObject;

import com.salarycap.dal.DataSourceLayer;
import com.salarycap.dal.SalaryCapDal;
import com.salarycap.importers.ContractImporter;
import com.salarycap.importers.PlayerImporter;
import com.salarycap.importers.TeamImporter;
import com.salarycap.service.ContractOverviewService;
import com.salarycap.service.DeadMoneyService;
import com.salarycap.service.PlayerService;
import com.salarycap.service.TeamService;
import com.salarycap.service.YearlyContractService;

public class Driver {

	public static void main(String[] args) {
		
		SalaryCapDal salaryCapDal = new SalaryCapDal();
		ContractOverviewService contractOverviewService = new ContractOverviewService(salaryCapDal);
		PlayerService playerService = new PlayerService(salaryCapDal);
		TeamService teamService = new TeamService(salaryCapDal);
		YearlyContractService yearlyContractService = new YearlyContractService(salaryCapDal, contractOverviewService);
		DeadMoneyService deadMoneyService = new DeadMoneyService(salaryCapDal);
		
		PlayerImporter playerImporter = new PlayerImporter(playerService);
		ContractImporter contractImporter = new ContractImporter(contractOverviewService, 
				yearlyContractService, deadMoneyService);
		TeamImporter teamImporter = new TeamImporter(teamService, yearlyContractService);
		
		playerImporter.doImport();
		contractImporter.doImport();
		teamImporter.doImport();

		System.out.println("Saving data to files and/or database.");
		
		/*
		 * Commented out for safety
		FileUtilities.saveToFile("Player.txt", playerService);
		FileUtilities.saveToFile("YearlyContract.txt", yearlyContractService);
		FileUtilities.saveToFile("ContractOverview.txt", contractOverviewService);
		FileUtilities.saveToFile("DeadMoney.txt", deadMoneyService);
		FileUtilities.saveToFile("Team.txt", teamService);
		*/
		
        DataSourceLayer dataSourceLayer = new DataSourceLayer(salaryCapDal, contractOverviewService);
        dataSourceLayer.clearDatabase();
        dataSourceLayer.addTeamsToDb();
        dataSourceLayer.addPlayersToDb();
        dataSourceLayer.addContractOverviewsToDb();
        dataSourceLayer.addYearlyContractsToDb();
        dataSourceLayer.addDeadMoneysToDb();
        
        System.out.println("\r\nIMPORT COMPLETE!!");
	}
}
