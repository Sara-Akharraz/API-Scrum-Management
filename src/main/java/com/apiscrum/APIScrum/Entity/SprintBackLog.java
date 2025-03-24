package com.apiscrum.APIScrum.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sprint_backlog")
public class SprintBackLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "sprintBacklog_id")
    private List<UserStory> userStoriesList;

    @OneToOne
    @JoinColumn(name = "associated_sprint_id", nullable = false)
    private Sprint associatedSprint;


}
