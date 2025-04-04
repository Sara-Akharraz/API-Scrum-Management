package com.apiscrum.apiscrum.DTO;

import com.apiscrum.apiscrum.enums.TestAcceptanceState;
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
public class TestAcceptanceDTO {
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
    @NotNull()
    private TestAcceptanceState state;
    @NotNull(message="User Story ID can't be null!")
    private Long associatedUserStory;

}
