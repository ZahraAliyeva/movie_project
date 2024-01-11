package com.company.LetterboxdProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "countries")
    private String country;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private Set<Release> releases;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_flag_photo", referencedColumnName = "id")
    private CountryFlagPhoto countryFlagPhotos;

    @ManyToMany(mappedBy = "countries")
    private Set<Film> films;
}
