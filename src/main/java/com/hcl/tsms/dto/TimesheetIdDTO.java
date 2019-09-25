package com.hcl.tsms.dto;

import java.io.Serializable;

public class TimesheetIdDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer timeSheetId;

	/**
	 * @return the timeSheetId
	 */
	public Integer getTimeSheetId() {
		return timeSheetId;
	}

	/**
	 * @param timeSheetId the timeSheetId to set
	 */
	public void setTimeSheetId(Integer timeSheetId) {
		this.timeSheetId = timeSheetId;
	}

}
