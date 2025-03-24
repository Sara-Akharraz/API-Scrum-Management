package com.apiscrum.APIScrum.Entity;

import com.apiscrum.APIScrum.enums.Role;
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

    @Column(name="name")
    private String name;
    @Column(name="email")
    private String Email;
    @Column(name="passwd")
    private String passwd;

    @Enumerated
    @Column(name="role")
    private Role role;






}
