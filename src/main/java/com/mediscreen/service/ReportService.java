package com.mediscreen.service;

import com.mediscreen.model.Report;

public interface ReportService {

  Report generateReport(int patientId);

  /**
   * Check if the patient id exists
   * 
   * @param patientId patient id
   * @return true if it exists
   */
  boolean doesPatientExist(Integer patientId);

}
