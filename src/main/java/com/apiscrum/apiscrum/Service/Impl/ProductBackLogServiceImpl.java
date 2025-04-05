package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.Controller.ProductBackLogController;
import com.apiscrum.APIScrum.DTO.ProductBackLogDto;
import com.apiscrum.APIScrum.DTO.UserStoryDto;
import com.apiscrum.APIScrum.Entity.*;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Mapper.ProductBackLogMapper;
import com.apiscrum.APIScrum.Mapper.ProjectMapper;
import com.apiscrum.APIScrum.Mapper.UserStoryMapper;
import com.apiscrum.APIScrum.Repository.EpicRepository;
import com.apiscrum.APIScrum.Repository.ProductBackLogRepository;
import com.apiscrum.APIScrum.Repository.ProjectRepository;
import com.apiscrum.APIScrum.Repository.UserStoryRepository;
import com.apiscrum.APIScrum.Service.ProductBackLogService;
import com.apiscrum.APIScrum.Service.UserStoryService;
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
    public ProductBackLogDto addProductBackLog(ProductBackLogDto productBackLog, Long id_project) {
        Project project = projectRepository.findById(id_project)
                .orElseThrow(() -> new RuntimeException("Product Backlog not found with id: " + id_project));
        productBackLog.setProject(project);
        return ProductBackLogMapper.mapToProductBackLogDTO(productBackLogRepository.save(
                ProductBackLogMapper.mapToProductBackLog(productBackLog)
        ));
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
