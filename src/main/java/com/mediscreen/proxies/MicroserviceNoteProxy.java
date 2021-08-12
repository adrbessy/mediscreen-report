package com.mediscreen.proxies;

import com.mediscreen.model.Note;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mediscreen-note", url = "${note.url.cross}")
public interface MicroserviceNoteProxy {

  @GetMapping("/notes")
  List<Note> getNotes(@RequestParam("patientId") int patientId);

}
