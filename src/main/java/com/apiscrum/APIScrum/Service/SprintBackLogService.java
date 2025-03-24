package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.Entity.SprintBackLog;
import com.apiscrum.APIScrum.Entity.Task;
import com.apiscrum.APIScrum.Entity.Test_Acceptance;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.enums.TaskProgress;

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
    public List<Test_Acceptance> getPassedTests(Long SprintBackLog_id);
    public List<Test_Acceptance> getPendingTests(Long SprintBackLog_id);
    public List<Test_Acceptance> getFailedTests(Long SprintBackLog_id);

}
