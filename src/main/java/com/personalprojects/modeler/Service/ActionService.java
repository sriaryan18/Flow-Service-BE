package com.personalprojects.modeler.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalprojects.modeler.Model.Flow;
import com.personalprojects.modeler.Model.TaskDefinition;
import com.personalprojects.modeler.Repository.FlowRepository;
import com.personalprojects.modeler.Repository.TaskDefinitonRepositiry;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActionService {

    @Autowired
    FlowRepository flowRepository;

    @Autowired
    TaskDefinitonRepositiry taskDefinitonRepositiry;

    public void handleActionComplete(UUID flowId) throws Exception {
        Optional<Flow> flowOptional = flowRepository.findById(flowId);
        if(flowOptional.isPresent()){
            Flow flow = flowOptional.get();
            JsonNode currentTaskDetails = flow.getCurrentTaskDetails();
            if(currentTaskDetails == null){
                startNewFlow(flow);
            }
            flowRepository.save(flow);
        }
    }

    private void startNewFlow(Flow flow) throws RuntimeException {
        String startTaskKey = flow.getStartTaskKey();
        taskDefinitonRepositiry.findByTaskDefinitionName(startTaskKey)
                .ifPresentOrElse((definition)->{
                    ObjectMapper objectMapper = new ObjectMapper();
                    HashMap<String,String> currentTaskDetails =  new HashMap<>();
                    currentTaskDetails.put("taskDefinitionName",definition.getTaskDefinitionName());
                    flow.setCurrentTaskDetails(objectMapper.valueToTree(currentTaskDetails));;
                },()->{
                    throw new RuntimeException("Task Definition now found");
                });
    }
}
