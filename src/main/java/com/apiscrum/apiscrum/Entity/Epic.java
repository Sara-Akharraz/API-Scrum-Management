package com.apiscrum.apiscrum.Entity;

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
@Table(name="epic")
public class Epic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @OneToMany(mappedBy = "epic", cascade = CascadeType.ALL)
    private List<UserStory> userStories;
    @ManyToOne
    @JoinColumn(name = "product_backLog")
    private ProductBackLog productBackLog;

}
