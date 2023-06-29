package com.openclassrooms.clientmediscreenui.proxies;

import com.openclassrooms.clientmediscreenui.beans.PatientBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "apipatient", url = "${API_PATIENT_URL}")
public interface MicroservicePatientsProxy {
    @GetMapping(value = "/apipatient/patients")
    List<PatientBean> listDesPatients();

    @GetMapping(value = "/apipatient/patients/{id}")
    PatientBean recupererUnPatient(@PathVariable("id") Long id);

    @PostMapping(value = "/apipatient/patients")
    PatientBean createPatient(@RequestBody PatientBean patient);
    @PutMapping(value = "/apipatient/patients/{id}")
    PatientBean updatePatient(@PathVariable Long id, @RequestBody PatientBean updatedPatient);

}
