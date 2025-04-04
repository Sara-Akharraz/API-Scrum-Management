package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.Entity.Task;
import com.apiscrum.apiscrum.Entity.User;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Repository.TaskRepository;
import com.apiscrum.apiscrum.enums.Role;
import com.apiscrum.apiscrum.enums.TaskProgress;
import com.apiscrum.apiscrum.enums.TaskTags;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;


    private Task task;
    private User user =new User(1L,"sara","sara@gmail.com","123",Role.DEVELOPER);
    private User user2=new User(2L,"test","test@gmail.com","123",Role.DEVELOPER);
    private UserStory userStory=new UserStory();
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        task=new Task(1L,"dark mode","description", TaskProgress.In_Progress,user,TaskTags.FrontEnd,userStory);
    }
    @Test
    public void addTaskTest(){
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        Task createdtask=taskService.addTask(task);

        assertNotNull(createdtask);
        assertEquals("dark mode",createdtask.getTask_title());
    }
    @Test
    public void getTaskByIdTest(){
       when(taskRepository.findById(1L)).thenReturn((Optional.of(task)));
       Task foundTask =taskService.getTaskById(1L);
        assertNotNull(foundTask);
        assertEquals("dark mode",foundTask.getTask_title());
    }
    @Test
    public void deleteTaskTest(){
        when(taskRepository.existsById(1L)).thenReturn(true);
        doNothing().when(taskRepository).deleteById(1L);
        taskService.deleteTask(1L);
        verify(taskRepository,times(1)).deleteById(1L);
    }
    @Test
    public void updateTaskTest(){
      Task updatedTask= new Task(1L,"Light mode","updated description",TaskProgress.In_Progress,user,TaskTags.FrontEnd,userStory);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);
        taskService.updateTask(1L,updatedTask);
        verify(taskRepository,times(1)).save(any(Task.class));
    }

    @Test
    void getAllTaskTest(){
        List<Task> tasks= Arrays.asList(task,new Task(2L, "Another Task", "Another Description", TaskProgress.Blocked, user2, TaskTags.BackEnd, userStory));
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> testTasks=taskService.getAllTasks();
        assertEquals(2,testTasks.size());
        verify(taskRepository,times(1)).findAll();
    }
    @Test
    void testUpdateTaskProgress() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        taskService.updateTaskProgress(1L,TaskProgress.Done);

        assertEquals(TaskProgress.Done, task.getProgress());
        verify(taskRepository, times(1)).save(any(Task.class));
    }


}
