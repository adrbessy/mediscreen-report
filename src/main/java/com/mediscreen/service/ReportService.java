package com.mediscreen.service;

public interface ReportService {

  String generateReport(int patientId);

  /**
   * Check if the patient id exists
   * 
   * @param patientId patient id
   * @return true if it exists
   */
  boolean doesPatientExist(Integer patientId);

}
