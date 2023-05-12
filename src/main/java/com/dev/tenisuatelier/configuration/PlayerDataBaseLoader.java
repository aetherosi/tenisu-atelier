package com.dev.tenisuatelier.configuration;

import com.dev.tenisuatelier.dto.PlayerBaseDTO;
import com.dev.tenisuatelier.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class PlayerDataBaseLoader implements CommandLineRunner {

    private final String playersDatasource;

    private final PlayerService playerService;

    private final Logger LOGGER = LoggerFactory.getLogger(PlayerDataBaseLoader.class);

    @Autowired
    public PlayerDataBaseLoader(@Value("${players.filepath}") String playersDatasource, PlayerService playerService) {
        this.playersDatasource = playersDatasource;
        this.playerService = playerService;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPlayerDatabase = readFile(playersDatasource);

        try {
            PlayerBaseDTO playerBase = objectMapper.readValue(jsonPlayerDatabase, PlayerBaseDTO.class);

            playerService.saveAll(playerBase.getPlayers());

            LOGGER.info("{} users were saved in database", playerBase.getPlayers().size());
        } catch (IOException e) {
            LOGGER.error("Unable to save users due to : " + e.getMessage());
        }
    }

    private String readFile(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
