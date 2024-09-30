package com.personalprojects.modeler.Service.Executors;

import java.util.List;

import org.springframework.stereotype.Component;

import com.personalprojects.modeler.Tasks.HumanTask;

/**
 * HumanTaskExecutor
 */
@Component
public class HumanTaskExecutor extends TaskExecutor<HumanTask> {

  @Override
  public Object executeTask(HumanTask humanTask, Object payload) {

    Boolean isPreConditionSatisfied = (Boolean) this.executePreConditions(humanTask.getPreConditions());
    if (isPreConditionSatisfied) {
      return humanTask.getFormElements();
    }
    throw new Error("Human task could not be executed due to pre conditions not met");
  }

  @Override
  public Object executePreConditions(List<String> preConditions) {
    return true;
  }

}
