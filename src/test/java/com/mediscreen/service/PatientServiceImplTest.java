package com.mediscreen.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import com.mediscreen.proxies.MicroservicePatientProxy;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest()
public class PatientServiceImplTest {

  @Autowired
  private PatientService patientService;

  @MockBean
  private MicroservicePatientProxy microservicePatientProxyMock;

  @Test
  public void testDoesPatientExist() {
    int patientId = 1;
    when(microservicePatientProxyMock.doesPatientExist(patientId)).thenReturn(true);

    boolean result = patientService.doesPatientExist(patientId);
    assertTrue(result);
  }

  @Test
  public void testGetPatientAge() {
    int result = patientService.getPatientAge("1988-06-16", LocalDate.of(2021, 8, 13));
    assertThat(result).isEqualTo(33);
  }

}
