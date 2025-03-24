package com.apiscrum.APIScrum.Mapper;

import com.apiscrum.APIScrum.DTO.Test_AcceptanceDTO;
import com.apiscrum.APIScrum.Entity.Test_Acceptance;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Test_AcceptanceMapper {
    @Autowired
    private UserStoryRepository userStoryRepository;

    public Test_AcceptanceDTO toDTO(Test_Acceptance test){
        return new Test_AcceptanceDTO(
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
    public Test_Acceptance toEntity(Test_AcceptanceDTO testDTO){
        UserStory userStory=userStoryRepository.findById(testDTO.getAssociatedUserStory()).orElseThrow(()-> new RuntimeException("User Story not found"));
        return new Test_Acceptance(
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
