package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.Entity.Sprint;
import com.apiscrum.APIScrum.Repository.SprintRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SprintServiceImplTest {

    @Mock
    private SprintRepository sprintRepository;

    @InjectMocks
    private SprintServiceImpl sprintService;

    private Sprint sprint1;
    private Sprint sprint2;

    @BeforeEach
    void setUp() {
        // Sample Sprint objects
        sprint1 = new Sprint();
        sprint1.setId(1L);
        sprint1.setDuration(String.valueOf(10));

        sprint2 = new Sprint();
        sprint2.setId(2L);
        sprint2.setDuration(String.valueOf(20));
    }

    @Test
    void addSprintTest() {
        when(sprintRepository.save(sprint1)).thenReturn(sprint1);

        Sprint savedSprint = sprintService.addSprint(sprint1);

        assertNotNull(savedSprint);
        assertEquals(sprint1.getId(), savedSprint.getId());
        verify(sprintRepository, times(1)).save(sprint1);
    }

    @Test
    void UpdateSprintTest() {
        Sprint updatedSprint = new Sprint();
        updatedSprint.setDuration(String.valueOf(30));

        when(sprintRepository.findById(sprint1.getId())).thenReturn(Optional.of(sprint1));

        sprintService.updateSprint(sprint1.getId(), updatedSprint);

        assertEquals(String.valueOf(30), sprint1.getDuration());
        verify(sprintRepository, times(1)).findById(sprint1.getId());
    }

    @Test
    void deleteSprintTest() {
        when(sprintRepository.existsById(sprint1.getId())).thenReturn(true);
        doNothing().when(sprintRepository).deleteById(sprint1.getId());

        sprintService.deleteSprint(sprint1.getId());

        verify(sprintRepository, times(1)).existsById(sprint1.getId());
        verify(sprintRepository, times(1)).deleteById(sprint1.getId());
    }

    @Test
    void getAllSprintsTest() {
        List<Sprint> expectedSprints = Arrays.asList(sprint1, sprint2);

        when(sprintRepository.findAll()).thenReturn(expectedSprints);

        List<Sprint> result = sprintService.getAllSprints();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(sprintRepository, times(1)).findAll();
    }

    @Test
    void getAllSprintsByProjectTest() {
        Long projectId = 100L;
        List<Sprint> expectedSprints = Arrays.asList(sprint1, sprint2);

        when(sprintRepository.findByProjectId(projectId)).thenReturn(expectedSprints);

        List<Sprint> result = sprintService.getAllSprintsByProject(projectId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(sprintRepository, times(1)).findByProjectId(projectId);
    }

    @Test
    void getSprintByIdTest() {
        when(sprintRepository.findById(sprint1.getId())).thenReturn(Optional.of(sprint1));

        Sprint result = sprintService.getSprintById(sprint1.getId());

        assertNotNull(result);
        assertEquals(sprint1.getId(), result.getId());
        verify(sprintRepository, times(1)).findById(sprint1.getId());
    }


}
