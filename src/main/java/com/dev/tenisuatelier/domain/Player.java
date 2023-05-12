package com.dev.tenisuatelier.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String shortName;
    private String gender;

    private Country country;

    @Column(name = "picture")
    private String pictureURL;

    @Embedded
    private Stats stats;
}
