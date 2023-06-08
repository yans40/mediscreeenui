package com.openclassrooms.clientmediscreenui.proxies;

import com.openclassrooms.clientmediscreenui.beans.PatientBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-patients", url = "localhost:8081/apipatient")
public interface MicroservicePatientsProxy {
    @GetMapping(value = "/patients")
    List<PatientBean> listDesPatients();

    @GetMapping("/patients/{id}")
    PatientBean recupererUnPatient(@PathVariable("id") Long id);

    @PostMapping("/patients")
    PatientBean createPatient(@RequestBody PatientBean patient);
    @PutMapping("/patients/{id}")
    PatientBean updatePatient(@PathVariable Long id, @RequestBody PatientBean updatedPatient);

}
