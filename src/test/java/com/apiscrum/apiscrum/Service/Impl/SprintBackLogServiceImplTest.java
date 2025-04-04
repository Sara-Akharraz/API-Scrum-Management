package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.Entity.*;
import com.apiscrum.apiscrum.Repository.SprintBackLogRepository;
import com.apiscrum.apiscrum.enums.TestAcceptanceState;
import com.apiscrum.apiscrum.enums.TaskProgress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SprintBackLogServiceImplTest {
    @Mock
    private SprintBackLogRepository sprintBackLogRepository;

    @InjectMocks
    private SprintBackLogServiceImpl sprintBackLogService;

    private SprintBackLog sprintBackLog;
    private Sprint sprint;
    private List<UserStory> us;
    private List<UserStory> us2;



    @BeforeEach
    public void setup() {
        sprint = new Sprint();
        us = new ArrayList<>();
        us2 = new ArrayList<>();
        sprintBackLog = new SprintBackLog(1L, us, sprint);
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
        when(sprintBackLogRepository.save(any(SprintBackLog.class))).thenReturn(updatedSprintBacklog);

        SprintBackLog result = sprintBackLogService.updateSprintBackLog(1L, updatedSprintBacklog);

        assertNotNull(result);
        assertEquals(updatedSprintBacklog.getUserStoriesList(), result.getUserStoriesList());
        verify(sprintBackLogRepository, times(1)).save(updatedSprintBacklog);
    }

    @Test
    public void deleteSprintBackLogTest() {

        SprintBackLog sprintBackLog = new SprintBackLog();
        sprintBackLog.setId(1L);

        UserStory us1 = new UserStory();
        us1.setId(101L);
        us1.setSprintBackLog(sprintBackLog);

        UserStory us2 = new UserStory();
        us2.setId(102L);
        us2.setSprintBackLog(sprintBackLog);


        List<UserStory> userStories = new ArrayList<>();
        userStories.add(us1);
        userStories.add(us2);
        sprintBackLog.setUserStoriesList(userStories);

        when(sprintBackLogRepository.findById(1L)).thenReturn(Optional.of(sprintBackLog));

        sprintBackLogService.deleteSprintBackLog(1L);

        verify(sprintBackLogRepository, times(1)).findById(1L);
        verify(sprintBackLogRepository, times(1)).save(sprintBackLog);
        verify(sprintBackLogRepository, times(1)).deleteById(1L);

        for (UserStory us : userStories) {
            assertNull(us.getSprintBackLog());
        }

        assertTrue(sprintBackLog.getUserStoriesList().isEmpty());
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
        userStory.setTasks(new ArrayList<>(Arrays.asList(task)));
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
        userStory.setTasks(new ArrayList<>(Arrays.asList(task)));
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
        userStory.setTasks(new ArrayList<>(Arrays.asList(task)));
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
        userStory.setTasks(new ArrayList<>(Arrays.asList(task)));
        us.add(userStory);
        when(sprintBackLogRepository.findDoneTaskBySprintBackLogId(1L)).thenReturn(userStory.getTasks());
        List<Task> test=sprintBackLogService.getDoneTasks(1L);
        assertEquals(1,test.size());
        verify(sprintBackLogRepository,times(1)).findDoneTaskBySprintBackLogId(1L);
    }
    @Test
    public void getPassedTests(){
        TestAcceptance test=new TestAcceptance();
        test.setState(TestAcceptanceState.Passed);
        UserStory userStory=new UserStory();
        userStory.setTest_acceptanceList(new ArrayList<>(Arrays.asList(test)));
        us.add(userStory);
        when(sprintBackLogRepository.findPassedTestBySprintBackLogId(1L)).thenReturn(userStory.getTest_acceptanceList());
        List<TestAcceptance> tests=sprintBackLogService.getPassedTests(1L);
        assertEquals(1,tests.size());
        verify(sprintBackLogRepository,times(1)).findPassedTestBySprintBackLogId(1L);
    }
    @Test
    public void getPendingTests(){
        TestAcceptance test=new TestAcceptance();
        test.setState(TestAcceptanceState.Pending);
        UserStory userStory=new UserStory();
        userStory.setTest_acceptanceList(new ArrayList<>(Arrays.asList(test)));
        us.add(userStory);
        when(sprintBackLogRepository.findPendingTestBySprintBackLogId(1L)).thenReturn(userStory.getTest_acceptanceList());
        List<TestAcceptance> tests=sprintBackLogService.getPendingTests(1L);
        assertEquals(1,tests.size());
        verify(sprintBackLogRepository,times(1)).findPendingTestBySprintBackLogId(1L);
    }
    @Test
    public void getFailedTests(){
        TestAcceptance test=new TestAcceptance();
        test.setState(TestAcceptanceState.Failed);
        UserStory userStory=new UserStory();
        userStory.setTest_acceptanceList(new ArrayList<>(Arrays.asList(test)));
        us.add(userStory);
        when(sprintBackLogRepository.findFailedTestBySprintBackLogId(1L)).thenReturn(userStory.getTest_acceptanceList());
        List<TestAcceptance> tests=sprintBackLogService.getFailedTests(1L);
        assertEquals(1,tests.size());
        verify(sprintBackLogRepository,times(1)).findFailedTestBySprintBackLogId(1L);
    }
}
