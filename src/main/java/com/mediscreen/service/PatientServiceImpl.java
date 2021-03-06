package com.mediscreen.service;

import com.mediscreen.model.Patient;
import com.mediscreen.proxies.MicroservicePatientProxy;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

  private static final Logger logger = LogManager.getLogger(PatientServiceImpl.class);

  @Autowired
  private MicroservicePatientProxy microservicePatientProxy;

  /**
   * Check if the patient id exists
   * 
   * @param patientId patient id
   * @return true if it exists
   */
  @Override
  public boolean doesPatientExist(Integer patientId) {
    logger.debug("in the method doesPatientExist in the class PatientServiceImpl");
    boolean patientExists = microservicePatientProxy.doesPatientExist(patientId);
    return patientExists;
  }

  /**
   * Get patient
   * 
   * @param familyName patient family name
   * @return the patient
   */
  @Override
  public Patient getPatient(String familyName) {
    logger.debug("in the method getPatient in the class PatientServiceImpl");
    Patient patient = microservicePatientProxy.getPatient(familyName);
    return patient;
  }

  /**
   * Get the patient age
   * 
   * @param dob the date of birth
   * @return the age of the patient
   */
  @Override
  public int getPatientAge(String dob) {
    logger.debug("in the method getPatientAge in the class PatientServiceImpl");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate birthdate = LocalDate.parse(dob, formatter);
    Period period = Period.between(birthdate, LocalDate.now());
    int age = period.getYears();
    return age;
  }

}
