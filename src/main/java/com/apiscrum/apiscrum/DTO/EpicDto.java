package com.apiscrum.apiscrum.DTO;

import com.apiscrum.apiscrum.Entity.ProductBackLog;
import com.apiscrum.apiscrum.Entity.UserStory;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpicDto {
    private Long id;
    @NotNull(message="Title of Epic is required (connot be null)")
    private String title;
    private String description;
    @JsonIgnore
    private List<UserStory> userStories;
    @JsonIgnore
    private ProductBackLog productBackLog;
}
