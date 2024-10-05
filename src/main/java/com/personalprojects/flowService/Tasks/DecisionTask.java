package com.personalprojects.flowService.Tasks;

import com.personalprojects.flowService.Model.TaskDefinition;

class DecisionTask extends TaskDefinition {

  ResolverMeta meta;

  Boolean taskKeyIfSatisfied;
  Boolean taskKeyIfNotSatisfied;

}
