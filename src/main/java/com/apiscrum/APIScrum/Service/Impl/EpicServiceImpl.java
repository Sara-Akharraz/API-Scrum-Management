package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Repository.EpicRepository;
import com.apiscrum.APIScrum.Service.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpicServiceImpl implements EpicService {

    @Autowired
    EpicRepository epicRepository;

    @Override
    public void addEpic(Epic epic) {
        epicRepository.save(epic);
    }

    @Override
    public void deleteEpic(Long id) {
        epicRepository.deleteById(id);
    }

    @Override
    public List<UserStory> getUserEpicStories(Long id) {
        Epic epic = epicRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Epic not found with id: " + id));

        return epic.getUserStories();
    }

    @Override
    public List<Epic> getEpics() {
        return List.of();
    }


}
