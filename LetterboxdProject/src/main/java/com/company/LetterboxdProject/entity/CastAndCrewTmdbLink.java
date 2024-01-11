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
@Table(name = "cast_and_crew_tmdb_link")
public class CastAndCrewTmdbLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cast_and_crew_tmdb_link")
    private String castAndCrewTmdbLink;

    @OneToOne(mappedBy = "castAndCrewTmdbLink")
    @JsonIgnore
    private CastAndCrew castAndCrew;

    public CastAndCrewTmdbLink(String castAndCrewTmdbLink) {
        this.castAndCrewTmdbLink = castAndCrewTmdbLink;
    }
}
