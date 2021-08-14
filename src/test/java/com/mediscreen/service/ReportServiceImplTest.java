package com.mediscreen.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import com.mediscreen.model.Note;
import com.mediscreen.model.Patient;
import com.mediscreen.proxies.MicroserviceNoteProxy;
import com.mediscreen.proxies.MicroservicePatientProxy;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest()
public class ReportServiceImplTest {

  @Autowired
  private ReportService reportService;

  @MockBean
  private MicroservicePatientProxy microservicePatientProxyMock;

  @MockBean
  private MicroserviceNoteProxy microserviceNoteProxyMock;

  @MockBean
  private TriggerService triggerServiceMock;

  @MockBean
  private PatientService patientServiceMock;

  Integer patientId;
  Patient patient;
  List<Note> noteList;

  @BeforeEach
  private void setUp() {
    patientId = 1;
    patient = new Patient();
    patient.setId(patientId);
    patient.setGiven("Adrien");
    patient.setFamily("Bessy");
    patient.setSex("M");

    Note note = new Note();
    note.setPatientId(1);
    note.setNote("Poids égal ou inférieur au poids recommandé");
    noteList = new ArrayList<>();
    noteList.add(note);

    when(microservicePatientProxyMock.getPatient(patientId)).thenReturn(patient);
    when(microserviceNoteProxyMock.getNotes(patientId)).thenReturn(noteList);
  }

  @Tag("Test for Age inf to 30, M sex and 3 triggers.")
  @Test
  public void testGenerateReportWhenAgeInfTo30AndMSexAndTriggerCountIsEqualTo3() {
    patient.setDob("1991-10-08");

    when(triggerServiceMock.countTrigger(noteList)).thenReturn(3);
    when(patientServiceMock.getPatientAge(patient.getDob())).thenReturn(29);

    String result = reportService.generateReport(patientId);
    assertThat(result).isEqualTo("Patient: Adrien Bessy (age 29) diabetes assessment is: In Danger");
  }

  @Tag("Test for Age inf to 30, M sex and 5 triggers.")
  @Test
  public void testGenerateReportWhenAgeInfTo30AndMSexAndTriggerCountIsEqualTo5() {
    patient.setDob("1991-10-08");

    when(triggerServiceMock.countTrigger(noteList)).thenReturn(5);
    when(patientServiceMock.getPatientAge(patient.getDob())).thenReturn(29);

    String result = reportService.generateReport(patientId);
    assertThat(result).isEqualTo("Patient: Adrien Bessy (age 29) diabetes assessment is: Early onset");
  }

  @Tag("Test for Age inf to 30, F sex and 4 triggers.")
  @Test
  public void testGenerateReportWhenAgeInfTo30AndFSexAndTriggerCountIsEqualTo4() {
    patient.setDob("1991-10-08");
    patient.setSex("F");

    when(triggerServiceMock.countTrigger(noteList)).thenReturn(4);
    when(patientServiceMock.getPatientAge(patient.getDob())).thenReturn(29);

    String result = reportService.generateReport(patientId);
    assertThat(result).isEqualTo("Patient: Adrien Bessy (age 29) diabetes assessment is: In Danger");
  }

  @Tag("Test for Age inf to 30, F sex and 7 triggers.")
  @Test
  public void testGenerateReportWhenAgeInfTo30AndFSexAndTriggerCountIsEqualTo7() {
    patient.setDob("1991-10-08");
    patient.setSex("F");

    when(triggerServiceMock.countTrigger(noteList)).thenReturn(7);
    when(patientServiceMock.getPatientAge(patient.getDob())).thenReturn(29);

    String result = reportService.generateReport(patientId);
    assertThat(result).isEqualTo("Patient: Adrien Bessy (age 29) diabetes assessment is: Early onset");
  }

  @Tag("Test for Age sup to 30 and 2 triggers.")
  @Test
  public void testGenerateReportWhenAgeSupTo30AndTriggerCountIsEqualTo2() {
    patient.setDob("1988-06-16");

    when(triggerServiceMock.countTrigger(noteList)).thenReturn(2);
    when(patientServiceMock.getPatientAge(patient.getDob())).thenReturn(33);

    String result = reportService.generateReport(patientId);
    assertThat(result).isEqualTo("Patient: Adrien Bessy (age 33) diabetes assessment is: Borderline");
  }

  @Tag("Test for Age sup to 30 and 6 triggers.")
  @Test
  public void testGenerateReportWhenAgeSupTo30AndTriggerCountIsEqualTo6() {
    patient.setDob("1988-06-16");

    when(triggerServiceMock.countTrigger(noteList)).thenReturn(6);
    when(patientServiceMock.getPatientAge(patient.getDob())).thenReturn(33);

    String result = reportService.generateReport(patientId);
    assertThat(result).isEqualTo("Patient: Adrien Bessy (age 33) diabetes assessment is: In Danger");
  }

  @Tag("Test for Age sup to 30 and 8 triggers.")
  @Test
  public void testGenerateReportWhenAgeSupTo30AndTriggerCountIsEqualTo8() {
    patient.setDob("1988-06-16");

    when(triggerServiceMock.countTrigger(noteList)).thenReturn(8);
    when(patientServiceMock.getPatientAge(patient.getDob())).thenReturn(33);

    String result = reportService.generateReport(patientId);
    assertThat(result).isEqualTo("Patient: Adrien Bessy (age 33) diabetes assessment is: Early onset");
  }



}
