package com.hcl.tsms.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.tsms.entity.Timesheet;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {
	
	public Optional<Timesheet> findByWorkDate(LocalDate workDate);

}
