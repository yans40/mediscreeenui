package com.openclassrooms.clientmediscreenui.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "apidiabete", url="${API_DIABETE_URL}" )
public interface MicroserviceDiabeteAssessmentProxy {

    @GetMapping("/apidiabete/evaluerrisque/{patientId}")
    public String evaluerrisque(@PathVariable(name = "patientId") Long patientId);
}
