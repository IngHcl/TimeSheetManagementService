package com.hcl.tsms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.tsms.dto.ApprovalRequestDTO;
import com.hcl.tsms.dto.TSApprovalResponseDTO;
import com.hcl.tsms.dto.TimesheetRequestDTO;
import com.hcl.tsms.dto.TimesheetResponseDTO;
import com.hcl.tsms.service.TimesheetApprovalService;
import com.hcl.tsms.service.TimesheetService;

/**
 * 
 * @author Shiva
 *
 */

@RestController
@RequestMapping("/hcl")
public class TimesheetController {

	@Autowired
	TimesheetService timesheetService;

	@Autowired
	TimesheetApprovalService timesheetApproval;

	@PostMapping("/timesheets")
	public ResponseEntity<TimesheetResponseDTO> submittingTimeSheet(
			@RequestBody TimesheetRequestDTO timesheetRequestDTO) {

		return new ResponseEntity<>(timesheetService.submitTimesheet(timesheetRequestDTO),
				HttpStatus.CREATED);

	}

	@PutMapping("/timesheets")
	public ResponseEntity<List<TSApprovalResponseDTO>> approvingTimeSheet(
			@RequestBody ApprovalRequestDTO approvalRequestDTO) {

		return new ResponseEntity<>(timesheetApproval.timeSheetApproval(approvalRequestDTO), HttpStatus.CREATED);

	}

}
