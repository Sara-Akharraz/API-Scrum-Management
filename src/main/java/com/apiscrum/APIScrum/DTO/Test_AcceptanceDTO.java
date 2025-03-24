package com.apiscrum.APIScrum.DTO;

import com.apiscrum.APIScrum.enums.Test_AcceptanceState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Test_AcceptanceDTO {
    @NotNull(message="ID can't be null!")
    private Long id;
    @NotBlank()
    private String scenario;
    @NotBlank()
    private String given;
    @NotBlank()
    private String when;
    @NotBlank()
    private String then;
    @NotBlank()
    private String and;
    @NotBlank()
    private String but;
    @NotBlank()
    private Test_AcceptanceState state;
    @NotNull(message="User Story ID can't be null!")
    private Long associatedUserStory;

}
