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
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_backLog", nullable = true)
    private ProductBackLog productBackLog;
    @OneToMany(mappedBy = "associatedProject",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sprint> sprints;

}
