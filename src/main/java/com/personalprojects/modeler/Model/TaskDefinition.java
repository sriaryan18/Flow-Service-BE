
package com.personalprojects.modeler.Model;

import java.util.List;
import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity(name = "task_definition")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskDefinition {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID Id;

  @Column(unique = true)
  String name;

  private List<String> preConditions;

  private String assignee;

  private String type;

  private String flowName;

  private Boolean isRequired = false;

  private Boolean isBlocking = true;

}
