package com.apiscrum.APIScrum.DTO;

import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;
import jakarta.persistence.*;
import java.util.List;
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
    private List<UserStory> userStories;
    private ProductBackLog productBackLog;
}
