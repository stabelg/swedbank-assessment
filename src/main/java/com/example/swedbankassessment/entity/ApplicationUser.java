package com.example.swedbankassessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Builder
@Data
@Table(name = "application_user")
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(cascade = ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private UserDetail userDetail;
}
