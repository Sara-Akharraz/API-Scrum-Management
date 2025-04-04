package com.apiscrum.apiscrum.Mapper;

import com.apiscrum.apiscrum.DTO.TestAcceptanceDTO;
import com.apiscrum.apiscrum.Entity.TestAcceptance;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestAcceptanceMapper {
    @Autowired
    private UserStoryRepository userStoryRepository;

    public TestAcceptanceDTO toDTO(TestAcceptance test){
        return new TestAcceptanceDTO(
                test.getId(),
                test.getScenario(),
                test.getGiven(),
                test.getWhen(),
                test.getThen(),
                test.getAnd(),
                test.getBut(),
                test.getState(),
                test.getAssociatedUserStory().getId()
            );
    }
    public TestAcceptance toEntity(TestAcceptanceDTO testDTO){
        UserStory userStory=userStoryRepository.findById(testDTO.getAssociatedUserStory()).orElseThrow(()-> new RuntimeException("User Story not found"));
        return new TestAcceptance(
                testDTO.getId(),
                testDTO.getScenario(),
                testDTO.getGiven(),
                testDTO.getWhen(),
                testDTO.getThen(),
                testDTO.getAnd(),
                testDTO.getBut(),
                userStory,
                testDTO.getState()
        );
    }
}
