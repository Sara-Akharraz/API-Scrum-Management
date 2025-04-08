package com.apiscrum.apiscrum.DTO;

import com.apiscrum.apiscrum.Entity.*;
import com.apiscrum.apiscrum.enums.UserStoryPriority;
import com.apiscrum.apiscrum.enums.UserStoryProgress;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStoryDto {
    @NotNull
    private Long id;
    @NotBlank(message = "Title cannot be null")
    private String title;
    @NotBlank(message = "'as a' cannot be null")
    private String as_a;
    @NotBlank(message = "'i wish to' cannot be null")
    private String i_wish_to;
    @NotBlank(message = "'in order to' cannot be null")
    private String in_order_to;
    private UserStoryPriority priority;
    @JsonIgnore
    private ProductBackLog productBackLog;
    @JsonIgnore
    private Epic epic;
    @JsonIgnore
    private SprintBackLog sprintBackLog;
    @JsonIgnore
    private List<Task> tasks;
    @JsonIgnore
    private List<TestAcceptance> test_acceptanceList;

    private UserStoryProgress progress;
}
