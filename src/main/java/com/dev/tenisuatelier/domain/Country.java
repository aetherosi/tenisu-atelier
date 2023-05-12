package com.dev.tenisuatelier.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Country {

    @Column(name = "country_flag")
    private String picture;

    @Column(name = "country_code")
    private String code;

}
