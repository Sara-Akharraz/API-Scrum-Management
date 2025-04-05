package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.DTO.ProductBackLogDto;
import com.apiscrum.APIScrum.DTO.UserStoryDto;
import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Mapper.UserStoryMapper;

import java.util.List;
import java.util.Optional;

public interface ProductBackLogService {
    public ProductBackLogDto addProductBackLog(ProductBackLogDto productBackLog, Long id_project);
    public ProductBackLogDto updateProductBackLog(Long id, ProductBackLogDto productBackLog);
    public void deleteProductBackLog(Long id);
    public Optional<ProductBackLogDto> getProductBackLog(Long id);
    public List<ProductBackLogDto> getAllProductBackLog();
    public ProductBackLogDto addUserStoryToPB(UserStoryDto userStory, Long id);
}
