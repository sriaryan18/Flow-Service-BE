package com.personalprojects.flowService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.personalprojects.flowService.config.ExternalServices;

@SpringBootApplication
@EnableConfigurationProperties(ExternalServices.class)
public class ModelerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ModelerApplication.class, args);
  }

}
