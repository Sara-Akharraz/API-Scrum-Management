package com.apiscrum.APIScrum.Entity;

import com.apiscrum.APIScrum.enums.UserStoryPriority;
import com.apiscrum.APIScrum.enums.UserStoryProgress;
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
@Table(name="user_story")
public class UserStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="as_a")
    private String as_a;
    @Column(name="i_wish_to")
    private String i_wish_to;
    @Column(name="in_order_to")
    private String in_order_to;
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private UserStoryPriority priority;
    @ManyToOne
    @JoinColumn(name="product_backLog")
    private ProductBackLog productBackLog;
    @ManyToOne
    @JoinColumn(name="epic", nullable = true)
    private Epic epic;
    @ManyToOne
    @JoinColumn(name="sprintBackLog",nullable = true)
    private SprintBackLog sprintBackLog;

    @OneToMany(mappedBy = "task_id")
    private List<Task> tasks;

    @OneToMany(mappedBy = "test_acceptance_id")
    private List<Test_Acceptance> test_acceptanceList;
    @Column(name="testing_progress")
    private UserStoryProgress progress;

}
