package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Repository.UserStoryRepository;
import com.apiscrum.apiscrum.Service.UserStoryService;
import com.apiscrum.apiscrum.enums.UserStoryProgress;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserStoryServiceImpl implements UserStoryService {
    @Autowired
    UserStoryRepository userStoryRepository;

    @Override
    public void deleteUserStory(Long id) {
        userStoryRepository.deleteById(id);
    }

    @Override
    public UserStory getUserStory(Long id) {
        return userStoryRepository.findById(id).get();
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

}
