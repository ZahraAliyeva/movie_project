package com.company.LetterboxdProject.util;

import com.company.LetterboxdProject.dto.FilmFilterRequest;
import com.company.LetterboxdProject.entity.Film;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmSpecification {

    public Specification<Film> filterFilmByCastAndCrew(FilmFilterRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(request.getGenre() != null && !request.getGenre().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("genre_id")), "%" + request.getGenre() + "%"));
            }

            if(request.getTheme() != null && !request.getTheme().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("theme_id")), "%" + request.getTheme() + "%"));
            }

            if(request.getLanguage() != null && !request.getLanguage().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("language_id")), "%" + request.getLanguage() + "%"));
            }

            if(request.getReleaseYear() != null) {
                predicates.add(criteriaBuilder.equal(root.get("release_year"), request.getReleaseYear()));
            }

            if(request.getRunningTime() != null) {
                predicates.add(criteriaBuilder.equal(root.get("running_time"), request.getRunningTime()));
            }

            query.orderBy(criteriaBuilder.desc(root.get("film_id")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
