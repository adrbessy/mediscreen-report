package com.mediscreen.repositories;

import com.mediscreen.model.Note;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

  List<Note> findByPatientId(final Integer patientId);

}
