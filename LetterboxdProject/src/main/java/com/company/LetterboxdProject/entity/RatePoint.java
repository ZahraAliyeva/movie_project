package com.company.LetterboxdProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "rate_points")
public class RatePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rate")
    private Double rate;

    @OneToMany(mappedBy = "ratePoint",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<FilmRateHistory> filmRateHistories;


}
