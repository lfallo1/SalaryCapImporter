package com.salarycap.importers;

import java.util.List;

import com.salarycap.models.Team;
import com.salarycap.models.YearlyContract;
import com.salarycap.service.TeamService;
import com.salarycap.service.YearlyContractService;

public class TeamImporter implements Importer{
	private TeamService teamService;
	private YearlyContractService yearlyContractService;

	public TeamImporter(TeamService teamService, YearlyContractService yearlyContractService) {
		this.teamService = teamService;
		this.yearlyContractService = yearlyContractService;
	}
	
	@Override
	public void doImport(){
		List<YearlyContract> yearlyContracts = yearlyContractService.getAll();
		for (YearlyContract c : yearlyContracts) {
			if(!teamService.getAll().contains(new Team(c.getTeam(), c.getTeamName()))){
				String teamName = c.getTeam().equals(0) ? "Not available" : c.getTeamName();
				teamService.add(new Team(c.getTeam(), teamName));
			}
		}
	}
}
