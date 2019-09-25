package com.hcl.tsms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.tsms.dto.FromDatesDTO;
import com.hcl.tsms.dto.LeaveCalendarDTO;
import com.hcl.tsms.dto.TimesheetRequestDTO;
import com.hcl.tsms.dto.TimesheetResponseDTO;
import com.hcl.tsms.entity.Timesheet;
import com.hcl.tsms.exception.TsmsException;
import com.hcl.tsms.repository.TimesheetRepository;
import com.hcl.tsms.util.TsmsConstants;

/**
 * 
 * @author Shiva
 * @version 1.0
 *
 */

@Service
public class TimesheetServiceImpl implements TimesheetService {

	private static final Logger lOGGER = LoggerFactory.getLogger(TimesheetServiceImpl.class);

	@Value("${service.url.leaveCalendar}")
	private String leaveCalendarUrl;

	@Autowired
	TimesheetRepository timesheetRepository;
	LocalDate workDay = null;

	/**
	 * @param TimesheetRequestDTO
	 * @return TimesheetResponseDTO
	 * 
	 *         This method contains logic to submit the time sheet with all
	 *         validations
	 */

	@Override
	public TimesheetResponseDTO submitTimesheet(TimesheetRequestDTO timesheetRequestDTO) {

		lOGGER.info("Inside Timesheet serviceImpl");

		List<FromDatesDTO> dates = timesheetRequestDTO.getDates();

		List<Timesheet> timesheetList = new ArrayList<>();
		TimesheetResponseDTO timesheetResponseDTO = null;

		dates.stream().forEach(d -> {

			workDay = d.getFromToDates();

			String week = workDay.getDayOfWeek().name();
			Optional<Timesheet> optWorkDate = timesheetRepository.findByWorkDate(workDay);

			String leave = getLeaveType();

			optWorkDate.ifPresent(timeSheet -> {
				throw new TsmsException(TsmsConstants.TSMS_ALREADY_SUBMITTED);
			});

			if (week.equals(TsmsConstants.TSMS_SATURDAY) || week.equals(TsmsConstants.TSMS_SUNDAY)
					|| leave.equals(TsmsConstants.TSMS_CH)) {

				throw new TsmsException(TsmsConstants.TSMS_WEEKEND);
			}

			else {

				Timesheet timesheet = new Timesheet(LocalDate.now(), d.getFromToDates(), timesheetRequestDTO.getHours(),
						timesheetRequestDTO.getManagerId(), timesheetRequestDTO.getUserId(),
						TsmsConstants.TSMS_PENDING);

				timesheetList.add(timesheet);
			}

		});
		List<Timesheet> savedTimesheetList = timesheetRepository.saveAll(timesheetList);

		if (Objects.isNull(savedTimesheetList) || savedTimesheetList.isEmpty()) {

			throw new TsmsException(TsmsConstants.SUBMIT_PROBLEM);
		} else {
			timesheetResponseDTO = new TimesheetResponseDTO(TsmsConstants.TSMS_SUBMITTED,
					TsmsConstants.TSMS_SUBMITTED_SUCCESSCODE);

		}
		return timesheetResponseDTO;

	}

	public String getLeaveType() {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<LeaveCalendarDTO> leaveType = restTemplate.getForEntity(leaveCalendarUrl + workDay,
				LeaveCalendarDTO.class);
		String leave = leaveType.getBody().getLeaveType();

		lOGGER.info("Leave Calendar service:{}", leave);
		return leave;
	}

}
