package com.apiscrum.APIScrum.DTO;

import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.enums.UserStoryPriority;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStoryDto {
    private Long id;
    @NotNull(message = "Title cannot be null")
    private String title;
    @NotNull(message = "'as a' cannot be null")
    private String as_a;
    @NotNull(message = "'i wish to' cannot be null")
    private String i_wish_to;
    private String in_order_to;
    private UserStoryPriority priority;
    private ProductBackLog productBackLog;
    private Epic epic;
}
