package com.mediscreen.service;

import com.mediscreen.model.Note;
import com.mediscreen.model.Patient;
import com.mediscreen.model.Report;
import com.mediscreen.proxies.MicroserviceNoteProxy;
import com.mediscreen.proxies.MicroservicePatientProxy;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

  @Override
  public Report generateReport(int patientId) {
    Patient patient = microservicePatientProxy.getPatient(patientId);
    List<Note> notes = microserviceNoteProxy.getNotes(patientId);
    int triggerCount = CountTrigger(notes);
    System.out.println("triggerCount : " + triggerCount);
    int age = getPatientAge(patient.getDob());
    String sex = patient.getSex();
    Report report = createReport(triggerCount, age, sex);
    return report;
  }

  private Report createReport(int triggerCount, int age, String sex) {
    // TODO Auto-generated method stub
    return null;
  }

  private int getPatientAge(String dob) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate birthdate = LocalDate.parse(dob, formatter);
    LocalDate now = LocalDate.now();
    Period period = Period.between(birthdate, now);
    int age = period.getYears();
    return age;
  }

  private int CountTrigger(List<Note> notes) {
    int triggerCount = 0;
    List<String> triggerWords = Arrays.asList(
        "Hémoglobine A1C",
        "Microalbumine",
        "Taille",
        "Poids",
        "Fumeur",
        "Anormal",
        "Cholestérol",
        "Vertige",
        "Rechute",
        "Réaction",
        "Anticorps");
    String noteToStream = notes.stream()
        .map(Note::getNote)
        .map(String::trim)
        .collect(Collectors.joining(" "));
    System.out.println("noteToStream : " + noteToStream);

    for (String triggerWord : triggerWords) {
      if (noteToStream.toLowerCase().contains(triggerWord.toLowerCase())) {
        System.out.println("triggerWord : " + triggerWord);
        triggerCount++;
      }
    }
    return triggerCount;
  }

  /**
   * Check if the patient id exists
   * 
   * @param patientId patient id
   * @return true if it exists
   */
  @Override
  public boolean doesPatientExist(Integer patientId) {
    logger.debug("in the method doesPatientExist in the class ReportServiceImpl");
    boolean patientExists = microservicePatientProxy.doesPatientExist(patientId);
    return patientExists;
  }


}
