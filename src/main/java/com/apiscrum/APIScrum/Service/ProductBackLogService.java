package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;

import java.util.List;
import java.util.Optional;

public interface ProductBackLogService {
    public void addProductBackLog(ProductBackLog productBackLog);
    public void updateProductBackLog(Long id, ProductBackLog productBackLog);
    public void deleteProductBackLog(Long id);
    public Optional<ProductBackLog> getProductBackLog(Long id);
    public List<ProductBackLog> getAllProductBackLog();
}