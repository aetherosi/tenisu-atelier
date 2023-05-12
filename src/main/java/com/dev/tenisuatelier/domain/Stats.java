package com.dev.tenisuatelier.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@lombok.Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@Embeddable
public class Stats {

    private Integer rank;
    private Double points;
    private Double weight;
    private Double height;
    private Integer age;
    private Integer nbWins;
    private Integer nbLosses;

    @JsonProperty("last")
    private List<Boolean> matchHistory;

    public Stats() {
    }

}
