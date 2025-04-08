package com.apiscrum.apiscrum.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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


    @OneToMany(mappedBy = "sprintBackLog",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserStory> userStoriesList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "associated_sprint_id",referencedColumnName = "id",nullable = false)
    private Sprint associatedSprint;


}
