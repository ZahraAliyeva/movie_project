package com.company.LetterboxdProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "country_flag_photos")
public class CountryFlagPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "countryFlagPhotos")
    @JsonIgnore
    private Country country;

    @Column(name = "country_flag_photo")
    private String countryFlagPhotoUrl;
}
