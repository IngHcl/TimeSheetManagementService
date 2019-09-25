package com.hcl.tsms.service;

import org.springframework.stereotype.Service;

import com.hcl.tsms.dto.TimesheetRequestDTO;
import com.hcl.tsms.dto.TimesheetResponseDTO;

@Service
public interface TimesheetService {

	public TimesheetResponseDTO submitTimesheet(TimesheetRequestDTO timesheetRequestDTO);

}
