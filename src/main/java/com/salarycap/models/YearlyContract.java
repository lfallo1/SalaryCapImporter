package com.salarycap.models;

import com.salarycap.annotations.Name;

public class YearlyContract {
	@Name("ID")
	private Integer id;
	@Name("player_id")
	private Integer player;
	@Name("team_id")
	private Integer team;
	@Name("Year")
	private Integer year;
	@Name("BS")
	private Double baseSalary;
	@Name("CP")
	private Double capCharge;
	@Name("CS")
	private Double capSavings;
	@Name("DM")
	private Double deadMoney;
	@Name("GBS")
	private Double guaranteedBaseSalary;
	@Name("Notes")
	private String notes;
	@Name("OB")
	private Double optionBonus;
	@Name("PB")
	private Double signingBonus;
	@Name("RB")
	private Double rosterBonus;
	@Name("WO")
	private Double workoutBonus;
	@Name("Team")
	private String teamName;
	@Name("Role")
	private String role;
	@Name("Status")
	private String status;
	@Name("Position")
	private String position;

	public YearlyContract() {
	}

	public YearlyContract(Integer id, Integer player, Integer team,
			Integer year, Double baseSalary, Double capCharge,
			Double capSavings, Double deadMoney, Double guaranteedBaseSalary,
			String notes, Double optionBonus, Double signingBonus,
			Double rosterBonus, Double workoutBonus, String teamName,
			String role, String status, String position) {
		this.id = id;
		this.player = player;
		this.team = team;
		this.year = year;
		this.baseSalary = baseSalary;
		this.capCharge = capCharge;
		this.capSavings = capSavings;
		this.deadMoney = deadMoney;
		this.guaranteedBaseSalary = guaranteedBaseSalary;
		this.notes = notes;
		this.optionBonus = optionBonus;
		this.signingBonus = signingBonus;
		this.rosterBonus = rosterBonus;
		this.workoutBonus = workoutBonus;
		this.teamName = teamName;
		this.role = role;
		this.status = status;
		this.position = position;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Double getCapCharge() {
		return capCharge;
	}

	public void setCapCharge(Double capCharge) {
		this.capCharge = capCharge;
	}

	public Double getCapSavings() {
		return capSavings;
	}

	public void setCapSavings(Double capSavings) {
		this.capSavings = capSavings;
	}

	public Double getDeadMoney() {
		return deadMoney;
	}

	public void setDeadMoney(Double deadMoney) {
		this.deadMoney = deadMoney;
	}

	public Double getGuaranteedBaseSalary() {
		return guaranteedBaseSalary;
	}

	public void setGuaranteedBaseSalary(Double guaranteedBaseSalary) {
		this.guaranteedBaseSalary = guaranteedBaseSalary;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Double getOptionBonus() {
		return optionBonus;
	}

	public void setOptionBonus(Double optionBonus) {
		this.optionBonus = optionBonus;
	}

	public Double getSigningBonus() {
		return signingBonus;
	}

	public void setSigningBonus(Double signingBonus) {
		this.signingBonus = signingBonus;
	}

	public Double getRosterBonus() {
		return rosterBonus;
	}

	public void setRosterBonus(Double rosterBonus) {
		this.rosterBonus = rosterBonus;
	}

	public Double getWorkoutBonus() {
		return workoutBonus;
	}

	public void setWorkoutBonus(Double workoutBonus) {
		this.workoutBonus = workoutBonus;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return id + "\t" + player + "\t" + team + "\t" + baseSalary + "\t"
				+ capCharge + "\t" + capSavings + "\t" + deadMoney + "\t"
				+ guaranteedBaseSalary + "\t" + notes + "\t" + optionBonus
				+ "\t" + signingBonus + "\t" + position + "\t" + rosterBonus
				+ "\t" + role + "\t" + status + "\t" + teamName + "\t"
				+ workoutBonus + "\t" + year + "\r\n";
	}

}
