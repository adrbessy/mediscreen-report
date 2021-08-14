package com.mediscreen.service;

import com.mediscreen.model.Note;
import java.util.Arrays;
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
    List<String> triggerWords = Arrays.asList(
        "Hémoglobine A1C",
        "Hemoglobin A1C",
        "Microalbumine",
        "Taille",
        "Height",
        "Poids",
        "Weight",
        "Fumeur",
        "Smoker",
        "Anormal",
        "Abnormal",
        "Cholestérol",
        "Cholesterol",
        "Vertige",
        "Dizziness",
        "Rechute",
        "relapse",
        "Réaction",
        "reaction",
        "Anticorps",
        "Antibodies");
    String noteToStream = notes.stream()
        .map(Note::getNote)
        .map(String::trim)
        .collect(Collectors.joining(" "));
    System.out.println("noteToStream : " + noteToStream);

    for (String triggerWord : triggerWords) {
      if (noteToStream.toLowerCase().contains(triggerWord.toLowerCase())) {
        System.out.println("triggerWord : " + triggerWord);
        triggerCount++;
      }
    }
    return triggerCount;
  }

}
