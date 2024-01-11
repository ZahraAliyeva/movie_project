package com.company.LetterboxdProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "film_lists")
@AllArgsConstructor
@NoArgsConstructor
public class FilmList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "list_name")
    private String listName;

    @Column(name = "list_description")
    private String listDescription;

    @Column(name = "create_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "ranked")
    private Boolean ranked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_list_visible_to_id")
    private FilmListVisibleTo filmListVisibleTo;

    @OneToMany(mappedBy = "filmLists", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<FilmListLikeHistory> filmListLikeHistories;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "films_lists",
            joinColumns = @JoinColumn(name = "list_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
    private Set<Film> films;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tags_list",
            joinColumns = @JoinColumn(name = "list_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tags> tags;
}