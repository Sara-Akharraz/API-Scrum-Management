package com.apiscrum.apiscrum.Entity;

import com.apiscrum.apiscrum.enums.UserStoryPriority;
import com.apiscrum.apiscrum.enums.UserStoryProgress;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @JoinColumn(name="sprintBackLog")
    private SprintBackLog sprintBackLog;

    @OneToMany(mappedBy = "associatedUserStory")
    @JsonManagedReference
    private List<Task> tasks;

    @OneToMany(mappedBy = "associatedUserStory")
    @JsonManagedReference
    private List<TestAcceptance> test_acceptanceList;

    @Enumerated(EnumType.STRING)
    @Column(name="testing_progress")
    private UserStoryProgress progress;

}
