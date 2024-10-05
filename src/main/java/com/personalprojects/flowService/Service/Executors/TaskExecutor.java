package com.personalprojects.flowService.Service.Executors;

import java.util.List;

import com.personalprojects.flowService.Model.TaskDefinition;

/**
 * TaskExecutor
 */
public abstract class TaskExecutor<T extends TaskDefinition> {

  public abstract Object executeTask(T task, Object payload);

  public abstract Object executePreConditions(List<String> preConditions);

}
