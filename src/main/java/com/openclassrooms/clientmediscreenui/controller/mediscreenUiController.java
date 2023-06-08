package com.openclassrooms.clientmediscreenui.controller;

import com.openclassrooms.clientmediscreenui.beans.NoteBean;
import com.openclassrooms.clientmediscreenui.beans.PatientBean;
import com.openclassrooms.clientmediscreenui.proxies.MicroserviceNotesProxy;
import com.openclassrooms.clientmediscreenui.proxies.MicroservicePatientsProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class mediscreenUiController {
    private final MicroservicePatientsProxy patientsProxy;
    @Autowired
    MicroserviceNotesProxy notesProxy;

    public mediscreenUiController(MicroservicePatientsProxy patientsProxy) {
        this.patientsProxy = patientsProxy;
    }

    @RequestMapping("/")
    public String accueilDisplay(Model model) {

        List<PatientBean> patients = patientsProxy.listDesPatients();
        model.addAttribute("patients", patients);
        return "Accueil";
    }

    @GetMapping("/patient/new")
    public String showNewForm(Model model) {
        model.addAttribute("patient", new PatientBean());
        log.info("je vais ajouter un nouveau patient");
        return "patientForm";
    }

    @PostMapping("/patient")
    public String createPatient(PatientBean patientBean, RedirectAttributes ra) {
        patientsProxy.createPatient(patientBean);
        ra.addFlashAttribute("message", "the Patient a été ajouté avec succès!");
        return "redirect:/";
    }

    @GetMapping("/updatePatient/{id}")
    public String showPatientupdateForm(@PathVariable("id") Long id, Model model) {
        PatientBean patientBean = patientsProxy.recupererUnPatient(id);
        model.addAttribute("patient", patientBean);
        log.info("dirige vers le formulaire de mise à jour");
        return "update";
    }

    @PostMapping("/patient/{id}")
    public String updatePatient(@PathVariable("id") Long id, PatientBean updatedPatient, RedirectAttributes ra) {
        patientsProxy.updatePatient(id, updatedPatient);
        ra.addFlashAttribute("message", "les informations de votre patient ont été mises à jour!");
        log.info("maj transmise");
        return "redirect:/";
    }

    @GetMapping("addNote/{id}")
    public String showPatientNoteForm(@PathVariable Long id, Model model) {
        NoteBean noteBean= new NoteBean();
        PatientBean patientBean=patientsProxy.recupererUnPatient(id);
        model.addAttribute("patient",patientBean);
        model.addAttribute("noteBean",noteBean);
        return "noteForm";
    }

    @PostMapping("addNote/{id}")
    public String saveNote(@PathVariable("id") Long patientId, @RequestParam("notes") String notes) {
            // Créez une nouvelle instance de Note avec les données fournies
            NoteBean note = new NoteBean();
            note.setPatientId(patientId);
            note.setNotes(notes);
            note.setDate(new Date());

            // Appelez le service pour persister la note
            notesProxy.saveNote(note);

            // Redirigez vers la page d'accueil ou une autre vue si nécessaire
            return "redirect:/";
    }
}
