package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("location")
    private String location;

    @JsonProperty("website")
    private String website;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("fav_films")
    private Set<Long> favFilms;

    @JsonProperty("recent_likes")
    private Set<Long> recentLikes;
}
