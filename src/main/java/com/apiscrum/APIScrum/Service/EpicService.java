package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;

public interface EpicService {
    public void addEpic(ProductBackLog p, Epic epic);
    public void deleteEpic(Long id);
    public void addUserStrotyToEpic(Long id_e, UserStory userStory);
}
