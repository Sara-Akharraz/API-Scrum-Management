package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.DTO.ProjectDto;
import com.apiscrum.APIScrum.DTO.UserStoryDto;
import com.apiscrum.APIScrum.Entity.Project;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Repository.UserStoryRepository;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserStoryServiceImplTest {
    @Mock
    private UserStoryRepository userStoryRepository;
    @InjectMocks
    private UserStoryServiceImpl userStoryService;

    @Test
    public void TestDeleteUserStory(){
        when(userStoryRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userStoryRepository).deleteById(1L);
        userStoryService.deleteUserStory(1L);
        verify(userStoryRepository, times(1)).deleteById(1L);
    }

    @Test
    public void TesGetUserStory(){
        UserStory us = UserStory.builder()
                .id(1l)
                .as_a("Developer")
                .i_wish_to("Develop...")
                .build();
        when(userStoryRepository.findById(1L)).thenReturn(Optional.of(us));
        UserStoryDto foundedUS= userStoryService.getUserStory(1l);
        assertNotNull(foundedUS);
        assertEquals("Developer", foundedUS.getAs_a());
    }


}
