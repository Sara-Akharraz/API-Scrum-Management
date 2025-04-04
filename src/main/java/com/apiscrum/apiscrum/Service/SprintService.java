package com.apiscrum.apiscrum.Service;

import com.apiscrum.apiscrum.Entity.Sprint;

import java.util.List;

public interface SprintService {
    public Sprint addSprint(Sprint sprint);
    public Sprint updateSprint(Long id, Sprint UpdatedSprint);
    public void deleteSprint(Long id);
    public List<Sprint> getAllSprints();
    public List<Sprint> getAllSprintsByProject(Long id_project);
    public Sprint getSprintById(Long id);
}
