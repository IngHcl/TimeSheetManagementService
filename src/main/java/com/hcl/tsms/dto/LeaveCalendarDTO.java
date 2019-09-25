package com.hcl.tsms.dto;

public class LeaveCalendarDTO {

	private Integer leaveCalendarId;
	public LeaveCalendarDTO() {
		super();
		
	}

	private String leaveType;

	public Integer getLeaveCalendarId() {
		return leaveCalendarId;
	}

	public void setLeaveCalendarId(Integer leaveCalendarId) {
		this.leaveCalendarId = leaveCalendarId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

}
