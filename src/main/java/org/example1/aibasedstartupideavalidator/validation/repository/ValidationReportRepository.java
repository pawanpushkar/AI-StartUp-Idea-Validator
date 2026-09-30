package org.example1.aibasedstartupideavalidator.validation.repository;

import org.example1.aibasedstartupideavalidator.validation.entity.ValidationReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationReportRepository
        extends JpaRepository<ValidationReport, Long> {
}