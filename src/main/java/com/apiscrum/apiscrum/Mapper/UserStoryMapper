package com.apiscrum.APIScrum.Mapper;

import com.apiscrum.APIScrum.DTO.UserStoryDto;
import com.apiscrum.APIScrum.Entity.UserStory;

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
                .build();
    }
}
