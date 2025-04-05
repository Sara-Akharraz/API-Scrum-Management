package com.apiscrum.APIScrum.Mapper;

import com.apiscrum.APIScrum.DTO.ProductBackLogDto;
import com.apiscrum.APIScrum.Entity.ProductBackLog;

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
