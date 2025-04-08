package com.apiscrum.apiscrum.Service.Impl;


import com.apiscrum.apiscrum.DTO.UserStoryDto;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Repository.UserStoryRepository;
import com.apiscrum.apiscrum.enums.UserStoryPriority;
import com.apiscrum.apiscrum.enums.UserStoryProgress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
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
    UserStory userStory;
    @BeforeEach
    public void setUp(){
        userStory=new UserStory(
                1L,
                "FREE PALESTINE",
                "user",
                "have a site",
                "publish information about our Palestinian brothers and sisters and what they are going through.",
                UserStoryPriority.HAVE_TO,
                null,
                null,
                null,
                null,
                null,
                UserStoryProgress.Passed);
    }

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
//    @Test
//    public void TestAddUserStory(){
//        when(userStoryRepository.save(any(UserStory.class))).thenReturn(userStory);
//        UserStory createdUserStory=userStoryService.addUserStory(userStory);
//        assertNotNull(createdUserStory);
//        assertEquals("FREE PALESTINE",createdUserStory.getTitle());
//    }
    @Test
    public void TestUpdateUserStory(){
        UserStory updatedUserStory=new UserStory(
                1L,
                "FREE PALESTINE",
                "user",
                "have a site",
                "share stories and raise awareness about the struggles of our Palestinian brothers and sisters.",
                UserStoryPriority.HAVE_TO,
                null,
                null,
                null,
                null,
                null,
                UserStoryProgress.Passed);
        when(userStoryRepository.findById(1L)).thenReturn(Optional.of(userStory));
        when(userStoryRepository.save(any(UserStory.class))).thenReturn(updatedUserStory);
        userStoryService.updateUserStory(1L,updatedUserStory);
        verify(userStoryRepository,times(1)).save(any(UserStory.class));

    }
    @Test
    public void TestgetAllUserStories(){
        List<UserStory> userStories=Arrays.asList(userStory,
                new UserStory(
                        1L,
                        "FREE PALESTINE",
                        "user",
                        "have a site",
                        "to speak about what is happening in Gaza.",
                        UserStoryPriority.HAVE_TO,
                        null,
                        null,
                        null,
                        null,
                        null,
                        UserStoryProgress.To_Be_Tested)
                );
        when(userStoryRepository.findAll()).thenReturn((userStories));
        List<UserStory> testUserStories=userStoryService.getAllUserStories();
        assertEquals(2,testUserStories.size());
        verify(userStoryRepository,times(1)).findAll();
    }
}
