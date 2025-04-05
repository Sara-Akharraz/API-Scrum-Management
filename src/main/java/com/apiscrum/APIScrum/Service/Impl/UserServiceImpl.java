package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.DTO.UserStoryDto;
import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Mapper.UserStoryMapper;
import com.apiscrum.APIScrum.Repository.EpicRepository;
import com.apiscrum.APIScrum.Repository.ProductBackLogRepository;
import com.apiscrum.APIScrum.Repository.UserStoryRepository;
import com.apiscrum.APIScrum.Service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStoryServiceImpl implements UserStoryService {
    @Autowired
    UserStoryRepository userStoryRepository;
    @Autowired
    ProductBackLogRepository productBackLogRepository;
    @Autowired
    EpicRepository epicRepository;





    @Override
    public void deleteUserStory(Long id) {
        if(userStoryRepository.existsById(id))
            userStoryRepository.deleteById(id);
        else
            throw new RuntimeException("User Story not found with id: " + id);
    }

    @Override
    public UserStoryDto getUserStory(Long id) {

        return UserStoryMapper.mapToUserStoryDTO(userStoryRepository.findById(id).get());
    }
}
