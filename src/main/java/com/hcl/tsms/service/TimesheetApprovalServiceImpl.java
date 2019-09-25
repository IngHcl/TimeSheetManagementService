package com.hcl.tsms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.tsms.dto.ApprovalRequestDTO;
import com.hcl.tsms.dto.TSApprovalResponseDTO;
import com.hcl.tsms.dto.TimesheetIdDTO;
import com.hcl.tsms.entity.Timesheet;
import com.hcl.tsms.repository.TimesheetRepository;
import com.hcl.tsms.util.TsmsConstants;

/**
 * 
 * @author Shiva
 * 
 * 
 *
 */
@Service
public class TimesheetApprovalServiceImpl implements TimesheetApprovalService {

	@Autowired
	TimesheetRepository timesheetRepository;

	/**
	 * @param ApprovalRequestDTO
	 * @return List<TSApprovalResponseDTO>
	 * 
	 *         This method contains logic to approve/reject/referback the timesheet
	 * 
	 */
	@Override
	public List<TSApprovalResponseDTO> timeSheetApproval(ApprovalRequestDTO approvalRequestDTO) {

		List<TimesheetIdDTO> timesheetList = approvalRequestDTO.getTimesheets();

		String status = approvalRequestDTO.getStatus();
		List<TSApprovalResponseDTO> approvedList = new ArrayList<>();

		timesheetList.stream().forEach(t -> {
			Optional<Timesheet> optTimeSheet = timesheetRepository.findById(t.getTimeSheetId());

			optTimeSheet.ifPresent(timesheet -> {
				Timesheet timeSheet = optTimeSheet.get();
				timeSheet.setStatus(status);
				timesheetRepository.save(timesheet);
				TSApprovalResponseDTO tSApprovalResponseDTO = new TSApprovalResponseDTO(timesheet.getWorkDate(),
						timesheet.getHours(), TsmsConstants.TSMS_REMARKS, timesheet.getStatus(),
						TsmsConstants.TSMS_TASKNAME);
				approvedList.add(tSApprovalResponseDTO);

			});

		});

		return approvedList;
	}

}
