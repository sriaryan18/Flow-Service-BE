package com.personalprojects.modeler.Model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "process_flow")
public class Flow {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Nonnull
    private String startTaskKey;

    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode currentTaskDetails;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<Map<String, Object>> allTasks;  // this will be [{taskKey:string, status:boolean}]

}
