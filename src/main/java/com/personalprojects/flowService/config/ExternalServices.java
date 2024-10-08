package com.personalprojects.flowService.config;

import com.personalprojects.flowService.utils.ExternalService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "external-services")
public class ExternalServices {

  private List<ExternalService> services;

  public ExternalServices(List<ExternalService> services) {
    System.out.println("I am getting init" + services);
    this.services = services;
  }

  public List<ExternalService> getServices() {
    return this.services;
  }

  public void setServices(List<ExternalService> services) {
    this.services = services;
  }

}
