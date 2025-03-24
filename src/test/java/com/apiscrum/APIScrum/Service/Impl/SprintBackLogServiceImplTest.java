package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.Entity.Sprint;
import com.apiscrum.APIScrum.Entity.SprintBackLog;
import com.apiscrum.APIScrum.Entity.Task;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Repository.SprintBackLogRepository;
import com.apiscrum.APIScrum.Repository.TaskRepository;
import com.apiscrum.APIScrum.Service.SprintBackLogService;
import com.apiscrum.APIScrum.enums.TaskProgress;
import com.apiscrum.APIScrum.enums.TaskTags;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SprintBackLogServiceImplTest {
    @Mock
    private SprintBackLogRepository sprintBackLogRepository;

    @InjectMocks
    private SprintBackLogServiceImpl sprintBackLogService;

    private SprintBackLog sprintBackLog;
    private Sprint sprint = new Sprint();
    private List<UserStory> us = new ArrayList<>();
    private List<UserStory> us2 = new ArrayList<>();



    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        sprintBackLog = new SprintBackLog(1l, us, sprint);
    }

    @Test
    public void addSprintBackLogTest() {
        when(sprintBackLogRepository.save(any(SprintBackLog.class))).thenReturn(sprintBackLog);
        SprintBackLog createdSprintBackLog = sprintBackLogService.addSprintBackLog(sprintBackLog);

        assertNotNull(createdSprintBackLog);
        assertEquals(1L, createdSprintBackLog.getId());
    }

    @Test
    public void updateSprintBackLogTest() {
        SprintBackLog updatedSprintBacklog = new SprintBackLog(1L, us2, sprint);
        when(sprintBackLogRepository.findById(1L)).thenReturn(Optional.of(sprintBackLog));
        when(sprintBackLogRepository.save(any(SprintBackLog.class))).thenReturn(sprintBackLog);
        sprintBackLogService.updateSprintBackLog(1L, updatedSprintBacklog);
        verify(sprintBackLogRepository, times(1)).save(any(SprintBackLog.class));

    }

    @Test
    public void deleteSprintBackLogTest() {
        when(sprintBackLogRepository.existsById(1L)).thenReturn(true);
        doNothing().when(sprintBackLogRepository).deleteById(1L);
        sprintBackLogService.deleteSprintBackLog(1L);
        verify(sprintBackLogRepository, times(1)).deleteById(1L);

    }
    @Test
    public void getUserStoriesAssociated(){
        us.add(new UserStory());
        when(sprintBackLogRepository.findUserStoriesBySprintBackLogId(1L)).thenReturn(us);

        List<UserStory> test=sprintBackLogService.getUserStoriesAssociated(1L);
        assertEquals(1,test.size());
        verify(sprintBackLogRepository,times(1)).findUserStoriesBySprintBackLogId(1L);
    }
    @Test
    public void getToDoTasksTest(){
        Task task=new Task();
        task.setProgress(TaskProgress.TO_Do);
        UserStory userStory=new UserStory();
        userStory.setTasks(Arrays.asList(task));
        us.add(userStory);
        when(sprintBackLogRepository.findToDoTasksBySprintBackLogId(1L)).thenReturn(userStory.getTasks());
        List<Task> test=sprintBackLogService.getToDoTasks(1L);
        assertEquals(1,test.size());
        verify(sprintBackLogRepository,times(1)).findToDoTasksBySprintBackLogId(1L);
    }
    @Test
    public void getInProgressTasks(){
        Task task=new Task();
        task.setProgress(TaskProgress.In_Progress);
        UserStory userStory=new UserStory();
        userStory.setTasks(Arrays.asList(task));
        us.add(userStory);
        when(sprintBackLogRepository.findInProgressTasksBySprintBackLogId(1L)).thenReturn(userStory.getTasks());
        List<Task> test=sprintBackLogService.getInProgressTasks(1L);
        assertEquals(1,test.size());
        verify(sprintBackLogRepository,times(1)).findInProgressTasksBySprintBackLogId(1L);
    }

    @Test
    public void getBlockedTasks(){
        Task task=new Task();
        task.setProgress(TaskProgress.Blocked);
        UserStory userStory=new UserStory();
        userStory.setTasks(Arrays.asList(task));
        us.add(userStory);
        when(sprintBackLogRepository.findBlockedTasksBySprintBackLogId(1L)).thenReturn(userStory.getTasks());
        List<Task> test=sprintBackLogService.getBlockedTasks(1L);
        assertEquals(1,test.size());
        verify(sprintBackLogRepository,times(1)).findBlockedTasksBySprintBackLogId(1L);
    }

    @Test
    public void getDoneTasks(){
        Task task=new Task();
        task.setProgress(TaskProgress.Done);
        UserStory userStory=new UserStory();
        userStory.setTasks(Arrays.asList(task));
        us.add(userStory);
        when(sprintBackLogRepository.findDoneTaskBySprintBackLogId(1L)).thenReturn(userStory.getTasks());
        List<Task> test=sprintBackLogService.getDoneTasks(1L);
        assertEquals(1,test.size());
        verify(sprintBackLogRepository,times(1)).findDoneTaskBySprintBackLogId(1L);
    }
}