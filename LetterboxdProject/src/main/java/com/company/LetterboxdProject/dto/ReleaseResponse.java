package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReleaseResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("dates")
    private LocalDate date;

    @JsonProperty("country_flag_photo")
    private String countryFlagPhotoUrl;

    @JsonProperty("country_name")
    private String country;
}