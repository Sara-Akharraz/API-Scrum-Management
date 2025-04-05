package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.DTO.EpicDto;
import com.apiscrum.APIScrum.DTO.ProductBackLogDto;
import com.apiscrum.APIScrum.DTO.ProjectDto;
import com.apiscrum.APIScrum.DTO.UserStoryDto;
import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.Project;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Mapper.ProductBackLogMapper;
import com.apiscrum.APIScrum.Mapper.ProjectMapper;
import com.apiscrum.APIScrum.Mapper.UserStoryMapper;
import com.apiscrum.APIScrum.Repository.EpicRepository;
import com.apiscrum.APIScrum.Repository.ProductBackLogRepository;
import com.apiscrum.APIScrum.Repository.ProjectRepository;
import com.apiscrum.APIScrum.Repository.UserStoryRepository;
import com.apiscrum.APIScrum.Service.ProductBackLogService;
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
        ProductBackLogDto createdPB = productBackLogService.addProductBackLog(productBackLog, 1L);
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

}
