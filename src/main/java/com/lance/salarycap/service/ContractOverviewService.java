package com.lance.salarycap.service;

import java.util.ArrayList;
import java.util.List;

import com.lance.salarycap.dal.SalaryCapDal;
import com.salarycap.models.ContractOverview;

public class ContractOverviewService implements IRosterService<ContractOverview> {
	private SalaryCapDal salaryCapDal;
	
	public ContractOverviewService(SalaryCapDal salaryCapDal){
		this.salaryCapDal = salaryCapDal;
	}
	
	@Override
	public List<ContractOverview> getAll(){
		return this.salaryCapDal.getAllContractOverviews();
	}
	
	public ContractOverview getByPlayer(Integer playerId){
		for (ContractOverview c : salaryCapDal.getAllContractOverviews()) {
			if(c.getPlayer().equals(playerId)){
				return c;
			}
		}
		return null;
	}
	
	@Override
	public ContractOverview getById(Integer id){
		for (ContractOverview c : this.salaryCapDal.getAllContractOverviews()) {
			if(c.getId().equals(id)){
				return c;
			}
		}
		return null;
	}
	
	@Override
	public Integer add(ContractOverview contractOverview){
		return this.salaryCapDal.addContractOverview(contractOverview);
	}
}
