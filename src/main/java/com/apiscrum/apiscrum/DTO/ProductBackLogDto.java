package com.apiscrum.APIScrum.DTO;

import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.Project;
import com.apiscrum.APIScrum.Entity.UserStory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductBackLogDto {
    private Long id;
    @NotNull(message = "Title of product backlog is required (cannot be null)")
    private String title;
    private List<Epic> epics;
    @Builder.Default
    private List<UserStory> userStories = new ArrayList<>();
    private Project project;
}
