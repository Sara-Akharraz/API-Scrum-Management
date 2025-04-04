package com.apiscrum.apiscrum.Service;

import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.enums.UserStoryProgress;

public interface UserStoryService {
    public void deleteUserStory(Long id);
    public UserStory getUserStory(Long id);
    public UserStory updateUserStoryProgress(Long id, UserStoryProgress progress);

}
