package com.personalprojects.flowService.Service;


import com.personalprojects.flowService.Model.TaskDefinition;
import com.personalprojects.flowService.Model.TaskTypes;
import org.springframework.stereotype.Service;

@Service
public class CurrentTaskService {



    public Object performAction(TaskDefinition currentTaskDefinition, Object payload){
        if(currentTaskDefinition.getTaskType().equals(TaskTypes.HUMAN))
        {
            return currentTaskDefinition.getStepsToPerform();
        }
return  false;

    }
}
5