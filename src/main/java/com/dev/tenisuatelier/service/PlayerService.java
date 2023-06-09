package com.dev.tenisuatelier.service;

import com.dev.tenisuatelier.domain.Country;
import com.dev.tenisuatelier.domain.Player;
import com.dev.tenisuatelier.domain.Stats;
import com.dev.tenisuatelier.dto.PlayerDTO;
import com.dev.tenisuatelier.dto.RatioDTO;
import com.dev.tenisuatelier.mapper.PlayerMapper;
import com.dev.tenisuatelier.mapper.RatioMapper;
import com.dev.tenisuatelier.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Iterable<Player> saveAll(List<PlayerDTO> playersDTO) {
        return playerRepository.saveAll(PlayerMapper.mapAllPlayersDTOtoEntity(playersDTO));
    }

    public List<PlayerDTO> findAll() {
        Iterable<Player> players = playerRepository.findAll();

        return StreamSupport.stream(players.spliterator(), false)
                .sorted(Comparator.comparingDouble(c -> c.getStats().getRank()))
                .map(PlayerMapper::toPlayerDTO)
                .collect(Collectors.toList());
    }

    public Optional<PlayerDTO> findById(long playerId) {
        Optional<Player> player = playerRepository.findById(playerId);

        return player.map(PlayerMapper::toPlayerDTO);
    }

    public Optional<RatioDTO> findHighestWinRatioCountry() {
        Map<Country, Double> ratioPerCountryMap = new HashMap<>();

        Iterable<Player> players = playerRepository.findAll();

        Function<Player, Double> computeRatio = p -> {
            Stats stats = p.getStats();

            return (double) stats.getNbWins() / (double) stats.getMatchHistory().size();
        };

        Map<Country, List<Player>> playersPerCountryMap = StreamSupport.stream(players.spliterator(), false)
                .collect(Collectors.groupingBy(Player::getCountry));

        for (Country country : playersPerCountryMap.keySet()) {
            List<Player> playerCountries = playersPerCountryMap.get(country);

            Double winRatio = BigDecimal.valueOf(playerCountries.stream()
                            .collect(Collectors.averagingDouble(computeRatio::apply)))
                    .setScale(3, RoundingMode.CEILING)
                    .doubleValue();

            ratioPerCountryMap.put(country, winRatio);
        }

        Optional<Entry<Country, Double>> ratio = ratioPerCountryMap.entrySet().stream().min(Entry.comparingByValue(Comparator.reverseOrder()));

        return ratio.map(RatioMapper::mapToRatioDTO);
    }

    public Double computeAverageBMI() {
        Iterable<Player> players = playerRepository.findAll();

        Function<Player, Double> computePlayerBMI = p -> {
            Stats stats = p.getStats();
            return  toKilograms(stats.getWeight()) / (Math.pow(toMeters(stats.getHeight()), 2));
        };

        return BigDecimal.valueOf(
                        StreamSupport.stream(players.spliterator(), false)
                                .collect(Collectors.averagingDouble
                                        (computePlayerBMI::apply))
                )
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public Double computeMedianHeight() {
        Iterable<Player> players = playerRepository.findAll();

        Function<List<Double>, Double> computeMedianHeight = heights -> {
            int nbHeights = heights.size();
            if (isEven(nbHeights)) {
                return (heights.get(nbHeights / 2) + heights.get(nbHeights / 2 - 1)) / 2;
            } else {
                return heights.get(nbHeights / 2);
            }
        };

        List<Double> sortedHeights = StreamSupport.stream(players.spliterator(), false)
                .map(Player::getStats)
                .map(Stats::getHeight)
                .sorted()
                .toList();

        return computeMedianHeight.apply(sortedHeights);
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }

    private Double toKilograms(Double weight) {
        return weight / 1000;
    }

    private Double toMeters(Double height) {
        return height / 100;
    }

}
