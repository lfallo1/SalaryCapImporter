package com.salarycap.models;

import org.joda.time.LocalDate;

import com.salarycap.annotations.Name;

public class Player {
	@Name("id")
	private Integer id;
	@Name("Accrued")
	private Integer accrued;
	@Name("DOB")
	private LocalDate dateOfBirth;
	@Name("Name")
	private String name;
	@Name("Notes")
	private String notes;
	@Name("college")
	private String college;
	@Name("draft_pick")
	private Integer draftPick;
	@Name("draft_round")
	private Integer draftRound;
	@Name("draft_year")
	private Integer draftYear;
	@Name("height")
	private String height;
	@Name("original_team_id")
	private Integer originalTeamId;
	@Name("weight")
	private Integer weight;

	public Player(Integer id, Integer accrued, LocalDate dateOfBirth, String name,
			String notes, String college, Integer draftPick,
			Integer draftRound, Integer draftYear, String height,
			Integer originalTeamId, Integer weight) {
		this.id = id;
		this.accrued = accrued;
		this.dateOfBirth = dateOfBirth;
		this.name = name;
		this.notes = notes;
		this.college = college;
		this.draftPick = draftPick;
		this.draftRound = draftRound;
		this.draftYear = draftYear;
		this.height = height;
		this.originalTeamId = originalTeamId;
		this.weight = weight;
	}

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccrued() {
		return accrued;
	}

	public void setAccrued(Integer accrued) {
		this.accrued = accrued;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public Integer getDraftPick() {
		return draftPick;
	}

	public void setDraftPick(Integer draftPick) {
		this.draftPick = draftPick;
	}

	public Integer getDraftRound() {
		return draftRound;
	}

	public void setDraftRound(Integer draftRound) {
		this.draftRound = draftRound;
	}

	public Integer getDraftYear() {
		return draftYear;
	}

	public void setDraftYear(Integer draftYear) {
		this.draftYear = draftYear;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Integer getOriginalTeamId() {
		return originalTeamId;
	}

	public void setOriginalTeamId(Integer originalTeamId) {
		this.originalTeamId = originalTeamId;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return id + "\t" + accrued + "\t"
				+ dateOfBirth + "\t" + name
				+ "\t" + college + "\t" + draftPick
				+ "\t" + draftRound + "\t" + draftYear
				+ "\t" + height + "\t" + originalTeamId
				+ "\t" + weight + "\t" + notes + "\r\n";
	}
	
}
