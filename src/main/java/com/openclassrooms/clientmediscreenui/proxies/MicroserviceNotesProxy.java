package com.openclassrooms.clientmediscreenui.proxies;


import com.openclassrooms.clientmediscreenui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservice-notes", url="${API_NOTE_URL}" )
public interface MicroserviceNotesProxy {
    @GetMapping(value = "/apiNote/notes")
    List<NoteBean> listDesNotes();

    @GetMapping(value = "/apiNote/notesList/{patientId}")
   List <NoteBean> getNotesByPatientId( @PathVariable Long patientId);

    @PostMapping(value = "/apiNote/notes")
    void saveNote(@RequestBody NoteBean note);
}
