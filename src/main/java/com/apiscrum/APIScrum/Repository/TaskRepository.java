package com.apiscrum.APIScrum.Repository;

import com.apiscrum.APIScrum.Entity.Task;
import com.apiscrum.APIScrum.enums.TaskProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> FindByProgress(TaskProgress progress);
    List<Task> FindByUserStoryId(Long user_story_id);
}
