package com.personalprojects.modeler.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalprojects.modeler.Model.Flow;
import com.personalprojects.modeler.Model.TaskDefinition;
import com.personalprojects.modeler.Repository.FlowRepository;
import com.personalprojects.modeler.Repository.TaskDefinitonRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ActionService {

    @Autowired
    FlowRepository flowRepository;

    @Autowired
    TaskDefinitonRepositiry taskDefinitonRepositiry;

    @Autowired
    CurrentTaskService currentTaskService;

    public void initiateAction(UUID flowId) throws Exception {
        Optional<Flow> flowOptional = flowRepository.findById(flowId);
        if(flowOptional.isPresent()){
            Flow flow = flowOptional.get();
            JsonNode currentTaskDetails = flow.getCurrentTaskDetails();
            if(currentTaskDetails == null){
                String startTaskKey = flow.getStartTaskKey();

                updateCurrentTaskInFlow(flow,startTaskKey);
            }
            flowRepository.save(flow);
        }
    }

    private void updateCurrentTaskInFlow(Flow flow,String taskKey) throws RuntimeException {
        taskDefinitonRepositiry.findByTaskDefinitionName(taskKey)
                .ifPresentOrElse((definition)->{
                    ObjectMapper objectMapper = new ObjectMapper();
                    HashMap<String,String> currentTaskDetails =  new HashMap<>();
                    currentTaskDetails.put("taskDefinitionName",definition.getTaskDefinitionName());
                    flow.setCurrentTaskDetails(objectMapper.valueToTree(currentTaskDetails));

                },()->{
                    throw new RuntimeException("Task Definition now found");
                });
    }

    public void moveNext(UUID flowId, String currentTaskName, Object payload){
        Optional<Flow> flowOptional =  flowRepository.findById(flowId);
        flowOptional.ifPresentOrElse((flowInst)->{
            JsonNode currentTask =flowInst.getCurrentTaskDetails();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                TaskDefinition currentTaskDefinition = objectMapper.treeToValue(currentTask, TaskDefinition.class);
                boolean isTaskCompleted = currentTaskService.performAction(currentTaskDefinition,payload);
                if(isTaskCompleted){

                fetchDetailsOfNextTask(flowInst,currentTaskName);
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        },()->{
            throw  new RuntimeException("Flow not found");
        });

    }

    private void fetchDetailsOfNextTask(Flow flow, String currentTaskName){
        List<Map<String,Object>> allTasks =  flow.getAllTasks();
        int nextTaskIdx = -1;
        String nextTaskKey = null;
        for(int i = 0;i<allTasks.size();i++){
            Map<String,Object> task = allTasks.get(i);
            String taskName = (String) task.get("taskName");
            if(Objects.equals(taskName, currentTaskName)){
                task.put("status",true);
                nextTaskIdx = i+1;
            }
            if(nextTaskIdx == i){
                nextTaskKey = taskName;
                break;
            }
        }
        if(nextTaskKey != null){
            updateCurrentTaskInFlow(flow,nextTaskKey);
        }


    }

}
