package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.Entity.Project;
import com.apiscrum.APIScrum.Entity.Sprint;
import com.apiscrum.APIScrum.Entity.SprintBackLog;

import java.util.Date;
import java.util.List;

public interface SprintService {
    public Sprint addSprint(Sprint sprint);
    public Sprint updateSprint(Long id, Sprint UpdatedSprint);
    public void deleteSprint(Long id);
    public List<Sprint> getAllSprints();
    public List<Sprint> getAllSprintsByProject(Long id_project);
    public Sprint getSprintById(Long id);
}
