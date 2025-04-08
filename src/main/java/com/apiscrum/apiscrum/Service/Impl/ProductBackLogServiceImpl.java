package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.Controller.ProductBackLogController;
import com.apiscrum.apiscrum.DTO.ProductBackLogDto;
import com.apiscrum.apiscrum.DTO.UserStoryDto;
import com.apiscrum.apiscrum.Entity.*;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Mapper.ProductBackLogMapper;
import com.apiscrum.apiscrum.Mapper.ProjectMapper;
import com.apiscrum.apiscrum.Mapper.UserStoryMapper;
import com.apiscrum.apiscrum.Repository.EpicRepository;
import com.apiscrum.apiscrum.Repository.ProductBackLogRepository;
import com.apiscrum.apiscrum.Repository.ProjectRepository;
import com.apiscrum.apiscrum.Repository.UserStoryRepository;
import com.apiscrum.apiscrum.Service.ProductBackLogService;
import com.apiscrum.apiscrum.Service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductBackLogServiceImpl implements ProductBackLogService {

    @Autowired
    ProductBackLogRepository productBackLogRepository;
    @Autowired
    EpicRepository epicRepository;
    @Autowired
    UserStoryRepository userStoryRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Override
    public ProductBackLogDto addProductBackLog(String title, Long id_project) {
        Project project = projectRepository.findById(id_project)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id_project));
        ProductBackLog productBackLog = ProductBackLog.builder().title(title).project(project).build();
        project.setProductBackLog(productBackLog);
        productBackLogRepository.save(productBackLog);
        projectRepository.save(project);
        return ProductBackLogMapper.mapToProductBackLogDTO(productBackLog);

    }

    @Override
    public ProductBackLogDto updateProductBackLog(Long id, ProductBackLogDto productBackLog) {
        ProductBackLog pb = productBackLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Backlog not found with id: " + id));
        pb.setProject(productBackLog.getProject());
        pb.setEpics(productBackLog.getEpics());
        pb.setTitle(productBackLog.getTitle());
        pb.setUserStories(productBackLog.getUserStories());
        ProductBackLog p = productBackLogRepository.save(pb);
        System.out.println("-------------------------------------------"+ pb.getId());
        return ProductBackLogMapper.mapToProductBackLogDTO(p);
    }

    @Override
    public void deleteProductBackLog(Long id) {
        if(productBackLogRepository.existsById(id))
            productBackLogRepository.deleteById(id);
        else
            throw new RuntimeException("Product Backlog not found with id: " + id);

    }

    @Override
    public Optional<ProductBackLogDto> getProductBackLog(Long id) {

        return productBackLogRepository.findById(id).map(ProductBackLogMapper::mapToProductBackLogDTO);
    }

    @Override
    public List<UserStoryDto> getUserStoriesByProductBackLog(Long id) {
        ProductBackLog pb = productBackLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Backlog not found with id: " + id));
        return pb.getUserStories().stream().map((p) -> UserStoryMapper.mapToUserStoryDTO(p))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductBackLogDto> getAllProductBackLog() {
        List<ProductBackLog> pbs = productBackLogRepository.findAll();
        return pbs.stream().map((pb) -> ProductBackLogMapper.mapToProductBackLogDTO(pb))
                .collect(Collectors.toList());
    }

    @Override
    public ProductBackLogDto addUserStoryToPB(UserStoryDto userStory, Long id) {
        ProductBackLog pb = productBackLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Backlog not found with id: " + id));
        userStory.setProductBackLog(pb);
        userStoryRepository.save(UserStoryMapper.mapToUserStory(userStory));
        return ProductBackLogMapper.mapToProductBackLogDTO(pb);
    }
//
//    @Override
//    public void addEpic(Long id, Epic epic) {
//        ProductBackLog productBackLog = productBackLogRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("ProductBackLog not found with id: " + id));
//        productBackLog.getEpics().add(epic);
//    }
//
//
//
//    @Override
//    public void addUserStory(Long id_p, UserStory userStory) {
//        ProductBackLog productBackLog = productBackLogRepository.findById(id_p)
//                .orElseThrow(() -> new RuntimeException("ProductBackLog not found with id: " + id_p));
//        productBackLog.getUserStories().add(userStory);
//    }

}
