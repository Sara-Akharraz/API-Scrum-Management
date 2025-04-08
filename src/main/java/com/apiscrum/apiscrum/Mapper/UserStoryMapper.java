package com.apiscrum.apiscrum.Mapper;

import com.apiscrum.apiscrum.DTO.UserStoryDto;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Repository.TaskRepository;
import com.apiscrum.apiscrum.Repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class UserStoryMapper {

    public static UserStoryDto mapToUserStoryDTO(UserStory userStory){
        return UserStoryDto.builder()
                .id(userStory.getId())
                .title(userStory.getTitle())
                .as_a(userStory.getAs_a())
                .i_wish_to(userStory.getI_wish_to())
                .in_order_to(userStory.getIn_order_to())
                .priority(userStory.getPriority())
                .productBackLog(userStory.getProductBackLog())
                .epic(userStory.getEpic())
                .tasks(userStory.getTasks())
                .progress(userStory.getProgress())
                .sprintBackLog(userStory.getSprintBackLog())
                .test_acceptanceList(userStory.getTest_acceptanceList())
                .build();
    }

    public static UserStory mapToUserStory(UserStoryDto userStoryDto){
        return UserStory.builder()
                .id(userStoryDto.getId())
                .title(userStoryDto.getTitle())
                .as_a(userStoryDto.getAs_a())
                .i_wish_to(userStoryDto.getI_wish_to())
                .in_order_to(userStoryDto.getIn_order_to())
                .priority(userStoryDto.getPriority())
                .productBackLog(userStoryDto.getProductBackLog())
                .epic(userStoryDto.getEpic())
                .progress(userStoryDto.getProgress())
                .sprintBackLog(userStoryDto.getSprintBackLog())
                .tasks(userStoryDto.getTasks())
                .test_acceptanceList(userStoryDto.getTest_acceptanceList())
                .build();
    }
}
