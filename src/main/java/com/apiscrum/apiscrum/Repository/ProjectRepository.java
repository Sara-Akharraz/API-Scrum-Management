package com.apiscrum.apiscrum.Repository;

import com.apiscrum.apiscrum.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p WHERE p.productBackLog.id = :id")
    Optional<Project> getProjectByProductBackLog(@Param("id") Long id);
}