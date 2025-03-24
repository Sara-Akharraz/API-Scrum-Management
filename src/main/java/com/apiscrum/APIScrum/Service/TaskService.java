package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.Entity.Task;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.enums.TaskProgress;
import com.apiscrum.APIScrum.enums.TaskTags;

import java.util.List;

public interface TaskService {
    public Task addTask(Task task);
    public Task updateTask(Long id, Task UpdatedTask);
    public void deleteTask(Long id);
    public Task updateTaskProgress(Long id,TaskProgress updatedProgress);
    public List<Task> getAllTasks();
    public Task getTaskById(Long id);
}
