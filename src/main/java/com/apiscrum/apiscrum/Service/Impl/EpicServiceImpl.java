package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.Entity.Epic;
import com.apiscrum.apiscrum.Entity.ProductBackLog;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Repository.EpicRepository;
import com.apiscrum.apiscrum.Service.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpicServiceImpl implements EpicService {

    @Autowired
    EpicRepository epicRepository;
    @Override
    public void addEpic(ProductBackLog p, Epic epic) {
        epicRepository.save(epic);
    }

    @Override
    public void deleteEpic(Long id) {
        epicRepository.deleteById(id);
    }

    @Override
    public void addUserStoryToEpic(Long id_e, UserStory userStory) {
        Epic epic = epicRepository.findById(id_e)
                .orElseThrow(() -> new RuntimeException("Epic not found with id: " + id_e));
        epic.getUserStories().add(userStory);
    }


}
