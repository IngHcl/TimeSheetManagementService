package com.hcl.tsms.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.tsms.dto.ApprovalRequestDTO;
import com.hcl.tsms.dto.FromDatesDTO;
import com.hcl.tsms.dto.TSApprovalResponseDTO;
import com.hcl.tsms.dto.TimesheetIdDTO;
import com.hcl.tsms.dto.TimesheetRequestDTO;
import com.hcl.tsms.dto.TimesheetResponseDTO;
import com.hcl.tsms.service.TimesheetApprovalServiceImpl;
import com.hcl.tsms.service.TimesheetServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TimesheetControllerTest {

	@Mock
	TimesheetServiceImpl timesheetServiceImpl;

	@Mock
	TimesheetApprovalServiceImpl timesheetApprovalServiceImpl;

	@InjectMocks
	TimesheetController timesheetController;

	TimesheetRequestDTO timesheetRequestDTO = null;
	TimesheetResponseDTO timesheetResponseDTO = null;
	ApprovalRequestDTO approvalRequestDTO = null;
	TimesheetIdDTO timesheetIdDTO = null;
	List<TSApprovalResponseDTO> approveList = null;
	TSApprovalResponseDTO tSApprovalResponseDTO = null;

	@Before
	public void setup() {

		List<FromDatesDTO> datesList = new ArrayList<>();
		FromDatesDTO fromDatesDTO = new FromDatesDTO();
		fromDatesDTO.setFromToDates(LocalDate.of(2019, 9, 16));
		datesList.add(fromDatesDTO);
		timesheetRequestDTO = new TimesheetRequestDTO();

		timesheetRequestDTO.setHours(9);
		timesheetRequestDTO.setManagerId(2);
		timesheetRequestDTO.setStatus("PENDING");
		timesheetRequestDTO.setUserId(1);
		timesheetRequestDTO.setDates(datesList);

		timesheetResponseDTO = new TimesheetResponseDTO();
		timesheetIdDTO = new TimesheetIdDTO();
		timesheetIdDTO.setTimeSheetId(1);
		List<TimesheetIdDTO> timesheetIdDTOList = new ArrayList<>();
		timesheetIdDTOList.add(timesheetIdDTO);

		approvalRequestDTO = new ApprovalRequestDTO();
		approvalRequestDTO.setStatus("APPROVED");
		approvalRequestDTO.setUserId(1);
		approvalRequestDTO.setTimesheets(timesheetIdDTOList);

		approveList = new ArrayList<>();

		tSApprovalResponseDTO = new TSApprovalResponseDTO();
		tSApprovalResponseDTO.setHours(9);
		tSApprovalResponseDTO.setRemarks("");
		tSApprovalResponseDTO.setStatus("APPROVED");
		tSApprovalResponseDTO.setTaskName("Training");
		tSApprovalResponseDTO.setworkDate(LocalDate.of(2019, 9, 16));

		approveList.add(tSApprovalResponseDTO);

	}

	@Test
	public void testSubmittingTimeSheet() {

		Mockito.when(timesheetServiceImpl.submitTimesheet(timesheetRequestDTO)).thenReturn(timesheetResponseDTO);

		ResponseEntity<TimesheetResponseDTO> actualValue = timesheetController.submittingTimeSheet(timesheetRequestDTO);

		assertEquals(timesheetResponseDTO.getStatusCode(), actualValue.getBody().getStatusCode());

	}

	@Test
	public void testApprovingTimeSheet() {

		Mockito.when(timesheetApprovalServiceImpl.timeSheetApproval(approvalRequestDTO)).thenReturn(approveList);

		ResponseEntity<List<TSApprovalResponseDTO>> actualValue = timesheetController
				.approvingTimeSheet(approvalRequestDTO);

		assertEquals(tSApprovalResponseDTO.getHours(), actualValue.getBody().get(0).getHours());

	}

}
