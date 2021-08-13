package com.mediscreen.service;

import java.time.LocalDate;

public interface PatientService {

  boolean doesPatientExist(Integer patientId);

  int getPatientAge(String dob, LocalDate currentDate);

}
