package com.personalprojects.modeler.Pojos.Tasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalprojects.modeler.Pojos.FormPojo;
import com.personalprojects.modeler.Pojos.PreConditions;
import com.personalprojects.modeler.Pojos.TaskDefinition;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter

public class HumanTask extends TaskDefinition {

    private FormPojo formElements;

    /**
     * check if form is submitted (so expect a payload)
     * if all ok with payload save it as well
     * check if any pre-condition is there
     * perform that
     * @return
     */
    @Override
    public Object getStepsToPerform() {
       if(this.formElements != null){
           return this.formElements;
       }else {
           return null;
       }
    }



    @Override
    public boolean completeTask(Object payload) {
        ObjectMapper objectMapper  = new ObjectMapper();
        FormPojo payloadForm= objectMapper.convertValue(payload,FormPojo.class);
        List<PreConditions> preConditions = getPreConditions();
        if(!preConditions.isEmpty()){
            performPreConditions(preConditions);
        }
        this.setFormElements(payloadForm);

       // TODO: send the payload to dataLayer
        System.out.println("Sent data to data layer");
        return true;
    }

    public void performPreConditions(List<PreConditions> preConditions){
        System.out.println("Pre conditions performed");
    }
}
