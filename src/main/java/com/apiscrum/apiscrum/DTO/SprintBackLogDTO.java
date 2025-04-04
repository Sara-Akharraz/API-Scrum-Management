package com.apiscrum.apiscrum.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SprintBackLogDTO {
    @NotNull(message="ID can't be null!")
    private Long id;
    private List<Long> userStoriesList;

    private Long associatedSprintId;

}
