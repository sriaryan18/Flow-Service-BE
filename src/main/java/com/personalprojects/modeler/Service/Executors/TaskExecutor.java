package com.personalprojects.modeler.Service.Executors;

import java.util.List;

import com.personalprojects.modeler.Model.TaskDefinition;

/**
 * TaskExecutor
 */
public abstract class TaskExecutor<T extends TaskDefinition> {

  public abstract Object executeTask(T task, Object payload);

  public abstract Object executePreConditions(List<String> preConditions);

}
