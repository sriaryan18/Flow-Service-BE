package com.personalprojects.modeler.Pojos;


import com.fasterxml.jackson.databind.JsonNode;
import com.personalprojects.modeler.Model.TaskTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public    class TaskDefinition {


    private UUID id;

    private String taskDefinitionName;


    private String assignee;


    private JsonNode definition;


    private TaskTypes taskType;

    private Boolean status = false;
    private List<PreConditions> preConditions = new ArrayList<>();
    private boolean isBlocking =false;

    public   boolean completeTask(Object payload){
        return false;
    };

    public   void performPreConditions(List<PreConditions> preConditions){};
    public Object getStepsToPerform(){return null;}

}
