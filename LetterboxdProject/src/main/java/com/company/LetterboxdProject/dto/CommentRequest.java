package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentRequest {

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("spoilers")
    private Boolean spoilers;

    @JsonProperty("comment_tags")
    private Set<String> tags;
}
