package com.apiscrum.APIScrum.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
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
}
