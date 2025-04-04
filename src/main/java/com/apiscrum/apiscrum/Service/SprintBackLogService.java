package com.apiscrum.apiscrum.Service;

import com.apiscrum.apiscrum.Entity.SprintBackLog;
import com.apiscrum.apiscrum.Entity.Task;
import com.apiscrum.apiscrum.Entity.TestAcceptance;
import com.apiscrum.apiscrum.Entity.UserStory;

import java.util.List;

public interface SprintBackLogService {
    public SprintBackLog addSprintBackLog(SprintBackLog sprintBackLog);
    public SprintBackLog updateSprintBackLog(Long id,SprintBackLog UpdatedSprintBacklog);
    public void deleteSprintBackLog(Long id);
    public List<Task> getToDoTasks(Long SprintBackLog_id);
    public List<UserStory> getUserStoriesAssociated(Long SprintBackLog_id);
    public List<Task> getInProgressTasks(Long SprintBackLog_id);
    public List<Task> getBlockedTasks(Long SprintBackLog_id);
    public List<Task> getDoneTasks(Long SprintBackLog_id);
    public List<TestAcceptance> getPassedTests(Long SprintBackLog_id);
    public List<TestAcceptance> getPendingTests(Long SprintBackLog_id);
    public List<TestAcceptance> getFailedTests(Long SprintBackLog_id);
    public SprintBackLog getSprintBackLogById(Long id);
}
