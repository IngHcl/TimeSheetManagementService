package com.hcl.tsms.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class TSApprovalResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LocalDate workDate;

	public TSApprovalResponseDTO(LocalDate workDate, Integer hours, String remarks, String status, String taskName) {
		super();
		this.workDate = workDate;
		this.hours = hours;
		this.remarks = remarks;
		this.status = status;
		this.taskName = taskName;
	}

	private Integer hours;
	private String remarks;
	private String status;
	private String taskName;

	/**
	 * @return the workDate
	 */
	public LocalDate getworkDate() {
		return workDate;
	}

	/**
	 * @param workDate the workDate to set
	 */
	public void setworkDate(LocalDate WorkDate) {
		workDate = WorkDate;
	}

	/**
	 * @return the hours
	 */
	public Integer getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(Integer hours) {
		this.hours = hours;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public TSApprovalResponseDTO() {

	}

}
