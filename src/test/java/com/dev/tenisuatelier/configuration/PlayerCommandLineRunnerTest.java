package com.dev.tenisuatelier.configuration;

import com.dev.tenisuatelier.domain.Player;
import com.dev.tenisuatelier.service.PlayerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class PlayerCommandLineRunnerTest {

    @Autowired
    private PlayerService playerService;

    @Test
    void run() {

    }
}