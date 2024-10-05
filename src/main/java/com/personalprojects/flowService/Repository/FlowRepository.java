package com.personalprojects.flowService.Repository;

import com.personalprojects.flowService.Model.Flow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface FlowRepository extends JpaRepository<Flow, UUID> {

}
