package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Repository.EpicRepository;
import com.apiscrum.APIScrum.Repository.ProductBackLogRepository;
import com.apiscrum.APIScrum.Repository.UserStoryRepository;
import com.apiscrum.APIScrum.Service.ProductBackLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductBackLogServiceImpl implements ProductBackLogService {

    @Autowired
    ProductBackLogRepository productBackLogRepository;
    @Autowired
    EpicRepository epicRepository;
    @Autowired
    UserStoryRepository userStoryRepository;
    @Override
    public void addProductBackLog(ProductBackLog productBackLog) {
        productBackLogRepository.save(productBackLog);
    }

    @Override
    public void updateProductBackLog(Long id, ProductBackLog productBackLog) {
        ProductBackLog pb = productBackLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Backlog not found with id: " + id));
        pb.setProject(productBackLog.getProject());
        pb.setEpics(productBackLog.getEpics());
        pb.setTitle(productBackLog.getTitle());
        pb.setUserStories(productBackLog.getUserStories());
        productBackLogRepository.save(pb);
    }

    @Override
    public void deleteProductBackLog(Long id) {
        productBackLogRepository.deleteById(id);
    }

    @Override
    public Optional<ProductBackLog> getProductBackLog(Long id) {
        return productBackLogRepository.findById(id);
    }

    @Override
    public List<ProductBackLog> getAllProductBackLog() {
        return productBackLogRepository.findAll();
    }

    @Override
    public void addEpic(Long id, Epic epic) {
        ProductBackLog productBackLog = productBackLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductBackLog not found with id: " + id));
        productBackLog.getEpics().add(epic);
    }

    @Override
    public void deleteEpic(Long id_p, Long id) {
        ProductBackLog productBackLog = productBackLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductBackLog not found with id: " + id));
        Epic epic = epicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Epic not found with id: " + id));
        productBackLog.getEpics().remove(epic);
    }

    @Override
    public void addUserStory(Long id_p, UserStory userStory) {
        ProductBackLog productBackLog = productBackLogRepository.findById(id_p)
                .orElseThrow(() -> new RuntimeException("ProductBackLog not found with id: " + id_p));
        productBackLog.getUserStories().add(userStory);
    }

    @Override
    public void deleteUserStory(Long id_p,Long id_us){
        ProductBackLog productBackLog = productBackLogRepository.findById(id_p)
                .orElseThrow(() -> new RuntimeException("ProductBackLog not found with id: " + id_p));
        productBackLog.getUserStories().remove(id_us);
    }

    @Override
    public void deleteUserStoryInBaclog(Long id_us, Long id_pb) {
        ProductBackLog productBackLog = productBackLogRepository.findById(id_pb)
                .orElseThrow(() -> new RuntimeException("ProductBackLog not found with id: " + id_pb));
        UserStory us = userStoryRepository.findById(id_us)
                        .orElseThrow(() -> new RuntimeException("User Story not found with id : "+id_us));
        productBackLog.getUserStories().remove(id_us);
    }
}
