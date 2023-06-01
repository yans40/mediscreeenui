package com.openclassrooms.clientmediscreenui.proxies;

import com.openclassrooms.clientmediscreenui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-patients", url="localhost:8081/apipatient" )
public interface MicroservicePatientsProxy {
@GetMapping(value = "/patients")
    List<PatientBean> listDesPatients();

@GetMapping("/patients/{id}")
    PatientBean recupererUnPatient(@PathVariable("id") Long id);

}
