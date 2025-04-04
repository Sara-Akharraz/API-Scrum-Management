package com.apiscrum.apiscrum.Service;

import com.apiscrum.apiscrum.Entity.Epic;
import com.apiscrum.apiscrum.Entity.ProductBackLog;
import com.apiscrum.apiscrum.Entity.UserStory;

import java.util.List;
import java.util.Optional;

public interface ProductBackLogService {
    public void addProductBackLog(ProductBackLog productBackLog);
    public void updateProductBackLog(Long id, ProductBackLog productBackLog);
    public void deleteProductBackLog(Long id);
    public Optional<ProductBackLog> getProductBackLog(Long id);
    public List<ProductBackLog> getAllProductBackLog();
    public void addEpic(Long id, Epic epic);
    public void deleteEpic(Long id_p, Long id);
    public void deleteUserStory(Long id,Long id_us);
    public void addUserStory(Long id_p, UserStory userStory);
    public void deleteUserStoryInBaclog(Long id_us, Long id_pb);
}