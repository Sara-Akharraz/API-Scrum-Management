package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.Entity.Task;
import com.apiscrum.apiscrum.Repository.TaskRepository;
import com.apiscrum.apiscrum.Service.TaskService;
import com.apiscrum.apiscrum.enums.TaskProgress;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private  TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }
    @Override
    @Transactional
    public Task addTask(Task task) {
       return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task updateTask(Long id, Task UpdatedTask) {
        Optional<Task> prevTask =taskRepository.findById(id);
        if(prevTask.isPresent()){
            Task task=prevTask.get();
            task.setTask_title(UpdatedTask.getTask_title());
            task.setDescription(UpdatedTask.getDescription());
            task.setTag(UpdatedTask.getTag());
            task.setProgress(UpdatedTask.getProgress());
            task.setAssociatedUserStory(UpdatedTask.getAssociatedUserStory());
            return taskRepository.save(task);
        }
        else{
            throw new EntityNotFoundException("Task not found for id :" +id);
        }
    }

    @Override
    public void deleteTask(Long id) {
      if(taskRepository.existsById(id)){
          taskRepository.deleteById(id);
      }else{
          throw new EntityNotFoundException("Task not found for id :" +id);
      }

    }

    @Override
    public Task updateTaskProgress(Long id, TaskProgress updatedProgress) {
        Optional<Task> prevTask=taskRepository.findById(id);
        if(prevTask.isPresent()){
            Task task=prevTask.get();
            task.setProgress(updatedProgress);
           return taskRepository.save(task);
        }
        else{
            throw new EntityNotFoundException("Task not found for id :" + id);
        }
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Task not found with id :" + id));
    }
}
