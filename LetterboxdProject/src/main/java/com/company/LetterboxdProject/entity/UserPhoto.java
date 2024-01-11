package com.company.LetterboxdProject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user_photos")
public class UserPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "uuid")
    private String uuid;

    @Lob
    @Column(name = "user_profile_photo_url")
    private String userProfilePhotoUrl;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    public UUID getUuid(){
        return UUID.fromString(this.uuid);
    }

    public void setUuid(UUID uuid){
        this.uuid = uuid.toString();
    }
}
