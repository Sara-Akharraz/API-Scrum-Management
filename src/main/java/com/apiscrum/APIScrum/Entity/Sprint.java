package com.apiscrum.APIScrum.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sprint")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String sprint_title;
    @Column(name="duration")
    private String duration;
    @Column(name="start_date")
    private Date  start_date;
    @Column(name="end_date")
    private Date  end_date;
    @Column(name="description")
    private String description;
    @OneToOne
    @JoinColumn(name="associated_sprintBackLog_id",nullable=true)
    private SprintBackLog sprintBackLog;
    @ManyToOne
    @JoinColumn(name="associated_project_id",nullable=false)
    private Project associatedProject;

}
