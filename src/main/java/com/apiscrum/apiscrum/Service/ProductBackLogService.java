package com.apiscrum.apiscrum.Service;

import com.apiscrum.apiscrum.DTO.ProductBackLogDto;
import com.apiscrum.apiscrum.DTO.UserStoryDto;
import com.apiscrum.apiscrum.Entity.Epic;
import com.apiscrum.apiscrum.Entity.ProductBackLog;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Mapper.UserStoryMapper;

import java.util.List;
import java.util.Optional;

public interface ProductBackLogService {
    public ProductBackLogDto addProductBackLog(String title, Long id_project);
    public ProductBackLogDto updateProductBackLog(Long id, ProductBackLogDto productBackLog);
    public void deleteProductBackLog(Long id);
    public Optional<ProductBackLogDto> getProductBackLog(Long id);
    public List<UserStoryDto> getUserStoriesByProductBackLog(Long id);
    public List<ProductBackLogDto> getAllProductBackLog();
    public ProductBackLogDto addUserStoryToPB(UserStoryDto userStory, Long id);
}
