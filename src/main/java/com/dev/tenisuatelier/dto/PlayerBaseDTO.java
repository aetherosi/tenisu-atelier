package com.dev.tenisuatelier.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonRootName("players")
@AllArgsConstructor
@NoArgsConstructor
public class PlayerBaseDTO {

    private List<PlayerDTO> players;

}
