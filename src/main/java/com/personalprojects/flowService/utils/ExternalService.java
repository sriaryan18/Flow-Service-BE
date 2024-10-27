package com.personalprojects.flowService.utils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExternalService {
  private String serviceName;
  private String host;
  private List<Map<String, String>> endpoints;
  private String type;

  public ExternalService(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ExternalService(String serviceName, String host, List<Map<String, String>> endpoints, String type) {
    System.out.println("Service Name: " + serviceName);
    this.serviceName = serviceName;
    this.host = host;
    this.endpoints = endpoints;
    this.type = type;
  }

  public ExternalService() {
  }

  public String getServiceName() {
    return this.serviceName;
  }

  public String getHost() {
    return this.host;
  }

  public List<Map<String, String>> getEndpoints() {
    return this.endpoints;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setEndpoints(List<Map<String, String>> endpoints) {
    this.endpoints = endpoints;
  }

  public Optional<String> getEndPointByName(String name){
    return this.endpoints.stream()
            .filter(endpoint -> name.equalsIgnoreCase(endpoint.get("name")))
            .map(endpoint -> endpoint.get("endpoint"))
            .findFirst();
  }

}
