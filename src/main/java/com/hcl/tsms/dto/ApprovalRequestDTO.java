package com.hcl.tsms.dto;

import java.io.Serializable;
import java.util.List;

public class ApprovalRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the timesheets
	 */
	public List<TimesheetIdDTO> getTimesheets() {
		return timesheets;
	}
	/**
	 * @param timesheets the timesheets to set
	 */
	public void setTimesheets(List<TimesheetIdDTO> timesheets) {
		this.timesheets = timesheets;
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
	private List<TimesheetIdDTO> timesheets;
	private String status;
	
	

}
