package com.dev.tenisuatelier.mapper;


import com.dev.tenisuatelier.domain.Country;
import com.dev.tenisuatelier.domain.Player;
import com.dev.tenisuatelier.domain.Stats;
import com.dev.tenisuatelier.dto.CountryDTO;
import com.dev.tenisuatelier.dto.PlayerDTO;
import com.dev.tenisuatelier.dto.StatsDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PlayerMapper {
    public static Player toPlayer(PlayerDTO playerDTO) {

        CountryDTO countryDTO = playerDTO.getCountryDTO();
        StatsDTO statsDTO = playerDTO.getStatsDTO();

        return Player.builder()
                .firstName(playerDTO.getFirstName())
                .lastName(playerDTO.getLastName())
                .shortName(playerDTO.getShortName())
                .gender(playerDTO.getGender())
                .pictureURL(playerDTO.getPicture())
                .country(
                        Country.builder()
                                .code(countryDTO.getCode())
                                .picture(countryDTO.getPicture())
                                .build()
                )
                .stats(
                        Stats.builder()
                                .rank(statsDTO.getRank())
                                .points(statsDTO.getPoints())
                                .weight(statsDTO.getWeight())
                                .height(statsDTO.getHeight())
                                .age(statsDTO.getAge())
                                .matchHistory(statsDTO.getMatchHistory())
                                .nbLosses((int) statsDTO.getMatchHistory().stream().filter(w -> !w).count())
                                .nbWins((int) statsDTO.getMatchHistory().stream().filter(w -> w).count())
                                .build()
                ).build();
    }

    public static PlayerDTO toPlayerDTO(Player player) {

        Country country = player.getCountry();
        Stats stats = player.getStats();

        return PlayerDTO.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .shortName(player.getShortName())
                .gender(player.getGender())
                .picture(player.getPictureURL())
                .countryDTO(
                        CountryDTO.builder()
                                .code(country.getCode())
                                .picture(country.getPicture())
                                .build()
                )
                .statsDTO(
                        StatsDTO.builder()
                                .rank(stats.getRank())
                                .weight(stats.getWeight())
                                .height(stats.getHeight())
                                .age(stats.getAge())
                                .matchHistory(stats.getMatchHistory())
                                .points(stats.getPoints())
                                .build()
                ).build();
    }

    public static List<PlayerDTO> mapAllPlayersToDTO(Iterable<Player> players) {
        return StreamSupport.stream(players.spliterator(), false)
                .map(PlayerMapper::toPlayerDTO)
                .collect(Collectors.toList());
    }

    public static List<Player> mapAllPlayersDTOtoEntity(List<PlayerDTO> playersDTO) {
        return playersDTO.stream()
                .map(PlayerMapper::toPlayer)
                .collect(Collectors.toList());
    }
}
