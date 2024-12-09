package com.Qzin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "user_uuid", nullable = false, unique = true, updatable = false)
    private String user_uuid = String.valueOf(UUID.randomUUID());

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false)
    private LocalDateTime updated_at = LocalDateTime.now();

    @PrePersist
    public void updateCreatedAt() {
        if(this.createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void updateUpdatedAt() {
        this.updated_at = LocalDateTime.now();
    }
}
