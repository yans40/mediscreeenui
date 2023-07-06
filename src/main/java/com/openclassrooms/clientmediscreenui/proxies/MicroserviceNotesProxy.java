package com.openclassrooms.clientmediscreenui.proxies;


import com.openclassrooms.clientmediscreenui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-notes", url="${API_NOTE_URL}" )
public interface MicroserviceNotesProxy {
    @GetMapping(value = "/apiNote/notes")
    List<NoteBean> listDesNotes();

    @GetMapping(value = "/apiNote/notesList/{patientId}")
   List <NoteBean> getNotesByPatientId( @PathVariable Long patientId);

    @GetMapping(value = "/apiNote/notes/{id}")
    NoteBean getNotesById(@PathVariable String id);

    @PostMapping(value = "/apiNote/notes")
    void saveNote(@RequestBody NoteBean note);

    @PutMapping(value = "/apiNote/notes/{id}")
    NoteBean updateNote(@PathVariable String id, @RequestBody NoteBean updatedNote);

    @DeleteMapping(value = "/apiNote/notes/{id}")
    void deleteNote(@PathVariable String id);
}
