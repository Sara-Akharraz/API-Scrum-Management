package com.apiscrum.apiscrum.Mapper;

import com.apiscrum.apiscrum.DTO.EpicDto;
import com.apiscrum.apiscrum.Entity.Epic;

public class EpicMapper {

    public static EpicDto mapToEpicDTO(Epic epic) {
        return EpicDto.builder()
                .id(epic.getId())
                .title(epic.getTitle())
                .description(epic.getDescription())
                .userStories(epic.getUserStories())
                .productBackLog(epic.getProductBackLog())
                .build();
    }

    public static Epic mapToEpic(EpicDto epicDto) {
        return Epic.builder()
                .id(epicDto.getId())
                .title(epicDto.getTitle())
                .description(epicDto.getDescription())
                .userStories(epicDto.getUserStories())
                .productBackLog(epicDto.getProductBackLog())
                .build();
    }
}
