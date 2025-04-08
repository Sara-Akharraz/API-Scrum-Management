package com.apiscrum.apiscrum.Mapper;


import com.apiscrum.apiscrum.DTO.ProductBackLogDto;
import com.apiscrum.apiscrum.Entity.ProductBackLog;

public class ProductBackLogMapper {
    public static ProductBackLogDto mapToProductBackLogDTO(ProductBackLog productBackLog) {
        return ProductBackLogDto.builder()
                .id(productBackLog.getId())
                .title(productBackLog.getTitle())
                .epics(productBackLog.getEpics())
                .userStories(productBackLog.getUserStories())
                .build();
    }

    public static ProductBackLog mapToProductBackLog(ProductBackLogDto productBackLogDto) {
        return ProductBackLog.builder()
                .id(productBackLogDto.getId())
                .title(productBackLogDto.getTitle())
                .epics(productBackLogDto.getEpics())
                .userStories(productBackLogDto.getUserStories())
                .build();
    }
}
