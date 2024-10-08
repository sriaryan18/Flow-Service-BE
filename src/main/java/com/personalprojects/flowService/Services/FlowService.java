package com.personalprojects.flowService.Services;

import com.personalprojects.flowService.Model.FlowInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FlowService {

  @Autowired
  WebClient webClient;

  public void startANewFlow(String flowName) {

  }
}
