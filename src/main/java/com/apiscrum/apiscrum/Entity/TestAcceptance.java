package com.apiscrum.apiscrum.Entity;

import com.apiscrum.apiscrum.enums.TestAcceptanceState;
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
public class TestAcceptance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="scenario",nullable = false)
    private String  scenario;
    @Column(name="given_",nullable = false)
    private String given;
    @Column(name="when_",nullable = false)
    private String when;
    @Column(name="then_",nullable = false)
    private String then;
    @Column(name="and_")
    private String and;
    @Column(name="but_")
    private String but;
    @ManyToOne
    @JoinColumn(name="user_story_id",nullable=false)
    private UserStory associatedUserStory;

    @Enumerated(EnumType.STRING)
    private TestAcceptanceState state;


}
