package com.openclassrooms.clientmediscreenui.proxies;


import com.openclassrooms.clientmediscreenui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservice-notes", url="localhost:8082/apiNote" )
public interface MicroserviceNotesProxy {
    @GetMapping(value = "/notes")
    List<NoteBean> listDesNotes();

    @GetMapping("/notes/{patientId}")
    NoteBean getNotesByPatientId( @PathVariable Long patientId);// a implementer dans apiNotes

    @PostMapping("/notes")
    void saveNote(@RequestBody NoteBean notetosave);
}
