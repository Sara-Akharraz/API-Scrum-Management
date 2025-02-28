package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;

public interface EpicService {
    public void addEpic(ProductBackLog p, Epic epic);
    public void deleteEpic(Long id);
}
