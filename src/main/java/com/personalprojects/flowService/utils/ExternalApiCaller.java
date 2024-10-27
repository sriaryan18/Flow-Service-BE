package com.personalprojects.flowService.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Optional;

@Component
public class ExternalApiCaller {

  @Autowired
  WebClient webClient;

  public <T> T handleApi(ExternalService externalService,
                         Map<String,String> queryParams, Map<String,String> pathVariables
                        ,String nameOfEndPont, Class<T> responseType) {
    String type = externalService.getType();

    switch (type) {
      case "GET":
        return handleGetCall(externalService, nameOfEndPont,queryParams,pathVariables, responseType);

      default:
        break;

    }
    return (T) responseType;
  }

  public <T> T handleGetCall(ExternalService externalService,String nameOfEndPont,
                             Map<String,String> queryParams,Map<String,String> pathVariables,
                             Class<T> responseType){
      Optional<String> endpoint = externalService.getEndPointByName(nameOfEndPont);
      if(endpoint.isPresent()){
          String fullUrl = String.format("%s%s",externalService.getHost(),endpoint.get());
          return webClient.get().uri((uriBuilder -> {
              uriBuilder.path(fullUrl);
              queryParams.forEach(uriBuilder::queryParam);
              return uriBuilder.build(pathVariables);
          }))
          .retrieve()
          .bodyToMono(responseType).block();

      }else{
          throw new Error("No endpoint configured for this");
      }

  }


}
