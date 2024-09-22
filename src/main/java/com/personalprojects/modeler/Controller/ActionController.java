package com.personalprojects.modeler.Controller;


import com.personalprojects.modeler.Service.ActionService;
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
            actionService.initiateAction(flowId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("{flowId}/complete")
    private ResponseEntity<?> continueToNextTask(@PathVariable UUID flowId,
                                                 @RequestBody String currentTaskId,
                                                 @RequestBody Object payload){
        try{
            actionService.moveNext(flowId,currentTaskId,payload);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException("Not able to continue to next task");
        }
    }

}
