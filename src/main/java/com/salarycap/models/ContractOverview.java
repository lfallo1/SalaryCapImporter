package com.salarycap.models;

import com.salarycap.annotations.Name;

public class ContractOverview {
	@Name("ID")
	private Integer id;
	@Name("player_id")
	private Integer player;
	@Name("team_id")
	private Integer team;	
	@Name("APY")
	private Double averagePerYear;
	@Name("FA")
	private String freeAgentYear;
	@Name("Guarantee")
	private Double guarantee;
	@Name("Name")
	private String name;
	@Name("Position")
	private String position;
	@Name("Role")
	private String role;
	@Name("Status")
	private String status;
	@Name("Total")
	private Double total;
	@Name("Years")
	private Integer years;
	
	public ContractOverview(){}
	
	public ContractOverview(Integer id, Integer player, Integer team,
			Double averagePerYear, String freeAgentYear, Double guarantee,
			String name, String position, String role, String status,
			Double total, Integer years) {
		this.id = id;
		this.player = player;
		this.team = team;
		this.averagePerYear = averagePerYear;
		this.freeAgentYear = freeAgentYear;
		this.guarantee = guarantee;
		this.name = name;
		this.position = position;
		this.role = role;
		this.status = status;
		this.total = total;
		this.years = years;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Double getAveragePerYear() {
		return averagePerYear;
	}
	public void setAveragePerYear(Double averagePerYear) {
		this.averagePerYear = averagePerYear;
	}
	public String getFreeAgentYear() {
		return freeAgentYear;
	}
	public void setFreeAgentYear(String freeAgentYear) {
		this.freeAgentYear = freeAgentYear;
	}
	public Double getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(Double guarantee) {
		this.guarantee = guarantee;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getYears() {
		return years;
	}
	public void setYears(Integer years) {
		this.years = years;
	}

	@Override
	public String toString() {
		return id + "\t" + player + "\t" + team + "\t"
				+ averagePerYear + "\t" + freeAgentYear + "\t"
				+ guarantee + "\t" + name + "\t" + position + "\t"
				+ role + "\t" + status + "\t" + total + "\t" + years
				+ "\r\n";
	}

	
}
