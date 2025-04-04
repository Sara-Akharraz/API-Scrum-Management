package com.apiscrum.apiscrum.Repository;

import com.apiscrum.apiscrum.Entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint,Long> {
    List<Sprint> findByAssociatedProjectId(Long id);
}
