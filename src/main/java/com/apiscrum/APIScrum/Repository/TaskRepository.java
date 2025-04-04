package com.apiscrum.apiscrum.Repository;

import com.apiscrum.apiscrum.Entity.Task;
import com.apiscrum.apiscrum.enums.TaskProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByAssociatedUserStoryId(Long userStoryId);
}
