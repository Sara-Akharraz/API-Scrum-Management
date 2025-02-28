package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;
import org.apache.catalina.User;

import java.util.List;

public interface ProductBackLogService {
    public void addProductBackLog(ProductBackLog productBackLog);
    public void updateProductBackLog(Long id, ProductBackLog productBackLog);
    public void deleteProductBackLog(Long id);
    public ProductBackLog getProductBackLog(Long id);
    public List<ProductBackLog> getAllProductBackLog();
    public void addEpic(Long id, Epic epic);
    public void deleteEpic(Long id_p, Long id);
    public void addUserStory(UserStory userStory);
    public void deleteUserStory(Long id);
}
