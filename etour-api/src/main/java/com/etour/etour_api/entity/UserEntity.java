package com.etour.etour_api.entity;

import com.etour.etour_api.enumeration.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @project etour-api
 * @since 27-01-2025
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends Auditable {
    @Column(unique = true, nullable = false, updatable = false)
    private String userId;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String middleName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(length = 100, unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Column(length = 20)
    private String phone;
    private String bio;
    private String imageUrl;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private LocalDateTime lastLogin;
    private Integer loginAttempts;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
}

