package com.Qzin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int itemId;

    @Column(name = "item_uuid", nullable = false, unique = true)
    private String itemUUID = String.valueOf(UUID.randomUUID());

    @ManyToOne
    @JoinColumn(name = "itemmeta_id", nullable = false)
    private ItemMetaData itemMeta;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false, unique = true)
    private Menu menu;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @Column(name = "serving_quantity", nullable = false)
    private String servingQuantity;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt = new Date();

    @PrePersist
    public void updateCreatedAt() {
        if(this.createdAt == null) {
            createdAt = new Date();
        }
    }

    @PreUpdate
    public void updateUpdatedAt() {
        this.updatedAt = new Date();
    }
}
