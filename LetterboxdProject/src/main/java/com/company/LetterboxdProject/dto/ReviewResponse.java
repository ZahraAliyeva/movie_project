package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user")
    private Long user;

    @JsonProperty("user_rate")
    private Double rate;

    @JsonProperty("watched_date")
    private LocalDate watchedDate;

    @JsonProperty("review")
    private String review;

    @JsonProperty("spoilers")
    private Boolean spoilers;

    @JsonProperty("review_tags")
    private Set<String> tags;

    @JsonProperty("number_of_like")
    private Long numberOfLikes;
}
