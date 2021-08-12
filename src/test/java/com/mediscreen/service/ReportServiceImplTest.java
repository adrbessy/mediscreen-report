package com.mediscreen.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import com.mediscreen.proxies.MicroserviceNoteProxy;
import com.mediscreen.proxies.MicroservicePatientProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest()
public class ReportServiceImplTest {

  @Autowired
  private ReportService reportService;

  @MockBean
  private MicroservicePatientProxy microservicePatientProxy;

  @MockBean
  private MicroserviceNoteProxy microserviceNoteProxy;

  @Test
  public void testDoesPatientExist() {
    Integer patientId = 1;

    when(microservicePatientProxy.doesPatientExist(patientId)).thenReturn(true);

    boolean result = reportService.doesPatientExist(patientId);
    assertTrue(result);
  }


}
