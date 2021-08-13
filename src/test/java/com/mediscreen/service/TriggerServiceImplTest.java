package com.mediscreen.service;

import static org.assertj.core.api.Assertions.assertThat;
import com.mediscreen.model.Note;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class TriggerServiceImplTest {

  @Autowired
  private TriggerService triggerService;

  @Test
  public void testCountTrigger() {
    Note note = new Note();
    note.setPatientId(1);
    note.setNote("Poids égal ou inférieur au poids recommandé");
    List<Note> noteList = new ArrayList<>();
    noteList.add(note);

    int result = triggerService.countTrigger(noteList);
    assertThat(result).isEqualTo(1);
  }

}
