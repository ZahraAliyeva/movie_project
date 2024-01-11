package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryRequest {

    @JsonProperty("country")
    private String country;

    @JsonProperty("flag_photo")
    private String flagPhoto;
}
