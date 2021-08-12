package com.mediscreen.proxies;

import com.mediscreen.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mediscreen-patient", url = "${patient.url.cross}")
public interface MicroservicePatientProxy {

  @GetMapping("/patientExists")
  boolean doesPatientExist(@RequestParam("id") Integer id);

  @GetMapping("/patient")
  Patient getPatient(@RequestParam("id") Integer id);

}
