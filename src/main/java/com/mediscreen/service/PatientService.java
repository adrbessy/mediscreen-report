package com.mediscreen.service;

import com.mediscreen.model.Patient;

public interface PatientService {

  /**
   * Check if the patient id exists
   * 
   * @param patientId patient id
   * @return true if it exists
   */
  boolean doesPatientExist(Integer patientId);

  /**
   * Get the patient age
   * 
   * @param dob the date of birth
   * @return the age of the patient
   */
  int getPatientAge(String dob);

  /**
   * Get patient
   * 
   * @param familyName patient family name
   * @return the patient
   */
  Patient getPatient(String familyName);

}
