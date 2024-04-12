package se.ju23.typespeeder;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import se.ju23.typespeeder.logic.Challenge;
import se.ju23.typespeeder.service.LoginService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ChallengePerformanceTest {
    private static final int MAX_EXECUTION_TIME = 200;
    private static final int MILLISECONDS_CONVERSION = 1_000_000;
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @BeforeEach // innan test (båda tester, annars i testets scope(det första som händer isf), både before och after)
    public void setUpOutput() {
        testIn = new ByteArrayInputStream("test input".getBytes());
        System.setIn(testIn);
    }

    @AfterEach // efter test
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
    }


    @Test
    public void testStartChallengePerformance() {
        LoginService mockedLoginsService = mock(LoginService.class);
        Challenge challenge = new Challenge(mockedLoginsService);
        long startTime = System.nanoTime();
        challenge.startChallenge();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Starting a challenge took too long. Execution time: " + duration + " ms.");
    }
    @Test
    public void testLettersToTypePerformance() {
        LoginService mockedLoginsService = mock(LoginService.class);
        Challenge challenge = new Challenge(mockedLoginsService);
        long startTime = System.nanoTime();
        challenge.lettersToType();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Selecting letters to type took too long. Execution time: " + duration + " ms.");
    }
}