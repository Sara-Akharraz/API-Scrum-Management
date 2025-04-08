package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.Entity.*;
import com.apiscrum.apiscrum.Repository.SprintBackLogRepository;
import com.apiscrum.apiscrum.Repository.UserStoryRepository;
import com.apiscrum.apiscrum.Service.SprintBackLogService;
import com.apiscrum.apiscrum.enums.UserStoryProgress;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SprintBackLogServiceImpl implements SprintBackLogService {

    private SprintBackLogRepository sprintBackLogRepository;
    private UserStoryRepository userStoryRepository;

    public SprintBackLogServiceImpl(SprintBackLogRepository sprintBackLogRepository){
        this.sprintBackLogRepository=sprintBackLogRepository;
    }
    @Override
    public SprintBackLog getSprintBackLogById(Long id) {
        return sprintBackLogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SprintBackLog not found for id: " + id));
    }
    @Override
    public SprintBackLog addSprintBackLog(SprintBackLog sprintBackLog) {
        if (sprintBackLog.getUserStoriesList() != null) {
            for (UserStory us : sprintBackLog.getUserStoriesList()) {
                us.setSprintBackLog(sprintBackLog);
            }
        }

        return sprintBackLogRepository.save(sprintBackLog);
    }

    @Override
    public SprintBackLog updateSprintBackLog(Long id, SprintBackLog updatedSprintBacklog) {
        return sprintBackLogRepository.findById(id).map(existedSprintBacklog -> {

            existedSprintBacklog.setAssociatedSprint(updatedSprintBacklog.getAssociatedSprint());

            existedSprintBacklog.getUserStoriesList().clear();

            if (updatedSprintBacklog.getUserStoriesList() != null) {
                for (UserStory us : updatedSprintBacklog.getUserStoriesList()) {
                    us.setSprintBackLog(existedSprintBacklog);
                    existedSprintBacklog.getUserStoriesList().add(us);
                }
            }

            return sprintBackLogRepository.save(existedSprintBacklog);
        }).orElseThrow(() -> new EntityNotFoundException("SprintBackLog not found for id :" + id));
    }

    @Override
    public void deleteSprintBackLog(Long id) {
        SprintBackLog sprintBackLog = sprintBackLogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SprintBackLog not found for id :" + id));
        for (UserStory userStory : sprintBackLog.getUserStoriesList()) {
            userStory.setSprintBackLog(null);
        }

        sprintBackLog.getUserStoriesList().clear();
        sprintBackLogRepository.save(sprintBackLog);

        sprintBackLogRepository.deleteById(id);

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
    public List<TestAcceptance> getPassedTests(Long SprintBackLog_id) {
        return sprintBackLogRepository.findPassedTestBySprintBackLogId(SprintBackLog_id);
    }

    @Override
    public List<TestAcceptance> getPendingTests(Long SprintBackLog_id) {
        return sprintBackLogRepository.findPendingTestBySprintBackLogId(SprintBackLog_id);
    }

    @Override
    public List<TestAcceptance> getFailedTests(Long SprintBackLog_id) {
        return sprintBackLogRepository.findFailedTestBySprintBackLogId(SprintBackLog_id);
    }
}
