package com.apiscrum.APIScrum.Entity;

import com.apiscrum.APIScrum.enums.Test_AcceptanceState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="test_acceptance")
public class Test_Acceptance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="scenario",nullable = false)
    private String  Scenario;
    @Column(name="given",nullable = false)
    private String Given;
    @Column(name="when",nullable = false)
    private String When;
    @Column(name="then",nullable = false)
    private String Then;
    @Column(name="and",nullable = true)
    private String And;
    @Column(name="but",nullable = true)
    private String But;
    @ManyToOne
    @JoinColumn(name="user_story_id",nullable=false)
    private UserStory associatedUserStory;

    @Enumerated(EnumType.STRING)
    private Test_AcceptanceState state;


}
