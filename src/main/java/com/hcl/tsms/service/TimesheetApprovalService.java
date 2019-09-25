package com.hcl.tsms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hcl.tsms.dto.ApprovalRequestDTO;
import com.hcl.tsms.dto.TSApprovalResponseDTO;

@Service
public interface TimesheetApprovalService {

	public List<TSApprovalResponseDTO> timeSheetApproval(ApprovalRequestDTO approvalRequestDTO);
}
