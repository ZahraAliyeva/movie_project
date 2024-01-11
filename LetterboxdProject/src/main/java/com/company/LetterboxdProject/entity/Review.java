package com.company.LetterboxdProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review")
    private String review;

    @Column(name = "review_date")
    private LocalDate reviewDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "spoilers")
    private Boolean spoilers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;

    @OneToMany(mappedBy = "reviews", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ReviewComment> comments;

    @OneToMany(mappedBy = "reviews", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ReviewLikeHistory> reviewLikeHistories;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "review_tags",
            joinColumns = @JoinColumn(name = "review_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tags> tags;
}
