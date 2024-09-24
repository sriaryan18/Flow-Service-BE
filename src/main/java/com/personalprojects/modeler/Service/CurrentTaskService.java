package com.personalprojects.modeler.Service;


import com.personalprojects.modeler.Model.TaskTypes;
import com.personalprojects.modeler.Pojos.TaskDefinition;
import com.personalprojects.modeler.Pojos.Tasks.HumanTask;
import org.springframework.beans.factory.annotation.Autowired;
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
