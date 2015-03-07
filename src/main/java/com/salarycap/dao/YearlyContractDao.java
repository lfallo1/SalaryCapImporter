package com.salarycap.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface YearlyContractDao {
	@SqlUpdate("DELETE FROM yearly_contract")
	void deleteAll();
	
	@SqlUpdate("INSERT INTO yearly_contract (id, player, team, year, base_salary, cap_charge, cap_savings, dead_money, guaranteed_base_salary, notes, option_bonus, signing_bonus, roster_bonus, workout_bonus, team_name, role, status, position) VALUES (:id, :player, :team, :year, :baseSalary, :capCharge, :capSavings, :deadMoney, :guaranteedBaseSalary, :notes, :optionBonus, :signingBonus, :rosterBonus, :workoutBonus, :teamName, :role, :status, :position);")
	void insert(@Bind("id") Integer id, @Bind("player") Integer player, @Bind("team") Integer team, 
			@Bind("year") Integer year, @Bind("baseSalary") Double baseSalary, @Bind("capCharge") Double capCharge,
			@Bind("capSavings") Double capSavings, @Bind("deadMoney") Double deadMoney, @Bind("guaranteedBaseSalary") Double guaranteedBaseSalary,
			@Bind("notes") String notes, @Bind("optionBonus") Double optionBonus, @Bind("signingBonus") Double signingBonus,
			@Bind("rosterBonus") Double rosterBonus, @Bind("workoutBonus") Double workoutBonus, @Bind("teamName") String teamName,
			@Bind("role") String role, @Bind("status") String status, @Bind("position") String position);
}
