package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewRequest {

    @JsonProperty("review")
    private String review;

    @JsonProperty("spoilers")
    private Boolean spoilers;

    @JsonProperty("review_tags")
    private Set<String> tags;
}
