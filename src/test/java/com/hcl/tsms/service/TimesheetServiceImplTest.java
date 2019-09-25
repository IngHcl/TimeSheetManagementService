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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hcl.tsms.dto.FromDatesDTO;
import com.hcl.tsms.dto.LeaveCalendarDTO;
import com.hcl.tsms.dto.TimesheetRequestDTO;
import com.hcl.tsms.dto.TimesheetResponseDTO;
import com.hcl.tsms.entity.Timesheet;
import com.hcl.tsms.repository.TimesheetRepository;
import com.hcl.tsms.util.TsmsConstants;

@RunWith(MockitoJUnitRunner.class)
public class TimesheetServiceImplTest {
	@Mock
	TimesheetRepository timesheetRepository;

	@Mock
	RestTemplate restTemplate;
	@InjectMocks
	TimesheetServiceImpl timesheetServiceImpl;

	Timesheet timesheet = null;
	TimesheetRequestDTO timesheetRequestDTO = null;

	List<Timesheet> timesheetList = new ArrayList<>();
	TimesheetResponseDTO timesheetResponseDTO = null;
	LeaveCalendarDTO leaveCalendarDTO = null;

	@org.junit.Before
	public void setup() {

		List<FromDatesDTO> datesList = new ArrayList<>();
		FromDatesDTO fromDatesDTO = new FromDatesDTO();
		fromDatesDTO.setFromToDates(LocalDate.of(2019, 9, 16));
		datesList.add(fromDatesDTO);
		timesheetRequestDTO = new TimesheetRequestDTO();

		timesheetRequestDTO.setHours(9);
		timesheetRequestDTO.setManagerId(1);
		timesheetRequestDTO.setStatus("PENDING");
		timesheetRequestDTO.setUserId(1);
		timesheetRequestDTO.setDates(datesList);

		timesheet = new Timesheet();
		timesheet.setHours(9);
		timesheet.setManagerId(1);
		timesheet.setStatus("PENDING");
		timesheet.setTimesheetId(1);
		timesheet.setTsSubmitDate(LocalDate.now());
		timesheet.setUserId(2);
		timesheet.setWorkDate(LocalDate.of(2019, 9, 16));
		timesheetList.add(timesheet);

		timesheetResponseDTO = new TimesheetResponseDTO(TsmsConstants.TSMS_SUBMITTED,
				TsmsConstants.TSMS_SUBMITTED_SUCCESSCODE);

		leaveCalendarDTO = new LeaveCalendarDTO();
		leaveCalendarDTO.setLeaveCalendarId(1);
		leaveCalendarDTO.setLeaveType("CH");

	}

	@Test
	public void testSubmitTimesheet() {

		LocalDate dates = LocalDate.of(2019, 9, 20);
		Mockito.when(timesheetRepository.findByWorkDate(Mockito.any())).thenReturn(Optional.of(timesheet));
		Mockito.when(restTemplate.getForEntity("http://localhost:9093/leaveCalendar/leaves?leaveType=" + dates,
				LeaveCalendarDTO.class)).thenReturn(new ResponseEntity(leaveCalendarDTO, HttpStatus.OK));
		Mockito.when(timesheetRepository.saveAll(Mockito.any())).thenReturn(timesheetList);
		TimesheetResponseDTO actualValue = timesheetServiceImpl.submitTimesheet(timesheetRequestDTO);

		assertEquals(timesheetResponseDTO.getStatusCode(), actualValue.getStatusCode());

	}

}
