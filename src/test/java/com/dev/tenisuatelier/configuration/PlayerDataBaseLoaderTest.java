package com.dev.tenisuatelier.configuration;

import com.dev.tenisuatelier.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PlayerDataBaseLoaderTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    public PlayerDataBaseLoader sut;


    @Test
    void run() throws Exception {

    }

}