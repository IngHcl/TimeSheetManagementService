package com.hcl.tsms.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class FromDatesDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate fromToDates;

	public LocalDate getFromToDates() {
		return fromToDates;
	}

	public void setFromToDates(LocalDate fromToDates) {
		this.fromToDates = fromToDates;
	}

	


}
