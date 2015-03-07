package com.salarycap.models;

import com.salarycap.annotations.Name;

public class DeadMoney {
	@Name("ID")
	private Integer id;
	@Name("Dead")
	private Double deadMoney;	
	@Name("Year")
	private Integer year;
	@Name("player_id")
	private Integer player;
	@Name("team_id")
	private Integer team;
	
	public DeadMoney(){}
	
	public DeadMoney(Double deadMoney, Integer id, Integer year,
			Integer player, Integer team) {
		this.deadMoney = deadMoney;
		this.id = id;
		this.year = year;
		this.player = player;
		this.team = team;
	}
	public Double getDeadMoney() {
		return deadMoney;
	}
	public void setDeadMoney(Double deadMoney) {
		this.deadMoney = deadMoney;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getPlayer() {
		return player;
	}
	public void setPlayer(Integer player) {
		this.player = player;
	}
	public Integer getTeam() {
		return team;
	}
	public void setTeam(Integer team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return deadMoney + "\t" + id + "\t" + year
				+ "\t" + player + "\t" + team + "\r\n";
	}
	
	
}
