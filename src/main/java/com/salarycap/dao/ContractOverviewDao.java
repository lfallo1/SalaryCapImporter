package com.salarycap.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface ContractOverviewDao {
	@SqlUpdate("DELETE FROM contract_overview")
	void deleteAll();
	
	@SqlUpdate("INSERT INTO contract_overview (id, player, team, average_per_year, free_agent_year, guarantee, position, role, status, total, years) VALUES (:id, :player, :team, :averagePerYear, :freeAgentYear, :guarantee, :position, :role, :status, :total, :years)")
	void insert(@Bind("id") Integer id, @Bind("player") Integer player, @Bind("team") Integer team, 
			@Bind("averagePerYear") Double averagePerYear, @Bind("freeAgentYear") String freeAgentYear, 
			@Bind("guarantee") Double guarantee, @Bind("position") String position, @Bind("role") String role, 
			@Bind("status") String status, @Bind("total") Double total, @Bind("years") Integer years);
}
