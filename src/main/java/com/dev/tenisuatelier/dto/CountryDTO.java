package com.dev.tenisuatelier.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CountryDTO {

    private String picture;
    private String code;

    @JsonCreator
    public CountryDTO(@JsonProperty("picture") String picture,
                      @JsonProperty("code") String code) {
        this.picture = picture;
        this.code = code;
    }
}
