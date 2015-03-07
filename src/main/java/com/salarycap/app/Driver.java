package com.salarycap.app;

import com.lance.salarycap.dal.DataSourceLayer;
import com.lance.salarycap.dal.SalaryCapDal;
import com.lance.salarycap.service.ContractOverviewService;
import com.lance.salarycap.service.DeadMoneyService;
import com.lance.salarycap.service.PlayerService;
import com.lance.salarycap.service.YearlyContractService;
import com.salarycap.importers.ContractImporter;
import com.salarycap.importers.PlayerImporter;

public class Driver {

	public static void main(String[] args) {
		SalaryCapDal salaryCapDal = new SalaryCapDal();
		ContractOverviewService contractOverviewService = new ContractOverviewService(salaryCapDal);
		PlayerService playerService = new PlayerService(salaryCapDal);
		//TeamService teamService = new TeamService(salaryCapDal);
		YearlyContractService yearlyContractService = new YearlyContractService(salaryCapDal, contractOverviewService);
		DeadMoneyService deadMoneyService = new DeadMoneyService(salaryCapDal);
		
		PlayerImporter playerImporter = new PlayerImporter(playerService);
		ContractImporter contractImporter = new ContractImporter(contractOverviewService, 
				yearlyContractService, deadMoneyService);
		//TeamImporter teamImporter = new TeamImporter(teamService, yearlyContractService);
		
		playerImporter.doImport();
		contractImporter.doImport();
		//teamImporter.doImport();

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
        //dataSourceLayer.addTeamsToDb();
        dataSourceLayer.addPlayersToDb();
        dataSourceLayer.addContractOverviewsToDb();
        dataSourceLayer.addYearlyContractsToDb();
        dataSourceLayer.addDeadMoneysToDb();
        
        System.out.println("\r\nIMPORT COMPLETE!!");
	}
}
