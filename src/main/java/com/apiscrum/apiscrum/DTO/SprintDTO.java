package com.apiscrum.apiscrum.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SprintDTO {
    @NotNull(message = "ID can't be null!")
    private Long id;
    @NotBlank(message="Title can't be blank!")
    private String sprint_title;
    @NotBlank()
    private String duration;

    private Date start_date;

    private Date  end_date;
    @NotBlank()
    private String description;
    private Long sprintBackLogId;
    @NotNull(message = "Project id can't be null!")
    private Long associatedProjectId;

}
