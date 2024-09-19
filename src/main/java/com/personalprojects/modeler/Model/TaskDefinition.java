package com.personalprojects.modeler.Model;


import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_definition")
@Entity

public class TaskDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String taskDefinitionName;


    private String assignee;


     @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode definition;

    @NonNull
    private TaskTypes taskType;

}
