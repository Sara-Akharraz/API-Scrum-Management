package com.apiscrum.APIScrum.Repository;

import com.apiscrum.APIScrum.Entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint,Long> {
    List<Sprint> findByProjectId(Long id);
}
