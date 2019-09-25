package com.hcl.tsms.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Timesheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer timesheetId;

	public Timesheet(LocalDate tsSubmitDate, LocalDate workDate, Integer hours, Integer userId, Integer managerId,
			String status) {
		super();

		this.tsSubmitDate = tsSubmitDate;
		this.workDate = workDate;
		this.hours = hours;
		this.userId = userId;
		this.managerId = managerId;
		this.status = status;
	}

	private LocalDate tsSubmitDate;
	private LocalDate workDate;

	private Integer hours;
	private Integer userId;
	private Integer managerId;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(Integer timesheetId) {
		this.timesheetId = timesheetId;
	}

	public LocalDate getTsSubmitDate() {
		return tsSubmitDate;
	}

	public void setTsSubmitDate(LocalDate tsSubmitDate) {
		this.tsSubmitDate = tsSubmitDate;
	}

	public LocalDate getWorkDate() {
		return workDate;
	}

	public void setWorkDate(LocalDate workDate) {
		this.workDate = workDate;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Timesheet() {
		super();

	}

}
