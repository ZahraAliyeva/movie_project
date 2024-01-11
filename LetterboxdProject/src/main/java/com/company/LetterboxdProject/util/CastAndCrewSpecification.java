package com.company.LetterboxdProject.util;

import com.company.LetterboxdProject.dto.CastAndCrewRequest;
import com.company.LetterboxdProject.entity.CastAndCrew;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CastAndCrewSpecification {

    public Specification<CastAndCrew> filterCastAndCrew(CastAndCrewRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(request.getName() != null && !request.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
            }

            if(request.getSurname() != null && !request.getSurname().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("surname")), "%" + request.getSurname().toLowerCase() + "%"));
            }
            query.orderBy(criteriaBuilder.desc(root.get("name")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
