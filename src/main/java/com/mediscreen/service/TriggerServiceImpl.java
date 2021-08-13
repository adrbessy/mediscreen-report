package com.mediscreen.service;

import com.mediscreen.model.Note;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TriggerServiceImpl implements TriggerService {

  @Override
  public int countTrigger(List<Note> notes) {
    int triggerCount = 0;
    List<String> triggerWords = Arrays.asList(
        "Hémoglobine A1C",
        "Microalbumine",
        "Taille",
        "Poids",
        "Fumeur",
        "Anormal",
        "Cholestérol",
        "Vertige",
        "Rechute",
        "Réaction",
        "Anticorps");
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
