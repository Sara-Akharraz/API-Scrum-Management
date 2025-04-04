package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.Entity.Sprint;
import com.apiscrum.apiscrum.Repository.SprintBackLogRepository;
import com.apiscrum.apiscrum.Repository.SprintRepository;
import com.apiscrum.apiscrum.Service.SprintService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SprintServiceImpl implements SprintService {

    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private SprintBackLogRepository sprintBackLogRepository;

    public SprintServiceImpl(SprintRepository sprintRepository,SprintBackLogRepository sprintBackLogRepository){
        this.sprintRepository=sprintRepository;
        this.sprintBackLogRepository=sprintBackLogRepository;
    }

    @Override
    public Sprint addSprint(Sprint sprint) {
       return sprintRepository.save(sprint);
    }

    @Override

    public Sprint updateSprint(Long id, Sprint UpdatedSprint) {
       Optional<Sprint> prevSprint=sprintRepository.findById(id);
        if(prevSprint.isPresent()){
            Sprint newsprint=prevSprint.get();
            newsprint.setDuration(UpdatedSprint.getDuration());
            newsprint.setStart_date(UpdatedSprint.getStart_date());
            newsprint.setEnd_date(UpdatedSprint.getEnd_date());
            newsprint.setSprintBackLog(UpdatedSprint.getSprintBackLog());
            newsprint.setAssociatedProject(UpdatedSprint.getAssociatedProject());
            return sprintRepository.save(newsprint);
        }
        else{
            throw new EntityNotFoundException("Sprint not found for id :" + id);
        }
    }

    @Override
    @Transactional
    public void deleteSprint(Long id) {
        Sprint sprint = sprintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sprint not found"));
        if (sprint.getSprintBackLog() != null) {
            sprintBackLogRepository.delete(sprint.getSprintBackLog());
        }
        sprintRepository.delete(sprint);

    }

    @Override
    @Transactional
    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    @Override
    @Transactional
    public List<Sprint> getAllSprintsByProject(Long id_project) {
        return sprintRepository.findByAssociatedProjectId(id_project);
    }
    @Override
    public Sprint getSprintById(Long id) {
        return sprintRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Sprint not found for the id "+ id));
    }
}
