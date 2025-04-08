package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.DTO.EpicDto;
import com.apiscrum.apiscrum.DTO.ProductBackLogDto;
import com.apiscrum.apiscrum.DTO.ProjectDto;
import com.apiscrum.apiscrum.DTO.UserStoryDto;
import com.apiscrum.apiscrum.Entity.Epic;
import com.apiscrum.apiscrum.Entity.ProductBackLog;
import com.apiscrum.apiscrum.Entity.Project;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Mapper.ProductBackLogMapper;
import com.apiscrum.apiscrum.Mapper.ProjectMapper;
import com.apiscrum.apiscrum.Mapper.UserStoryMapper;
import com.apiscrum.apiscrum.Repository.EpicRepository;
import com.apiscrum.apiscrum.Repository.ProductBackLogRepository;
import com.apiscrum.apiscrum.Repository.ProjectRepository;
import com.apiscrum.apiscrum.Repository.UserStoryRepository;
import com.apiscrum.apiscrum.Service.ProductBackLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ProductBackLogServiceImplTest {
    @Mock
    private ProductBackLogRepository productBackLogRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    UserStoryRepository userStoryRepository;
    @InjectMocks
    private ProductBackLogServiceImpl productBackLogService;
    private ProductBackLogDto productBackLog;
    private Project project;

    @BeforeEach
    void setUp() {
        productBackLog = ProductBackLogDto.builder().id(1L).title("PB 1").build();
        project = Project.builder().id(1L).build();
    }

    @Test
    public void TestAddProductBackLog() {
        productBackLog = ProductBackLogDto.builder().id(1L).title("PB 1").build();
        when(projectRepository.findById(1L)).thenReturn(Optional.ofNullable(project));
        when(productBackLogRepository.save(any(ProductBackLog.class))).thenReturn(ProductBackLogMapper.mapToProductBackLog(productBackLog));
        ProductBackLogDto createdPB = productBackLogService.addProductBackLog(productBackLog.getTitle(), 1L);
        assertNotNull(createdPB);
        assertEquals(productBackLog.getTitle(), createdPB.getTitle());
    }

    @Test
    public void TestUpdateProductBackLog() {
        productBackLog = ProductBackLogDto.builder().id(1L).title("PB 1").build();
        when(productBackLogRepository.findById(1L)).thenReturn(Optional.of(ProductBackLogMapper.mapToProductBackLog(productBackLog)));
        ProductBackLogDto PBDto = ProductBackLogDto.builder().id(2L).title("PB Edited").build();
        when(productBackLogRepository.save(any(ProductBackLog.class))).thenReturn(ProductBackLogMapper.mapToProductBackLog(PBDto));
        ProductBackLogDto updatedPB = productBackLogService.updateProductBackLog(1L, PBDto);
        assertNotNull(updatedPB);
        assertEquals(PBDto.getTitle(), updatedPB.getTitle());
    }

    @Test
    public void TestDeleteProductBackLog() {
        when(productBackLogRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productBackLogRepository).deleteById(1L);
        productBackLogService.deleteProductBackLog(1L);
        verify(productBackLogRepository, times(1)).deleteById(1L);
    }

    @Test
    public void TestGetProductBackLog() {
        productBackLog = ProductBackLogDto.builder().id(1L).title("PB 1").build();
        when(productBackLogRepository.findById(1L)).thenReturn(Optional.of(ProductBackLogMapper.mapToProductBackLog(productBackLog)));
        Optional<ProductBackLogDto> foundPB = productBackLogService.getProductBackLog(1L);
        assertNotNull(foundPB);
        assertEquals(productBackLog.getTitle(), foundPB.get().getTitle());
    }

    @Test
    public void TestGetAllProductBackLog() {
        List<ProductBackLog> PBS = Arrays.asList(
                ProductBackLog.builder().title("PB 1").build(),
                ProductBackLog.builder().title("PB 2").build(),
                ProductBackLog.builder().title("PB 3").build()
        );
        when(productBackLogRepository.findAll()).thenReturn(PBS);
        List<ProductBackLogDto> foundPB = productBackLogService.getAllProductBackLog();
        assertNotNull(foundPB);
        assertEquals(PBS.size(), foundPB.size());
        assertEquals(PBS.get(0).getTitle(), foundPB.get(0).getTitle());
        assertEquals(PBS.get(1).getTitle(), foundPB.get(1).getTitle());
        assertEquals(PBS.get(2).getTitle(), foundPB.get(2).getTitle());
    }

    @Test
    public void TestAddUserStoryToPB() {
        productBackLog = ProductBackLogDto.builder().id(1L).title("PB 1").userStories(new ArrayList<>()).build();
        UserStoryDto us = UserStoryDto.builder().id(1L).as_a("Developer").i_wish_to("Develop...").build();
        productBackLog.getUserStories().add(UserStoryMapper.mapToUserStory(us));
        when(productBackLogRepository.findById(1L)).thenReturn(Optional.of(ProductBackLogMapper.mapToProductBackLog(productBackLog)));
        when(userStoryRepository.save(any(UserStory.class))).thenReturn(UserStoryMapper.mapToUserStory(us));
        ProductBackLogDto foundedPB = productBackLogService.addUserStoryToPB(us, 1L);
        assertNotNull(foundedPB);
        assertEquals(productBackLog.getTitle(), foundedPB.getTitle());
        assertEquals(foundedPB.getUserStories().get(0).getAs_a(), us.getAs_a());
    }
    @Test
    public void TestGetUserStoriesByProductBackLog(){
        List<UserStory> uss = Arrays.asList(
                UserStory.builder().title("US 1").as_a("Developer").i_wish_to("develop...").build(),
                UserStory.builder().title("US 2").as_a("Product Manager").i_wish_to("Manage....").build());
        productBackLog = ProductBackLogDto.builder().id(1L).title("PB 1").userStories(uss).build();
        when(productBackLogRepository.findById(1L)).thenReturn(Optional.of(ProductBackLogMapper.mapToProductBackLog(productBackLog)));
        List<UserStoryDto> pbuss = productBackLogService.getUserStoriesByProductBackLog(1L);
        assertNotNull(pbuss);
        assertEquals(pbuss.size(), uss.size());
        assertEquals(pbuss.get(0).getTitle(), uss.get(0).getTitle());
    }
}
