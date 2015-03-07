package com.salarycap.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface TeamDao {
	@SqlUpdate("DELETE FROM team")
	void deleteAll();
	
	@SqlUpdate("INSERT INTO team (id, name) VALUES (:id, :name)")
	void insert(@Bind("id") Integer id, @Bind("name") String name);
}
