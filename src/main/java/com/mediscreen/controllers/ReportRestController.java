package com.mediscreen.controllers;

import com.mediscreen.model.Report;
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


  /**
   * Generate a report
   * 
   * @param patientId A patient id
   * @return report
   */
  @CrossOrigin
  @GetMapping("/assess")
  public Report generateReport(@RequestParam int patientId) {
    logger.info("GET request with the endpoint 'assess'");
    reportService.doesPatientExist(patientId);
    Report report = reportService.generateReport(patientId);
    logger.info(
        "response following the Get on the endpoint 'assess' with the given patientId : {"
            + patientId + "}");
    return report;
  }

}