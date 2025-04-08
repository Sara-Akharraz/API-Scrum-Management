package com.apiscrum.apiscrum.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @OneToMany(mappedBy="productBackLog",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserStory> userStories;
    @ManyToOne
    @JoinColumn(name = "project", nullable = false)
    private Project project;
}
