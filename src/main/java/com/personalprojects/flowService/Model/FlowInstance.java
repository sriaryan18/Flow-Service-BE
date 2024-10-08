package com.personalprojects.flowService.Model;


import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Table(name = "flow_instance")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FlowInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String flowName;

    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode currentTaskDetails;
    JsonNode definition;

    String assignee;



    LocalDateTime startedAt;


    LocalDateTime lastUpdatedAt;


    @PrePersist
    public void perPersist(){
        startedAt = LocalDateTime.now();
        lastUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void postPersist(){
        lastUpdatedAt = LocalDateTime.now();
    }




}
