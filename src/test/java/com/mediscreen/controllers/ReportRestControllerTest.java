package com.mediscreen.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.mediscreen.model.Note;
import com.mediscreen.service.ReportService;
import java.util.ArrayList;
import java.util.List;
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

  @Test
  public void testGetNotes() throws Exception {
    Note note = new Note();
    note.setPatientId(1);
    note.setNote("Plant diet is necessary!");
    List<Note> noteList = new ArrayList<>();
    noteList.add(note);
    /*
     * when(noteServiceMock.getNotes(1)).thenReturn(noteList);
     * when(noteServiceMock.doesPatientExist(1)).thenReturn(true);
     */
    mockMvc
        .perform(MockMvcRequestBuilders.get("/notes?patientId=1"))
        .andExpect(status().isOk());
  }


}
