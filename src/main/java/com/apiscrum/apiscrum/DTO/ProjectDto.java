package com.apiscrum.apiscrum.DTO;

import com.apiscrum.apiscrum.Entity.ProductBackLog;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    private Long id;
    @NotNull(message = "name of project is required (cannot be null)")
    private String name;
    private String description;
    @JsonIgnore
    private ProductBackLog productBackLog;
}
