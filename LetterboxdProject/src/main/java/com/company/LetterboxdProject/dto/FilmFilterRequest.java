package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmFilterRequest {

    private String genre;

    private String theme;

    private Integer runningTime;

    private Integer releaseYear;

    private String language;

}
