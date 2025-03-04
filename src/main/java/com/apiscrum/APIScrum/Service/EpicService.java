package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;

import java.util.List;

public interface EpicService {
    public void addEpic(Epic epic);
    public void deleteEpic(Long id);
//    public void addUserStrotyToEpic(Long id_e, UserStory userStory);
    public List<UserStory> getUserEpicStories(Long id);
    public List<Epic> getEpics();
}