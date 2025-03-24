package com.apiscrum.APIScrum.DTO;

import com.apiscrum.APIScrum.enums.TaskProgress;
import com.apiscrum.APIScrum.enums.TaskTags;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {
    @NotNull(message="ID can't be null!")
    private Long id;
    @NotBlank(message="Title can't be blank!")
    private String taskTitle;
    @NotBlank()
    private String description;
    @NotBlank()
    private TaskTags tag;
    @NotBlank()
    private TaskProgress progress;
    @NotNull(message="User Story id is required!")
    private Long associatedUserStoryId;
    @NotNull(message="Task owner can't be null!")
    private Long taskOwner;

}
