package com.apiscrum.apiscrum.Service;

import com.apiscrum.apiscrum.Entity.Epic;
import com.apiscrum.apiscrum.Entity.ProductBackLog;
import com.apiscrum.apiscrum.Entity.UserStory;

public interface EpicService {
    public void addEpic(ProductBackLog p, Epic epic);
    public void deleteEpic(Long id);
    public void addUserStoryToEpic(Long id_e, UserStory userStory);

}
