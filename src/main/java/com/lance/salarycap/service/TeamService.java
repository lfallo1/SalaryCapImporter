package com.lance.salarycap.service;

import java.util.List;

import com.lance.salarycap.dal.SalaryCapDal;
import com.salarycap.models.Team;

public class TeamService implements IRosterService<Team>{
	private SalaryCapDal salaryCapDal;
	
	public TeamService(SalaryCapDal salaryCapDal){
		this.salaryCapDal = salaryCapDal;
	}
	
	@Override
	public List<Team> getAll(){
		return this.salaryCapDal.getAllTeams();
	}
	
	@Override
	public Team getById(Integer id){
		for (Team c : this.salaryCapDal.getAllTeams()) {
			if(c.getId().equals(id)){
				return c;
			}
		}
		return null;
	}
	
	@Override
	public Integer add(Team team){
		return this.salaryCapDal.addTeam(team);
	}
}
