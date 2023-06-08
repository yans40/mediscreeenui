package com.openclassrooms.clientmediscreenui.controller;

import com.openclassrooms.clientmediscreenui.beans.PatientBean;
import com.openclassrooms.clientmediscreenui.proxies.MicroservicePatientsProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class mediscreenUiController {
    private final MicroservicePatientsProxy patientsProxy;

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
    public String updatePatient(@PathVariable("id") Long id, PatientBean updatedPatient,RedirectAttributes ra) {
        patientsProxy.updatePatient(id, updatedPatient);
        ra.addFlashAttribute("message", "les informations de votre patient ont été mises à jour!");
        log.info("maj transmise");
        return "redirect:/";
    }
}
