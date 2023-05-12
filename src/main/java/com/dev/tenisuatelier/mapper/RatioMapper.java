package com.dev.tenisuatelier.mapper;

import com.dev.tenisuatelier.domain.Country;
import com.dev.tenisuatelier.dto.RatioDTO;

import java.util.Map;

public class RatioMapper {

    public static RatioDTO mapToRatioDTO(Map.Entry<Country, Double> countryRatioEntry) {
        Country country = countryRatioEntry.getKey();
        Double ratio = countryRatioEntry.getValue();

        return new RatioDTO(country, ratio);
    }
}
