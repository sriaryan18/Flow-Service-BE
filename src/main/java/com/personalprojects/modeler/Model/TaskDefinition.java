package com.personalprojects.modeler.Model;


import com.fasterxml.jackson.databind.JsonNode;
import com.personalprojects.modeler.Pojos.PreConditions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_definition")
@Entity

public abstract  class TaskDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String taskDefinitionName;


    private String assignee;


     @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode definition;

    @NonNull
    private TaskTypes taskType;


    private List<PreConditions> preConditions;

@ColumnDefault("false")
    private boolean isBlocking =false;

    public abstract Object getStepsToPerform();

    public abstract boolean completeTask(Object payload);

    public abstract void performPreConditions(List<PreConditions> preConditions);

}
