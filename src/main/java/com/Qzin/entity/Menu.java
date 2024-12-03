package com.Qzin.entity;



import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuId;

    @ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @ManyToOne
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private Inventory inventory;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable = true;

    @Column(updatable = false)
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @PrePersist
    public void prePersist() {
        if (this.price == null) {
            this.price = BigDecimal.valueOf(100.00);
        }
    }
}
