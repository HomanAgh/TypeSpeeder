package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import se.ju23.typespeeder.logic.MenuLogicImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuPerformanceTest {

    private static final int MAX_EXECUTION_TIME_MENU = 1;
    private static final int MAX_EXECUTION_TIME_LANGUAGE_SELECTION = 100;
    private static final int MILLISECONDS_CONVERSION = 1_000_000;

    @Test
    public void testGetMenuOptionsExecutionTime() {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        long startTime = System.nanoTime();
        MenuLogicImpl menuLogic = context.getBean(MenuLogicImpl.class);
        menuLogic.displayEnglishMenu(); //lägga till menuoptions i menulogic
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;

        assertTrue(duration <= MAX_EXECUTION_TIME_MENU, "Menu display took too long. Execution time: " + duration + " ms.");
    }

    @Test
    public void testUserCanChooseSwedishLanguageAndPerformance() {
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        MenuLogicImpl menu = context.getBean(MenuLogicImpl.class);

        long startTime = System.nanoTime();
        menu.chooseLanguage();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("1. English\n2. Svenska"), "Menu should prompt for language selection.");

        assertTrue(consoleOutput.contains("Språk inställt på svenska."), "Menu should confirm Swedish language selection.");

        assertTrue(duration <= MAX_EXECUTION_TIME_LANGUAGE_SELECTION, "Menu display and language selection took too long. Execution time: " + duration + " ms.");
    }

}