package com.hcl.tsms.dto;

import java.io.Serializable;

import java.util.List;

public class TimesheetRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<FromDatesDTO> dates;

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

	public List<FromDatesDTO> getDates() {
		return dates;
	}

	public void setDates(List<FromDatesDTO> dates) {
		this.dates = dates;
	}

}
