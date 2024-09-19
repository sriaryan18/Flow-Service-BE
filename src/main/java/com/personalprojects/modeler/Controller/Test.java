package com.personalprojects.modeler.Controller;


import com.personalprojects.modeler.Model.Flow;
import com.personalprojects.modeler.Model.TaskDefinition;
import com.personalprojects.modeler.Repository.FlowRepository;
import com.personalprojects.modeler.Repository.TaskDefinitonRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@RestController
public class Test {

    @Autowired
    TaskDefinitonRepositiry taskDefinitonRepositiry;

    @Autowired
    FlowRepository flowRepository;

    @PostMapping
    public String postTest(@RequestBody TaskDefinition taskDefinition){
        taskDefinitonRepositiry.save(taskDefinition);
        return "SOMETHING";
    }

    @PostMapping("/flow")
    public String createFlow(@RequestBody Flow flow){
        flowRepository.save(flow);
        return "SOMETHING";
    }
    @GetMapping
    public String getTest(){
        return "SOMETHING";
    }
}
