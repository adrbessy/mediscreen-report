package com.mediscreen.service;

import com.mediscreen.model.Note;
import java.util.List;

public interface TriggerService {

  /**
   * Count the trigger number in all notes
   * 
   * @param notes the notes of a patient
   * @return the number of triggers
   */
  int countTrigger(List<Note> notes);

}
