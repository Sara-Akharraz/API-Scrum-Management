package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.Entity.*;
import com.apiscrum.APIScrum.Repository.SprintBackLogRepository;
import com.apiscrum.APIScrum.Service.SprintBackLogService;
import com.apiscrum.APIScrum.enums.TaskProgress;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SprintBackLogServiceImpl implements SprintBackLogService {

    private SprintBackLogRepository sprintBackLogRepository;

    public SprintBackLogServiceImpl(SprintBackLogRepository sprintBackLogRepository){
        this.sprintBackLogRepository=sprintBackLogRepository;
    }

    @Override
    public SprintBackLog addSprintBackLog(SprintBackLog sprintBackLog) {
        return sprintBackLogRepository.save(sprintBackLog);

    }

    @Override
    public SprintBackLog updateSprintBackLog(Long id, SprintBackLog UpdatedSprintBacklog) {
        Optional<SprintBackLog> prevSprintBacklog =sprintBackLogRepository.findById(id);
        if(prevSprintBacklog.isPresent()){
            SprintBackLog sprintBackLog=prevSprintBacklog.get();
            sprintBackLog.setAssociatedSprint(UpdatedSprintBacklog.getAssociatedSprint());
            sprintBackLog.setUserStoriesList(UpdatedSprintBacklog.getUserStoriesList());
            return sprintBackLogRepository.save(sprintBackLog);
        }
        else{
            throw new EntityNotFoundException("SprintBackLog not found for id :" +id);
        }

    }

    @Override
    public void deleteSprintBackLog(Long id) {
        if(sprintBackLogRepository.existsById(id)){
            sprintBackLogRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("SprintBackLog not found for id :" +id);
        }


    }

    @Override
    public List<UserStory> getUserStoriesAssociated(Long SprintBackLog_id) {
        return sprintBackLogRepository.findUserStoriesBySprintBackLogId(SprintBackLog_id);
    }
    public List<Task> getToDoTasks(Long SprintBackLog_id) {
        return sprintBackLogRepository.findToDoTasksBySprintBackLogId(SprintBackLog_id);
    }
    public List<Task> getInProgressTasks(Long SprintBackLog_id) {
        return sprintBackLogRepository.findInProgressTasksBySprintBackLogId(SprintBackLog_id);
    }

    public List<Task> getBlockedTasks(Long SprintBackLog_id) {
        return sprintBackLogRepository.findBlockedTasksBySprintBackLogId(SprintBackLog_id);
    }

    public List<Task> getDoneTasks(Long SprintBackLog_id){
        return sprintBackLogRepository.findDoneTaskBySprintBackLogId(SprintBackLog_id);
    }

    @Override
    public List<Test_Acceptance> getPassedTests(Long SprintBackLog_id) {
        return sprintBackLogRepository.findPassedTestBySprintBackLogId(SprintBackLog_id);
    }

    @Override
    public List<Test_Acceptance> getPendingTests(Long SprintBackLog_id) {
        return sprintBackLogRepository.findPendingTestBySprintBackLogId(SprintBackLog_id);
    }

    @Override
    public List<Test_Acceptance> getFailedTests(Long SprintBackLog_id) {
        return sprintBackLogRepository.findFailedTestBySprintBackLogId(SprintBackLog_id);
    }
}
