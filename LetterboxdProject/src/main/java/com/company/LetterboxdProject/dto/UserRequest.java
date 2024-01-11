package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("location")
    private String location;

    @JsonProperty("website")
    private String website;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("custom_poster")
    private Boolean customPoster;

    @JsonProperty("pronoun")
    private Long pronoun;

    @JsonProperty("reply")
    private Long reply;

    @JsonProperty("fav_films")
    private Set<Long> favFilms;
}
