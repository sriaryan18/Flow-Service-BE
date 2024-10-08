package com.personalprojects.flowService.Coltrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flow-mgmt")
public class Flow {

  @PostMapping("/initiate/{flowName}")
  public void startANewFlow(@PathVariable String flowName) {

  }
}
