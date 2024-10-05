package com.personalprojects.flowService.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalprojects.flowService.Model.Flow;

import com.personalprojects.flowService.Model.TaskDefinition;
import com.personalprojects.flowService.Repository.FlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ActionService {

    @Autowired
    FlowRepository flowRepository;


    @Autowired
    CurrentTaskService currentTaskService;

    public Object initiateAction(UUID flowId) throws Exception {
        Optional<Flow> flowOptional = flowRepository.findById(flowId);
        if(flowOptional.isPresent()){
            Flow flow = flowOptional.get();
            JsonNode currentTaskDetails = flow.getCurrentTaskDetails();
            if(currentTaskDetails.isNull()){
                String startTaskKey = flow.getStartTaskKey();

                updateCurrentTaskInFlow(flow,startTaskKey);

               TaskDefinition currentTaskObj =  flow.getTaskDefinitions().stream().filter(
                        (taskDefinition) -> taskDefinition.getTaskDefinitionName().equals(startTaskKey) )
                        .findFirst().orElse(null);
               return currentTaskService.performAction(currentTaskObj,null);
                }
            flowRepository.save(flow);



        }
        return "Already Initiated";
    }

    private void updateCurrentTaskInFlow(Flow flow,String taskKey) throws RuntimeException {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String,String> currentTaskDetails =  new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        currentTaskDetails.put("startedAt", LocalDateTime.now().format(formatter));
        currentTaskDetails.put("currentTaskDefinitionKey",taskKey);
        currentTaskDetails.put("startedBy","SAMPLEUSER");
        flow.setCurrentTaskDetails(objectMapper.valueToTree(currentTaskDetails));

    }

    public void moveNext(UUID flowId, String currentTaskName, Object payload){

        Optional<Flow> flowOptional =  flowRepository.findById(flowId);
        flowOptional.ifPresentOrElse((flowInst)->{
            JsonNode currentTask =flowInst.getCurrentTaskDetails();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                TaskDefinition currentTaskDefinition = objectMapper.treeToValue(currentTask, TaskDefinition.class);
                Object isTaskCompleted =  currentTaskService.performAction(currentTaskDefinition,payload);
                if(isTaskCompleted != null){

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
        List<TaskDefinition> allTasks =  flow.getTaskDefinitions();
        int nextTaskIdx = -1;
        String nextTaskKey = null;
        for(int i = 0;i<allTasks.size();i++){
            TaskDefinition task = allTasks.get(i);
            String taskName = (String) task.getTaskDefinitionName();
            if(Objects.equals(taskName, currentTaskName)){
                task.setStatus(true);
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
