package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.DTO.UserStoryDto;
import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.UserStory;

import java.util.List;

public interface UserStoryService {
    public void deleteUserStory(Long id);
    public UserStoryDto getUserStory(Long id);

}