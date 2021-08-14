package com.mediscreen.service;

public interface ReportService {

  /**
   * Get the diabete report
   * 
   * @param patientId the patient id
   * @return the diabete report
   */
  String generateReport(int patientId);

}
