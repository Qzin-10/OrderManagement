package com.Qzin.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "itemmeta")
public class ItemMetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemmeta_id")
    private int itemMetaDataId;

    @Column(name = "itemmeta_uuid", nullable = false)
    private String itemMetaDataUUID = String.valueOf(UUID.randomUUID());

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageURL;

    @Column(name = "video_url", nullable = true)
    private String videoURL;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "veg_status", nullable = false)
    private boolean vegStatus;

    @Column(name = "category", nullable = false)
    private String category;

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
}
