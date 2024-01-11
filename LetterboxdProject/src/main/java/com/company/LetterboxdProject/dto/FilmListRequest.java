package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmListRequest {

    @JsonProperty("list_name")
    private String listName;

    @JsonProperty("list_description")
    private String listDescription;

    @JsonProperty("ranked")
    private Boolean ranked;

    @JsonProperty("list_tags")
    private Set<String> tags;

    @JsonProperty("film_list_visible_to_id")
    private Long filmListVisibleTo;

    @JsonProperty("films")
    private Set<Long> films;
}
