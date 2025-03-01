package com.apiscrum.APIScrum.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="product_backlog")
public class ProductBackLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;
    @OneToMany(mappedBy = "productBackLog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Epic> epics;
    @OneToMany(mappedBy="productBackLog")
    private List<UserStory> userStories;
    @ManyToOne
    @JoinColumn(name = "project", nullable = false)  // Use the appropriate column name
    private Project project;
}
