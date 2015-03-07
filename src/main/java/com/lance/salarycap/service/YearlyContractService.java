package com.lance.salarycap.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.lance.salarycap.dal.DataSourceLayer;
import com.lance.salarycap.dal.SalaryCapDal;
import com.salarycap.models.YearlyContract;
import com.salarycap.utilities.RosterUtilities;

public class YearlyContractService implements IRosterService<YearlyContract>{
	private SalaryCapDal salaryCapDal;
	private ContractOverviewService contractOverviewService;
	private static final Logger logger = Logger.getLogger(YearlyContractService.class);
	
	public YearlyContractService(SalaryCapDal salaryCapDal, ContractOverviewService contractOverviewService){
		this.salaryCapDal = salaryCapDal;
		this.contractOverviewService = contractOverviewService;
	}
	
	@Override
	public List<YearlyContract> getAll(){
		return this.salaryCapDal.getAllYearlyContracts();
	}
	
	@Override
	public YearlyContract getById(Integer id){
		for (YearlyContract c : this.salaryCapDal.getAllYearlyContracts()) {
			if(c.getId().equals(id)){
				return c;
			}
		}
		return null;
	}
	
	@Override
	public Integer add(YearlyContract yearlyContract){
		try{
			return this.salaryCapDal.addYearlyContract(yearlyContract);
		}
		catch(Exception e){
			logger.info(yearlyContract.toString() +"---> Not added to yearly contract List. " + e.getMessage());
			return -1;
		}
	}
}
