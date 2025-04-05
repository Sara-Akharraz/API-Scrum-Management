package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.DTO.EpicDto;
import com.apiscrum.APIScrum.DTO.ProductBackLogDto;
import com.apiscrum.APIScrum.DTO.ProjectDto;
import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.Project;
import com.apiscrum.APIScrum.Mapper.ProductBackLogMapper;
import com.apiscrum.APIScrum.Mapper.ProjectMapper;
import com.apiscrum.APIScrum.Repository.ProductBackLogRepository;
import com.apiscrum.APIScrum.Repository.ProjectRepository;
import com.apiscrum.APIScrum.Service.ProjectService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceImplTest {
    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProductBackLogRepository productBackLogRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    public void testAddProject() {
        Project mockProject = Project.builder()
                .id(1l)
                .name("Namy")
                .description("description of test")
                .build();
        when(projectRepository.save(Mockito.any(Project.class))).thenReturn(mockProject);
        ProjectDto result = projectService.addProject(ProjectMapper.mapToProjectDTO(mockProject));
        assertNotNull(result);
        assertEquals("Namy", result.getName());
        assertEquals("description of test", result.getDescription());
    }


    @Test
    public void TestGetProject() {
        Project mockProject = Project.builder()
                .id(1l)
                .name("Namy")
                .description("description of test")
                .build();
        when(projectRepository.findById(1L)).thenReturn(Optional.of(mockProject));
        ProjectDto foundedProject = projectService.getProject(1l);
        assertNotNull(foundedProject);
        assertEquals("Namy", foundedProject.getName());
    }

    @Test
    public void TestGetProjectByProductBackLog() {
        Project mockProject = Project.builder()
                .id(1l)
                .name("Namy")
                .description("description of test")
                .build();
        ProductBackLog productBackLog = ProductBackLog.builder()
                .id(1L).title("PB 1").userStories(new ArrayList<>())
                .project(mockProject).build();
        when(productBackLogRepository.findById(1L)).thenReturn(Optional.of(productBackLog));
        Optional<ProjectDto> foundedProjet = projectService.getProjectByProductBackLog(1L);
        assertNotNull(foundedProjet);
        assertEquals("Namy", foundedProjet.get().getName());
    }

    @Test
    public void TestDeleteProject(){
        when(projectRepository.existsById(1L)).thenReturn(true);
        doNothing().when(projectRepository).deleteById(1L);
        projectService.deleteProject(1L);
        verify(projectRepository, times(1)).deleteById(1L);
    }
    @Test
    public void TestGetProjects(){
        List<Project> projects = Arrays.asList(
                Project.builder().name("Epic 1").build(),
                Project.builder().name("Epic 2").build(),
                Project.builder().name("Epic 3").build());
        when(projectRepository.findAll()).thenReturn(projects);
        List<ProjectDto> foundEpics = projectService.getProjects();
        assertNotNull(projects);
        assertEquals(3, foundEpics.size());
        assertEquals("Epic 1", foundEpics.get(0).getName());
        assertEquals("Epic 2", foundEpics.get(1).getName());
        assertEquals("Epic 3", foundEpics.get(2).getName());
    }
}
