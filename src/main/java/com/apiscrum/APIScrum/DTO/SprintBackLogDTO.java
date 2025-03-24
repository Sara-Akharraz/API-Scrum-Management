package com.apiscrum.APIScrum.DTO;

import com.apiscrum.APIScrum.Entity.Sprint;
import com.apiscrum.APIScrum.Entity.SprintBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    @NotNull(message="Sprint ID can't be null!")
    private Long associatedSprintId;

}
