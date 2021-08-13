package com.mediscreen.controllers;

import com.mediscreen.service.PatientService;
import com.mediscreen.service.ReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportRestController {

  private static final Logger logger = LogManager.getLogger(ReportRestController.class);

  @Autowired
  private ReportService reportService;

  @Autowired
  private PatientService patientService;

  /**
   * Generate a report
   * 
   * @param patientId A patient id
   * @return report
   */
  @CrossOrigin
  @GetMapping("/assess")
  public String generateReport(@RequestParam int patientId) {
    logger.info("GET request with the endpoint 'assess'");
    patientService.doesPatientExist(patientId);
    String report = reportService.generateReport(patientId);
    logger.info(
        "response following the Get on the endpoint 'assess' with the given patientId : {"
            + patientId + "}");
    return report;
  }

}
