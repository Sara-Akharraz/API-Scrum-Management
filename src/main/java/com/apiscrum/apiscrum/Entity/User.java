package com.apiscrum.apiscrum.Entity;

import com.apiscrum.apiscrum.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name="passwd",nullable = false)
    private String passwd;

    @Enumerated(EnumType.STRING)
    @Column(name="role",nullable = false)
    private Role role;


}
