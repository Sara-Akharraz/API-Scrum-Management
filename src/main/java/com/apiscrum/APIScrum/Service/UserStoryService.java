package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.Task;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.enums.UserStoryProgress;

import java.util.List;

public interface UserStoryService {
    public void deleteUserStory(Long id);
    public UserStory getUserStory(Long id);
    public UserStory updateUserStory(Long id, UserStoryProgress progress);

}
