package com.mediscreen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.mediscreen")
public class MediscreenReportApplication {

  public static void main(String[] args) {
    SpringApplication.run(MediscreenReportApplication.class, args);
  }

}
