package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.DTO.UserStoryDto;
import com.apiscrum.apiscrum.Entity.Epic;
import com.apiscrum.apiscrum.Entity.ProductBackLog;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Mapper.UserStoryMapper;
import com.apiscrum.apiscrum.Repository.EpicRepository;
import com.apiscrum.apiscrum.Repository.ProductBackLogRepository;
import com.apiscrum.apiscrum.Repository.UserStoryRepository;
import com.apiscrum.apiscrum.Service.UserStoryService;
import com.apiscrum.apiscrum.enums.UserStoryProgress;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserStoryServiceImpl implements UserStoryService {
    @Autowired
    UserStoryRepository userStoryRepository;
    @Autowired
    ProductBackLogRepository productBackLogRepository;
    @Autowired
    EpicRepository epicRepository;


//    @Override
//    public UserStory addUserStory(UserStory userStory) {
//        return userStoryRepository.save(userStory);
//    }

    @Override
    public UserStory updateUserStory(Long id, UserStory userStory) {
        Optional<UserStory> existedUserstory=userStoryRepository.findById(id);
        if(existedUserstory.isPresent()){
            UserStory updatedUserstory=existedUserstory.get();
            updatedUserstory.setTitle(userStory.getTitle());
            updatedUserstory.setAs_a(updatedUserstory.getAs_a());
            updatedUserstory.setI_wish_to(userStory.getI_wish_to());
            updatedUserstory.setIn_order_to(userStory.getIn_order_to());
            updatedUserstory.setPriority(userStory.getPriority());
            updatedUserstory.setProgress(userStory.getProgress());
            updatedUserstory.setTasks(userStory.getTasks());
            updatedUserstory.setEpic(userStory.getEpic());
            updatedUserstory.setSprintBackLog(updatedUserstory.getSprintBackLog());
            updatedUserstory.setTest_acceptanceList(userStory.getTest_acceptanceList());
            return userStoryRepository.save(updatedUserstory);
        }else{
            throw new EntityNotFoundException("User Story not found with id "+ id);
        }

    }
    @Override
    public void deleteUserStory(Long id) {
        if(userStoryRepository.existsById(id))
            userStoryRepository.deleteById(id);
        else
            throw new RuntimeException("User Story not found with id: " + id);
    }

    @Override
    public UserStoryDto getUserStory(Long id) {
        UserStory us = userStoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Story not found with id: " + id));
        return UserStoryMapper.mapToUserStoryDTO(us);
    }
    @Override
    public UserStory updateUserStoryProgress(Long id, UserStoryProgress updatedprogress) {
        Optional<UserStory> prevUser_Story=userStoryRepository.findById(id);
        if(prevUser_Story.isPresent()){
            UserStory userStory=prevUser_Story.get();
            userStory.setProgress(updatedprogress);
            return userStoryRepository.save(userStory);
        }
        else{
            throw new EntityNotFoundException("User story not found for id :" +id);
        }
    }
    @Override
    public List<UserStory> getAllUserStories(){
        return userStoryRepository.findAll();
    }
    
}
