package com.dev.tenisuatelier.controller;

import com.dev.tenisuatelier.domain.Player;
import com.dev.tenisuatelier.dto.PlayerDTO;
import com.dev.tenisuatelier.dto.RatioDTO;
import com.dev.tenisuatelier.response.Response;
import com.dev.tenisuatelier.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    Response findAllPlayers() {
        List<PlayerDTO> players = playerService.findAll();

        return Response.builder()
                .status(200)
                .data(players)
                .message(players.size() + " found in database")
                .build();
    }

    @GetMapping(value = "/players/{id}", produces = "application/json")
    Response findPlayer(@PathVariable final long id) {
        Optional<PlayerDTO> player = playerService.findById(id);

        return player.map(p ->
                        Response.builder()
                                .status(200)
                                .data(p)
                                .message("Player found with id " + id).build())
                .orElseGet(() ->
                        Response.builder()
                                .status(422)
                                .data(new ArrayList<>())
                                .message("No player found for id : " + id)
                                .build());
    }

    @GetMapping(value = "/players", params = "action=average-bmi", produces = "application/json")
    Response computeAverageBMI(@RequestParam(name = "action") String action) {
        return Response.builder()
                .status(200)
                .data(
                        playerService.computeAverageBMI()
                ).build();
    }

    @GetMapping(value = "/players", params = "action=median-height", produces = "application/json")
    Response computeMedianHeight(@RequestParam(name = "action") String action) {
        return Response.builder()
                .status(200)
                .data(
                        playerService.computeMedianHeight()
                ).build();
    }

    @GetMapping(value = "/players", params = "action=best-country", produces = "application/json")
    Response findBestCountry(@RequestParam(name = "action") String action) {
        Optional<RatioDTO> ratio = playerService.findHighestWinRatioCountry();

        return ratio.map(p ->
                        Response.builder()
                                .status(200)
                                .data(ratio)
                                .build())
                .orElseGet(() ->
                        Response.builder()
                                .status(422)
                                .data(new ArrayList<>())
                                .message("Player database is empty")
                                .build());
    }

    @DeleteMapping(value = "/players/{id}", produces = "application/json")
    Response deletePlayer(@PathVariable final long id) {
        Optional<PlayerDTO> player = playerService.deleteById(id);

        return player.map(p ->
                        Response.builder()
                                .status(200)
                                .message("Player of ID : " + id + " was deleted successfully")
                                .build())
                .orElseGet(() ->
                        Response.builder()
                                .status(422)
                                .data(new ArrayList<>())
                                .message("Player of ID : " + id + "not found in database")
                                .build());
    }
}
