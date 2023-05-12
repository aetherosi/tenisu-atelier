package com.dev.tenisuatelier.dto;

import com.dev.tenisuatelier.domain.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class RatioDTO {
    private Country country;
    private Double ratio;
}
