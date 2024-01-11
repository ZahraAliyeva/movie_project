package com.company.LetterboxdProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cast_and_crew_photo")
public class CastAndCrewPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "cast_and_crew_photo_url")
    private String castAndCrewPhotoUrl;

    @OneToOne(mappedBy = "castAndCrewPhoto")
    @JsonIgnore
    private CastAndCrew castAndCrew;

    public CastAndCrewPhoto(String castAndCrewPhotoUrl) {
        this.castAndCrewPhotoUrl = castAndCrewPhotoUrl;
    }
}
