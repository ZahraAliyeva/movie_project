package com.company.LetterboxdProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "cast_and_crew")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CastAndCrew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "bio")
    private String bio;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cast_and_crew_photo", referencedColumnName = "id")
    private CastAndCrewPhoto castAndCrewPhoto;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "link_id", referencedColumnName = "id")
    private CastAndCrewTmdbLink castAndCrewTmdbLink;

    @OneToMany(mappedBy = "castAndCrew", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FilmCastAndCrew> filmCastAndCrews;
}