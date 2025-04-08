package com.apiscrum.apiscrum.Service;

import com.apiscrum.apiscrum.Entity.Task;
import com.apiscrum.apiscrum.enums.TaskProgress;

import java.util.List;

public interface TaskService {
    public Task addTask(Task task);
    public Task updateTask(Long id, Task UpdatedTask);
    public void deleteTask(Long id);
    public Task updateTaskProgress(Long id, TaskProgress updatedProgress);
    public List<Task> getAllTasks();
    public Task getTaskById(Long id);
}
