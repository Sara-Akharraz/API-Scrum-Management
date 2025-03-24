package com.apiscrum.APIScrum.Repository;

import com.apiscrum.APIScrum.Entity.SprintBackLog;
import com.apiscrum.APIScrum.Entity.Task;
import com.apiscrum.APIScrum.Entity.Test_Acceptance;
import com.apiscrum.APIScrum.Entity.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintBackLogRepository extends JpaRepository<SprintBackLog,Long> {
    @Query("SELECT sb.userStoriesList FROM SprintBackLog sb WHERE sb.id = :id")
    List<UserStory> findUserStoriesBySprintBackLogId(@Param("id") Long id);

    @Query("SELECT t FROM Task t WHERE t.associatedUserStory IN (SELECT us FROM SprintBackLog sb JOIN sb.userStoriesList us WHERE sb.id = :id) AND t.progress = 'TO_DO'")
    List<Task> findToDoTasksBySprintBackLogId(@Param("id") Long id);

    @Query("SELECT t FROM Task t WHERE t.associatedUserStory IN (SELECT us FROM SprintBackLog sb JOIN sb.userStoriesList us WHERE sb.id = :id) AND t.progress = 'IN_PROGRESS'")
    List<Task> findInProgressTasksBySprintBackLogId(@Param("id") Long id);

    @Query("SELECT t FROM Task t WHERE t.associatedUserStory IN (SELECT us FROM SprintBackLog sb JOIN sb.userStoriesList us WHERE sb.id = :id) AND t.progress = 'BLOCKED'")
    List<Task> findBlockedTasksBySprintBackLogId(@Param("id") Long id);

    @Query("SELECT t FROM Task WHERE t.associatedUserStory IN (SELECT us FROM SprintBackLog sb JOIN sb.userStoriesList us WHERE sb.id= :id) AND t.progress='DONE'")
    List<Task> findDoneTaskBySprintBackLogId(@Param("id") Long id);

    @Query("SELECT t FROM Test_Acceptance WHERE t.associatedUserStory IN(SELECT us FROM SprintBackLog sb JOIN sb.userStoriesList us WHERE sb.id=:id)AND t.state='Passed")
    List<Test_Acceptance> findPassedTestBySprintBackLogId(@Param("id") Long id);
    @Query("SELECT t FROM Test_Acceptance WHERE t.associatedUserStory IN(SELECT us FROM SprintBackLog sb JOIN sb.userStoriesList us WHERE sb.id=:id)AND t.state='Pending")
    List<Test_Acceptance> findPendingTestBySprintBackLogId(@Param("id") Long id);
    @Query("SELECT t FROM Test_Acceptance WHERE t.associatedUserStory IN(SELECT us FROM SprintBackLog sb JOIN sb.userStoriesList us WHERE sb.id=:id)AND t.state='Failed")
    List<Test_Acceptance> findFailedTestBySprintBackLogId(@Param("id") Long id);

}
