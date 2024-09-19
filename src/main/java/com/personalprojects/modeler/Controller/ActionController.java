package com.personalprojects.modeler.Controller;


import com.personalprojects.modeler.Service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/action")
public class ActionController {

    @Autowired
    ActionService actionService;

    @PostMapping("{flowId}/initiate")
    private ResponseEntity<?> performAction (@PathVariable UUID flowId){
        try{
            actionService.handleActionComplete(flowId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

}
