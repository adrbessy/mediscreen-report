package com.mediscreen.service;

import com.mediscreen.model.Note;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TriggerServiceImpl implements TriggerService {

  /**
   * Count the trigger number in all notes
   * 
   * @param notes the notes of a patient
   * @return the number of triggers
   */
  @Override
  public int countTrigger(List<Note> notes) {
    int triggerCount = 0;

    String[][] triggers = {
        { "Anticorps", " Antibodies" },
        { "Anormal", "Anormale", "abnormal" },
        { "Cholestérol", "Cholesterol" },
        { "Fumeur", "fume", "Smoker", "smoke" },
        { "Hémoglobine A1C", "Hemoglobin A1C" },
        { "Microalbumine" },
        { "Poids", "Weight" },
        { "Réaction", "Reaction" },
        { "Rechute", "relapse" },
        { "Taille", "Height" },
        { "Vertige", "Dizziness" }
    };

    String noteToStream = notes.stream()
        .map(Note::getNote)
        .map(String::trim)
        .collect(Collectors.joining(" "));
    System.out.println("noteToStream : " + noteToStream);


    for (int i = 0; i < triggers.length; i++) {
      for (int j = 0; j < triggers[i].length; j++) {
        if (noteToStream.toLowerCase().contains(triggers[i][j].toLowerCase())) {
          System.out.println("Trigger : " + triggers[i][j]);
          triggerCount++;
          break;
        }
      }
    }
    ;

    return triggerCount;
  }

}
