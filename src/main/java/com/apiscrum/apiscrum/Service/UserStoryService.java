package com.apiscrum.apiscrum.Service;

import com.apiscrum.apiscrum.DTO.UserStoryDto;
import com.apiscrum.apiscrum.Entity.Epic;
import com.apiscrum.apiscrum.Entity.User;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.enums.UserStoryProgress;

import java.util.List;

public interface UserStoryService {
//    public UserStory addUserStory(UserStory userStory);
    public UserStory updateUserStory(Long id,UserStory userStory);
    public void deleteUserStory(Long id);
    public UserStoryDto getUserStory(Long id);
    public UserStory updateUserStoryProgress(Long id, UserStoryProgress updatedprogress);
    public List<UserStory> getAllUserStories();
}
