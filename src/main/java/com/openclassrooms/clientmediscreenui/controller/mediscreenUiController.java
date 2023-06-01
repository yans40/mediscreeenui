package com.openclassrooms.clientmediscreenui.controller;

import com.openclassrooms.clientmediscreenui.beans.PatientBean;
import com.openclassrooms.clientmediscreenui.proxies.MicroservicePatientsProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class mediscreenUiController {
    private final MicroservicePatientsProxy patientsProxy;

    public mediscreenUiController(MicroservicePatientsProxy patientsProxy){
        this.patientsProxy = patientsProxy;
    }

//    private final RestTemplate restTemplate;
//
//    @Autowired
//    public mediscreenUiController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    @RequestMapping("/")
    public String accueilDisplay(Model model) {

        List<PatientBean> patients = patientsProxy.listDesPatients();
        model.addAttribute("patients",patients);
//        String patientUrl = "http://localhost:8081/apipatient/patients";
//        String patientResponse = restTemplate.getForObject(patientUrl, String.class);
//        model.addAttribute("patientResponse", patientResponse);

        return "Accueil";
    }
}
