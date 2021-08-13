package com.mediscreen.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.mediscreen.service.PatientService;
import com.mediscreen.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ReportService reportServiceMock;
  @MockBean
  private PatientService patientServiceMock;

  @Test
  public void testGenerateReport() throws Exception {
    int patientId = 1;
    String report = "Patient: Adrien Bessy (age 22) diabetes assessment is: In Danger";

    when(patientServiceMock.doesPatientExist(patientId)).thenReturn(true);
    when(reportServiceMock.generateReport(patientId)).thenReturn(report);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/assess?patientId=1"))
        .andExpect(status().isOk());
  }


}
