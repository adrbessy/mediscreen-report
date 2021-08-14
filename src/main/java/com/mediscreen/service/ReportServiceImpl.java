package com.mediscreen.service;

import com.mediscreen.model.Note;
import com.mediscreen.model.Patient;
import com.mediscreen.proxies.MicroserviceNoteProxy;
import com.mediscreen.proxies.MicroservicePatientProxy;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

  private static final Logger logger = LogManager.getLogger(ReportServiceImpl.class);

  @Autowired
  private MicroservicePatientProxy microservicePatientProxy;

  @Autowired
  private MicroserviceNoteProxy microserviceNoteProxy;

  @Autowired
  private TriggerService triggerService;

  @Autowired
  private PatientService patientService;

  /**
   * Get the diabete report
   * 
   * @param patientId the patient id
   * @return the diabete report
   */
  @Override
  public String generateReport(int patientId) {
    logger.debug("in the method generateReport in the class ReportServiceImpl");
    Patient patient = microservicePatientProxy.getPatient(patientId);
    List<Note> notes = microserviceNoteProxy.getNotes(patientId);
    int triggerCount = triggerService.countTrigger(notes);
    System.out.println("triggerCount : " + triggerCount);
    int age = patientService.getPatientAge(patient.getDob());
    String sex = patient.getSex();
    String report = createReport(triggerCount, age, sex, patient.getGiven(), patient.getFamily());
    return report;
  }

  /**
   * Create the diabete report
   * 
   * @param triggerCount the trigger number
   * @param age          the patient age
   * @param sex          the patient sex
   * @param given        the patient first name
   * @param family       the patient family name
   * @return the diabete report
   */
  private String createReport(int triggerCount, int age, String sex, String given, String family) {
    logger.debug("in the method createReport in the class ReportServiceImpl");
    String riskLevel = "None";

    if (age < 30) {

      if (sex.equals("M")) {
        if (triggerCount >= 3) {
          riskLevel = "In Danger";
        }
        if (triggerCount >= 5) {
          riskLevel = "Early onset";
        }
      }

      if (sex.equals("F")) {
        if (triggerCount >= 4) {
          riskLevel = "In Danger";
        }
        if (triggerCount >= 7) {
          riskLevel = "Early onset";
        }
      }

    }

    if (age >= 30) {
      if (triggerCount >= 2) {
        riskLevel = "Borderline";
      }
      if (triggerCount >= 6) {
        riskLevel = "In Danger";
      }
      if (triggerCount >= 8) {
        riskLevel = "Early onset";
      }
    }

    return "Patient: " + given + " " + family + " (age " + age + ") diabetes assessment is: " + riskLevel;
  }


}
