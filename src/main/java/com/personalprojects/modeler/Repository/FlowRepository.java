package com.personalprojects.modeler.Repository;

import com.personalprojects.modeler.Model.Flow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface FlowRepository extends JpaRepository<Flow, UUID> {

}
