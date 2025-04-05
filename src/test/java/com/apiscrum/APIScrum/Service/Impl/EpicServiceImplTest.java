package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.DTO.EpicDto;
import com.apiscrum.APIScrum.DTO.UserStoryDto;
import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Mapper.UserStoryMapper;
import com.apiscrum.APIScrum.Repository.EpicRepository;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Repository.ProductBackLogRepository;
import com.apiscrum.APIScrum.Repository.UserStoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EpicServiceImplTest {

    @Mock
    private EpicRepository epicRepository;
    @Mock
    private ProductBackLogRepository productBackLogRepository;
    @Mock
    private UserStoryRepository userStoryRepository;

    @InjectMocks
    private EpicServiceImpl epicService;
    private Epic epic;
    private EpicDto epicDto;
    private ProductBackLog productBackLog;
    @BeforeEach
    void setUp() {
        epic = new Epic();
        epic.setId(1L);
        epic.setTitle("Epic 1");
        productBackLog = ProductBackLog.builder().id(1L).title("title").build();
        epic.setProductBackLog(productBackLog);
        epicDto = EpicDto.builder()
                .id(1L)
                .title("Epic 1")
                .productBackLog(productBackLog)
                .build();
    }

    @Test
    void testAddEpic() {
        productBackLog = ProductBackLog.builder().id(1L).title("title").build();
        when(epicRepository.save(any(Epic.class))).thenReturn(epic);
        when(productBackLogRepository.findById(1L)).thenReturn(Optional.of(productBackLog));
        EpicDto createdEpic = epicService.addEpic(epicDto,1L); //1 id dial pb
        assertNotNull(createdEpic);
        assertEquals(epicDto.getTitle(), createdEpic.getTitle());
    }
    @Test
    void testGetEpic(){
        Epic epic = new Epic();
        epic.setId(1L);
        epic.setTitle("Test Epic");
        Mockito.when(epicRepository.findById(1L)).thenReturn(Optional.of(epic));
        EpicDto result = epicService.getEpic(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Epic", result.getTitle());
    }


    @Test
    void testDeleteEpic() {
        Epic epic = Epic.builder().id(1L).build();
        when(epicRepository.existsById(1L)).thenReturn(true);
        doNothing().when(epicRepository).deleteById(1L);
        epicService.deleteEpic(1L);
        verify(epicRepository,times(1)).deleteById(1L);
    }

    @Test
    void testGetEpics() {
        List<Epic> epics = Arrays.asList(
                Epic.builder().title("Epic 1").build(),
                Epic.builder().title("Epic 2").build(),
                Epic.builder().title("Epic 3").build());
        Mockito.when(epicRepository.findAll()).thenReturn(epics);
        List<EpicDto> foundEpics = epicService.getEpics();
        assertNotNull(epics);
        assertEquals(3, foundEpics.size());
        assertEquals("Epic 1", foundEpics.get(0).getTitle());
        assertEquals("Epic 2", foundEpics.get(1).getTitle());
        assertEquals("Epic 3", foundEpics.get(2).getTitle());
    }

    @Test
    public void testAddUserStoryToEpic(){
        UserStoryDto us = UserStoryDto.builder().title("US 1").as_a("Developer").i_wish_to("develop...").build();
        Epic epic = Epic.builder().title("Epic 1").userStories(new ArrayList<>()).build();
        when(epicRepository.findById(1L)).thenReturn(Optional.of(epic));
        EpicDto updatedEpic = epicService.addUserStoryToEpic(us, 1L);

        assertNotNull(updatedEpic);
        assertEquals(1, updatedEpic.getUserStories().size());
        assertEquals("US 1", updatedEpic.getUserStories().get(0).getTitle());
        assertEquals("Developer", updatedEpic.getUserStories().get(0).getAs_a());
    }

    @Test
    public void testGetUserEpicStories(){
        List<UserStory> uss = Arrays.asList(
                UserStory.builder().title("US 1").as_a("Developer").i_wish_to("develop...").build(),
                UserStory.builder().title("US 2").as_a("Product Manager").i_wish_to("Manage....").build());
        Epic epic = Epic.builder().id(1L).title("Test Epic").userStories(uss).build();
        when(epicRepository.findById(1L)).thenReturn(Optional.of(epic));
        List<UserStoryDto> foundUserStories = epicService.getUserEpicStories(1L);
        assertNotNull(foundUserStories);
        assertEquals(2, foundUserStories.size());
        assertEquals("US 1", foundUserStories.get(0).getTitle());
        assertEquals("Developer", foundUserStories.get(0).getAs_a());
        assertEquals("Manage....", foundUserStories.get(1).getI_wish_to());
    }

}
