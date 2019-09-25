package com.hcl.tsms.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.tsms.dto.ApprovalRequestDTO;
import com.hcl.tsms.dto.TSApprovalResponseDTO;
import com.hcl.tsms.dto.TimesheetIdDTO;
import com.hcl.tsms.entity.Timesheet;
import com.hcl.tsms.repository.TimesheetRepository;

@RunWith(MockitoJUnitRunner.class)
public class TimesheetApprovalServiceImplTest {

	@Mock
	TimesheetRepository timesheetRepository;

	@InjectMocks
	TimesheetApprovalServiceImpl timesheetApprovalServiceImpl;
	TimesheetIdDTO timesheetIdDTO = null;
	ApprovalRequestDTO approvalRequestDTO = null;
	Timesheet timesheet = null;
	List<TSApprovalResponseDTO> approveList = null;
	TSApprovalResponseDTO tSApprovalResponseDTO = null;

	@org.junit.Before
	public void setup() {

		timesheetIdDTO = new TimesheetIdDTO();
		timesheetIdDTO.setTimeSheetId(1);
		List<TimesheetIdDTO> timesheetIdDTOList = new ArrayList<>();
		timesheetIdDTOList.add(timesheetIdDTO);

		approvalRequestDTO = new ApprovalRequestDTO();
		approvalRequestDTO.setStatus("APPROVED");
		approvalRequestDTO.setUserId(1);
		approvalRequestDTO.setTimesheets(timesheetIdDTOList);

		timesheet = new Timesheet();
		timesheet.setHours(9);
		timesheet.setManagerId(1);
		timesheet.setStatus("PENDING");
		timesheet.setTimesheetId(1);
		timesheet.setTsSubmitDate(LocalDate.now());
		timesheet.setUserId(2);
		timesheet.setWorkDate(LocalDate.of(2019, 9, 16));

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
	public void testTimeSheetApproval() {
		Mockito.when(timesheetRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(timesheet));

		Mockito.when(timesheetRepository.save(Mockito.any())).thenReturn(timesheet);
		List<TSApprovalResponseDTO> actualValue = timesheetApprovalServiceImpl.timeSheetApproval(approvalRequestDTO);
		assertEquals(approveList.size(), actualValue.size());
	}

}
