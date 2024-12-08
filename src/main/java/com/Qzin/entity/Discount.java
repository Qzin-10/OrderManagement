package com.Qzin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "discount")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private int discountId;

    @Column(name = "discount_type", nullable = false)
    private String discountType; // Type of discount (e.g., "Percentage", "Flat Amount")

    @Column(name = "discount_value", nullable = false)
    private double discountValue; // Value of the discount (e.g., 20% or 50 units)

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true; // Indicates if the discount is active

    @Column(name = "updated_time")
    private Timestamp updatedTime = new Timestamp(System.currentTimeMillis()); // Timestamp for when the discount was last updated

    @Column(name = "minimum_value", nullable = false)
    private double minimumValue; // Minimum value required for discount applicability

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis()); // Timestamp for when the discount was created

    @PrePersist
    public void updateCreatedAt() {
        if (this.createdAt == null) {
            createdAt = new Timestamp(System.currentTimeMillis());
        }
    }

    @PreUpdate
    public void updateUpdatedTime() {
        this.updatedTime = new Timestamp(System.currentTimeMillis());
    }
}

