package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user")
    private Long user;

    @JsonProperty("user_rate")
    private Double rate;

    @JsonProperty("comment_date")
    private LocalDate commentDate;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("spoilers")
    private Boolean spoilers;

    @JsonProperty("review_tags")
    private Set<String> tags;

}
