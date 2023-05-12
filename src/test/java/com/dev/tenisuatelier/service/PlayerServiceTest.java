package com.dev.tenisuatelier.service;

import com.dev.tenisuatelier.domain.Country;
import com.dev.tenisuatelier.domain.Player;
import com.dev.tenisuatelier.domain.Stats;
import com.dev.tenisuatelier.dto.PlayerDTO;
import com.dev.tenisuatelier.dto.RatioDTO;
import com.dev.tenisuatelier.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    public PlayerService sut;

    public static List<Player> PLAYERS = new ArrayList<>();

    @BeforeAll
    static void setUp() {
            PLAYERS = List.of(
                   new Player(1L, "Jean", "Balle", "J.BAL", "M",
                           new Country("FRA_PIC", "FRA"),
                           "PIC",
                           new Stats(1, 5241d, 75000d, 175d, 25, 1, 4, List.of(false, true, false, false, false))
                   ),
                    new Player(2L, "Claire", "Filet", "C.FIL", "F",
                            new Country("FRA_PIC", "FRA"),
                            "PIC",
                            new Stats(2, 4000d, 65000d, 180d, 28, 3, 2, List.of(true, true, true, false, false))
                    ),
                    new Player(3L, "Gloria", "Pelota", "G.PEL", "F",
                            new Country("ESP_PIC", "ESP"),
                            "PIC",
                            new Stats(33, 3000d, 70000d, 165d, 22, 2, 3, List.of(true, true, false, false, false))
                    ),
                    new Player(4L, "David", "Raquette", "D.RAQ", "M",
                            new Country("SUI_PIC", "SUI"),
                            "PIC",
                            new Stats(12, 2000d, 82000d, 154d, 28, 5, 0, List.of(true, true, true, true, true))
                    )
            );

    }

    @Test
    public void should_return_all_players_sorted_by_rank_given_a_list_of_players() {
        when(playerRepository.findAll()).thenReturn(PLAYERS);

        List<PlayerDTO> ranking = sut.findAll();

        assertThat(ranking.get(0).getStatsDTO().getRank()).isEqualTo(1);
        assertThat(ranking.get(1).getStatsDTO().getRank()).isEqualTo(2);
        assertThat(ranking.get(2).getStatsDTO().getRank()).isEqualTo(12);
        assertThat(ranking.get(3).getStatsDTO().getRank()).isEqualTo(33);
    }

    @Test
    public void should_delete_player_given_an_existing_id() {
        long playerId = 2L;

        Player expectedPlayer = PLAYERS.get(1);

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(expectedPlayer));

        Optional<PlayerDTO> player = sut.deleteById(playerId);

        assertThat(player).isNotEmpty();
        assertThat(player.get().getFirstName()).isEqualTo("Jean");
        assertThat(player.get().getLastName()).isEqualTo("Balle");
        assertThat(player.get().getShortName()).isEqualTo("J.BAL");
    }

    @Test
    public void should_not_retrieve_player_given_an_existing_id() {
        long playerId = 1L;

        when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

        Optional<PlayerDTO> player = sut.findById(playerId);

        assertThat(player).isEmpty();
    }

    @Test
    public void should_compute_bmi_given_all_players() {
        when(playerRepository.findAll()).thenReturn(PLAYERS);

        Double averageBMI = sut.computeAverageBMI();

        assertThat(averageBMI).isEqualTo(26.2d);
    }

    @Test
    public void should_compute_median_height_given_an_even_number_of_players() {
        when(playerRepository.findAll()).thenReturn(PLAYERS);

        Double heightMedian = sut.computeMedianHeight();

        assertThat(heightMedian).isEqualTo(170d);
    }

    @Test
    public void should_compute_median_height_given_an_odd_number_of_players() {
        when(playerRepository.findAll()).thenReturn(PLAYERS.subList(0,3));

        Double heightMedian = sut.computeMedianHeight();

        assertThat(heightMedian).isEqualTo(175d);
    }

    @Test
    public void should_return_country_with_the_highest_win_ratio_given_a_list_of_players() {
        when(playerRepository.findAll()).thenReturn(PLAYERS);

        Optional<RatioDTO> countryWithRatio = sut.findHighestWinRatioCountry();

        assertThat(countryWithRatio.get().getCountry().getCode()).isEqualTo("SUI");
        assertThat(countryWithRatio.get().getCountry().getPicture()).isEqualTo("SUI_PIC");
        assertThat(countryWithRatio.get().getRatio()).isEqualTo(1d);
    }

}