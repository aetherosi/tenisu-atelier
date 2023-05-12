package com.dev.tenisuatelier.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class PlayerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String shortName;
    private String gender;

    private CountryDTO countryDTO;

    private String picture;

    private StatsDTO statsDTO;

    @JsonCreator
    public PlayerDTO(@JsonProperty("id") Long id,
                     @JsonProperty("firstname") String firstName,
                     @JsonProperty("lastname") String lastName,
                     @JsonProperty("shortname") String shortName,
                     @JsonProperty("sex") String gender,
                     @JsonProperty("country") CountryDTO countryDTO,
                     @JsonProperty("picture") String picture,
                     @JsonProperty("data") StatsDTO statsDTO) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shortName = shortName;
        this.gender = gender;
        this.countryDTO = countryDTO;
        this.picture = picture;
        this.statsDTO = statsDTO;
    }

}
