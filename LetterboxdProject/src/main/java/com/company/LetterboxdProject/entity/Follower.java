package com.company.LetterboxdProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "followers")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_user")
    private User from;

    @ManyToOne
    @JoinColumn(name = "to_user")
    private User to;

    @Column(name = "following_date")
    private LocalDate followingDate;

}