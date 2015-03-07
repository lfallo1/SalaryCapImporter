package com.salarycap.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface DeadMoneyDao {
	@SqlUpdate("DELETE FROM dead_money")
	void deleteAll();
	
	@SqlUpdate("INSERT INTO dead_money (id, dead_money, year, player, team) VALUES (:id, :deadMoney, :year, :player, :team);")
	void insert(@Bind("id") Integer id, @Bind("deadMoney") Double deadMoney,
			@Bind("year") Integer year, @Bind("player") Integer player, @Bind("team") Integer team);
}
