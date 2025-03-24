package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.Entity.Sprint;
import com.apiscrum.APIScrum.Repository.SprintRepository;
import com.apiscrum.APIScrum.Service.SprintService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SprintServiceImpl implements SprintService {


    private SprintRepository sprintRepository;

    public SprintServiceImpl(SprintRepository sprintRepository){
        this.sprintRepository=sprintRepository;
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
       if(sprintRepository.existsById(id)){
           sprintRepository.deleteById(id);
       }else{
           throw new EntityNotFoundException("Sprint not found for id " +id);
       }


    }

    @Override
    @Transactional
    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    @Override
    @Transactional
    public List<Sprint> getAllSprintsByProject(Long id_project) {
        return sprintRepository.findByProjectId(id_project);
    }
    @Override
    public Sprint getSprintById(Long id) {
        return sprintRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Sprint not found for the id "+ id));
    }
}
