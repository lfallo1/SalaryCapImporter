package com.salarycap.service;

import java.util.List;

import com.salarycap.dal.SalaryCapDal;
import com.salarycap.models.Player;

public class PlayerService implements IRosterService<Player>{
	private SalaryCapDal salaryCapDal;
	
	public PlayerService(SalaryCapDal salaryCapDal){
		this.salaryCapDal = salaryCapDal;
	}
	
	@Override
	public List<Player> getAll(){
		return this.salaryCapDal.getAllPlayers();
	}
	
	@Override
	public Player getById(Integer id){
		for (Player c : this.salaryCapDal.getAllPlayers()) {
			if(c.getId().equals(id)){
				return c;
			}
		}
		return null;
	}
	
	@Override
	public Integer add(Player player){
		return this.salaryCapDal.addPlayer(player);
	}
}
