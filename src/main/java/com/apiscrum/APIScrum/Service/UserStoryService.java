package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.UserStory;

import java.util.List;

public interface UserStoryService {
    public void createUserStory(UserStory userStory);
    public void deleteUserStory(Long id);
    public UserStory getUserStory(Long id);
}