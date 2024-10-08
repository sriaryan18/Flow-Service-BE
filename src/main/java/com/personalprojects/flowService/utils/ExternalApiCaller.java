package com.personalprojects.flowService.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ExternalApiCaller {

  @Autowired
  WebClient webClient;

}
