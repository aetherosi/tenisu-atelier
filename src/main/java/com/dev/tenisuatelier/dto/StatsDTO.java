package com.dev.tenisuatelier.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class StatsDTO {
    private Integer rank;
    private Double points;
    private Double weight;
    private Double height;
    private int age;

    private List<Boolean> matchHistory;

    @JsonCreator
    public StatsDTO(@JsonProperty("rank") Integer rank,
                    @JsonProperty("points") Double points,
                    @JsonProperty("weight") Double weight,
                    @JsonProperty("height") Double height,
                    @JsonProperty("age") int age,
                    @JsonProperty("last") List<Boolean> matchHistory) {
        this.rank = rank;
        this.points = points;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.matchHistory = matchHistory;
    }
}
