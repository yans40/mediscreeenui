package com.openclassrooms.clientmediscreenui.controller;

import com.openclassrooms.clientmediscreenui.beans.NoteBean;
import com.openclassrooms.clientmediscreenui.beans.PatientBean;
import com.openclassrooms.clientmediscreenui.proxies.MicroserviceDiabeteAssessmentProxy;
import com.openclassrooms.clientmediscreenui.proxies.MicroserviceNotesProxy;
import com.openclassrooms.clientmediscreenui.proxies.MicroservicePatientsProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Slf4j
@Controller
public class mediscreenUiController {
    @Autowired
    MicroserviceNotesProxy notesProxy;

    @Autowired
    MicroserviceDiabeteAssessmentProxy diabeteAssessmentProxy;
    @Autowired
    MicroservicePatientsProxy patientsProxy;


    @RequestMapping("/")
    public String accueilDisplay(Model model) {
        List<PatientBean> patients = patientsProxy.listDesPatients();

        Map<Long, String> assessmentResults = new HashMap<>();
        for (PatientBean patientBean : patients) {
            String assessmentResult = riskEvaluator(patientBean.getId());
            assessmentResults.put(patientBean.getId(), assessmentResult);
        }

        model.addAttribute("assessmentResults", assessmentResults);
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
    public String showPatientupdateForm(@PathVariable(name = "id") Long id, Model model) {
        PatientBean patientBean = patientsProxy.recupererUnPatient(id);
        model.addAttribute("patient", patientBean);
        log.info("dirige vers le formulaire de mise à jour");
        return "update";
    }

    @PostMapping("/patient/{id}")
    public String updatePatient(@PathVariable(name = "id") Long id, PatientBean updatedPatient, RedirectAttributes ra) {
        patientsProxy.updatePatient(id, updatedPatient);
        ra.addFlashAttribute("message", "les informations de votre patient ont été mises à jour!");
        log.info("maj transmise");
        return "redirect:/";
    }

    @GetMapping("/editNote/{id}")
    public String showNoteUpdateForNm(@PathVariable(name = "id") String id, Model model) {
        NoteBean noteBean = notesProxy.getNotesById(id);
        model.addAttribute("note", noteBean);
        return "updateNote";
    }

    @PostMapping("/notes/{id}")
    public String updateNote(@PathVariable(name = "id") String id, NoteBean noteBean) {
        notesProxy.updateNote(id, noteBean);
        return "redirect:/addNote/" + noteBean.getPatientId();
    }

    @GetMapping("addNote/{id}")
    public String showPatientNoteForm(@PathVariable(name = "id") Long id, Model model) {
        NoteBean noteBean = new NoteBean();
        PatientBean patientBean = patientsProxy.recupererUnPatient(id);
        // Récupérer les anciennes notes du patient à partir de l'API Note
        List<NoteBean> oldNotes = notesProxy.getNotesByPatientId(id);

        model.addAttribute("patient", patientBean);
        model.addAttribute("noteBean", noteBean);
        model.addAttribute("oldNotes", oldNotes);

        return "noteForm";
    }

    @PostMapping("addNote/{id}")
    public String saveNote(@PathVariable(name = "id") Long patientId, @RequestParam("notes") String observation) {
        // Créez une nouvelle instance de Note avec les données fournies
        NoteBean note = new NoteBean();
        note.setPatientId(patientId);
        note.setObservation(observation);
        note.setDate(new Date());

        // Appelez le service pour persister la note
        notesProxy.saveNote(note);

        return "redirect:/addNote/{id}";
    }

    @GetMapping("evaluatePatientDiabeteRisk")
    public String riskEvaluator(@PathVariable(name = "id") Long patientId) {
        return diabeteAssessmentProxy.evaluerrisque(patientId);

    }

    @GetMapping("deleteNote/{id}")
    public String deleteNote(@PathVariable(name = "id") String id) {
        NoteBean noteBean = notesProxy.getNotesById(id);
        notesProxy.deleteNote(id);
        return "redirect:/addNote/"+ noteBean.getPatientId();
    }

}
