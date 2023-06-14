package com.openclassrooms.clientmediscreenui.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-diabeteassessment", url="localhost:8083/apiDiabeteAssessment" )
public interface MicroserviceDiabeteAssessmentProxy {

    @GetMapping("/evaluerRisque/{patientId}")
    public String evaluerRisque(@PathVariable("patientId") Long patientId);
}
