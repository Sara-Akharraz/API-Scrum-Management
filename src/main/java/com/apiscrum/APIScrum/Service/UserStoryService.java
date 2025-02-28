package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.UserStory;

import java.util.List;

public interface UserStoryService {
    public void addUserStoryToBackLog(Long id_b, UserStory userStory);
    public void addUserStrotyToEpic(Long id_e, UserStory userStory);
    public void deleteUserStory(Long id);
    public UserStory getUserStory(Long id);

}
