package com.personalprojects.flowService.Controller;


import com.personalprojects.flowService.Model.Flow;
import com.personalprojects.flowService.Repository.FlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@RestController
public class Test {


    @Autowired
    FlowRepository flowRepository;

//    @PostMapping
//    public String postTest(@RequestBody TaskDefinition taskDefinition){
//        taskDefinitonRepositiry.save(taskDefinition);
//        return "SOMETHING";
//    }

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
