package com.lance.salarycap.service;

import java.util.List;

import com.lance.salarycap.dal.SalaryCapDal;
import com.salarycap.models.DeadMoney;

public class DeadMoneyService implements IRosterService<DeadMoney> {
	private SalaryCapDal salaryCapDal;
	
	public DeadMoneyService(SalaryCapDal salaryCapDal){
		this.salaryCapDal = salaryCapDal;
	}
	
	public List<DeadMoney> getAll(){
		return this.salaryCapDal.getAllDeadMoneys();
	}
	
	@Override
	public DeadMoney getById(Integer id){
		for (DeadMoney c : this.salaryCapDal.getAllDeadMoneys()) {
			if(c.getId().equals(id)){
				return c;
			}
		}
		return null;
	}
	
	@Override
	public Integer add(DeadMoney player){
		return this.salaryCapDal.addDeadMoney(player);
	}
}
