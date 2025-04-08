package com.apiscrum.apiscrum.Repository;

import com.apiscrum.apiscrum.Entity.SprintBackLog;
import com.apiscrum.apiscrum.Entity.Task;
import com.apiscrum.apiscrum.Entity.TestAcceptance;
import com.apiscrum.apiscrum.Entity.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintBackLogRepository extends JpaRepository<SprintBackLog,Long> {
    @Query("SELECT sb.userStoriesList FROM SprintBackLog sb WHERE sb.id = :id")
    List<UserStory> findUserStoriesBySprintBackLogId(@Param("id") Long id);

    @Query("SELECT t FROM Task t JOIN t.associatedUserStory us JOIN us.sprintBackLog sb WHERE sb.id = :id AND t.progress = 'TO_DO'")
    List<Task> findToDoTasksBySprintBackLogId(@Param("id") Long id);

    @Query("SELECT t FROM Task t JOIN t.associatedUserStory us JOIN us.sprintBackLog sb WHERE sb.id = :id AND t.progress = 'IN_PROGRESS'")
    List<Task> findInProgressTasksBySprintBackLogId(@Param("id") Long id);

    @Query("SELECT t FROM Task t JOIN t.associatedUserStory us JOIN us.sprintBackLog sb WHERE sb.id = :id AND t.progress = 'BLOCKED'")
    List<Task> findBlockedTasksBySprintBackLogId(@Param("id") Long id);

    @Query("SELECT t FROM Task t JOIN t.associatedUserStory us JOIN us.sprintBackLog sb WHERE sb.id = :id AND t.progress = 'DONE'")
    List<Task> findDoneTaskBySprintBackLogId(@Param("id") Long id);

    @Query("SELECT t FROM TestAcceptance t JOIN t.associatedUserStory us JOIN us.sprintBackLog sb WHERE sb.id = :id AND t.state = 'Passed'")
    List<TestAcceptance> findPassedTestBySprintBackLogId(@Param("id") Long id);

    @Query("SELECT t FROM TestAcceptance t JOIN t.associatedUserStory us JOIN us.sprintBackLog sb WHERE sb.id = :id AND t.state = 'Pending'")
    List<TestAcceptance> findPendingTestBySprintBackLogId(@Param("id") Long id);

    @Query("SELECT t FROM TestAcceptance t JOIN t.associatedUserStory us JOIN us.sprintBackLog sb WHERE sb.id = :id AND t.state = 'Failed'")
    List<TestAcceptance> findFailedTestBySprintBackLogId(@Param("id") Long id);


}
