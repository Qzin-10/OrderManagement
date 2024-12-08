package com.Qzin.entity;



import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuId;

    @Column(name = "menu_uuid", nullable = false)
    private String menuUUID = String.valueOf(UUID.randomUUID());

    @ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Item> itemsList;

    @Column(updatable = false)
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
}
