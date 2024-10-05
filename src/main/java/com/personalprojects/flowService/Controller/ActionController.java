package com.personalprojects.flowService.Controller;


import com.personalprojects.flowService.Service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/action")
public class ActionController {

    @Autowired
    ActionService actionService;

    @PostMapping("{flowId}/initiate")
    private ResponseEntity<?> performAction (@PathVariable UUID flowId) {
        try{
           Object res =  actionService.initiateAction(flowId);
            return new ResponseEntity<>(res,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("{flowId}/complete/{currentTaskId}")
    private ResponseEntity<?> continueToNextTask(@PathVariable UUID flowId,
                                                 @PathVariable String currentTaskId,
                                                 @RequestBody Object payload){
        try{
            actionService.moveNext(flowId,currentTaskId,payload);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException("Not able to continue to next task");
        }
    }

}
