package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Repository.UserStoryRepository;
import com.apiscrum.APIScrum.Service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStoryServiceImpl implements UserStoryService {
    @Autowired
    UserStoryRepository userStoryRepository;

    @Override
    public void createUserStory(UserStory userStory) {
        userStoryRepository.save(userStory);
    }

    @Override
    public void deleteUserStory(Long id) {
        userStoryRepository.deleteById(id);
    }

    @Override
    public UserStory getUserStory(Long id) {
        return userStoryRepository.findById(id).get();
    }

}
