
package com.personalprojects.flowService.Tasks;

import com.fasterxml.jackson.databind.JsonNode;
import com.personalprojects.flowService.Model.TaskDefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HumanTask extends TaskDefinition {

  public JsonNode formElements;

}
