package com.hcl.tsms.dto;

public class TimesheetResponseDTO {
	
	private String message;

	private Integer statusCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public TimesheetResponseDTO(String message, Integer statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

	public TimesheetResponseDTO() {
		
	}


}
