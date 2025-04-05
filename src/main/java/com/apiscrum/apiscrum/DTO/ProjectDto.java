package com.apiscrum.APIScrum.DTO;

import com.apiscrum.APIScrum.Entity.ProductBackLog;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    private ProductBackLog productBackLog;
}
