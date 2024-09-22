package com.personalprojects.modeler.Service;


import com.personalprojects.modeler.Model.Flow;
import com.personalprojects.modeler.Model.TaskDefinition;
import com.personalprojects.modeler.Pojos.HumanTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentTaskService {

    @Autowired
    HumanTask humanTask;

    public boolean performAction(TaskDefinition currentTaskDefinition, Object payload){
        if(currentTaskDefinition instanceof HumanTask)
        {
           return humanTask.completeTask(payload);
        }
return  false;

    }
}
