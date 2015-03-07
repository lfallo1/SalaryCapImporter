package com.salarycap.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface PlayerDao {
	@SqlUpdate("DELETE FROM player")
	void deleteAll();
	
	@SqlUpdate("INSERT INTO player (id, accrued, date_of_birth, name, notes, college, draft_pick, draft_round, draft_year, height, original_team_id, weight) VALUES (:id, :accrued, :dateOfBirth, :name, :notes, :college, :draftPick, :draftRound, :draftYear, :height, :originalTeamId, :weight)")
	void insert(@Bind("id") Integer id, @Bind("accrued") Integer accrued, @Bind("dateOfBirth") String dateOfBirth,
			@Bind("name") String name, @Bind("notes") String notes, @Bind("college") String college,
			@Bind("draftPick") Integer draftPick, @Bind("draftRound") Integer draftRound, @Bind("draftYear") Integer draftYear, 
			@Bind("height") String height, @Bind("originalTeamId") Integer originalTeamId, @Bind("weight") Integer weight);
}
