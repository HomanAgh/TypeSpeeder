package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Set;

import org.mockito.Mockito;
import se.ju23.typespeeder.logic.MenuLogic;
import se.ju23.typespeeder.logic.MenuLogicImpl;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testClassExists() {
        try {
            Class<?> clazz = Class.forName("se.ju23.typespeeder.logic.MenuLogic");
            assertNotNull(clazz, "The class 'MenuLogic' should exist.");
        } catch (ClassNotFoundException e) {
            fail("The class 'MenuLogic' does not exist.", e);
        }
    }

    @Test
    public void testMethodExists() {
        try {
            Class<?> clazz = Class.forName("se.ju23.typespeeder.logic.MenuLogic");
            Method method = clazz.getMethod("displayMainMenu");
            assertNotNull(method, "The method 'displayMainMenu()' should exist in the class 'MenuLogic'.");
        } catch (ClassNotFoundException e) {
            fail("The class 'MenuLogic' does not exist.", e);
        } catch (NoSuchMethodException e) {
            fail("The method 'displayMainMenu()' does not exist in the class 'MenuLogic'.", e);
        }
    }

    @Test
    public void testMenuImplementsInterface() {
        try {
            Class<?> menuClass = Class.forName("se.ju23.typespeeder.logic.MenuLogicImpl");
            boolean implementsInterface = false;

            Class<?>[] interfaces = menuClass.getInterfaces();
            for (Class<?> iface : interfaces) {
                if (iface.equals(MenuLogic.class)) {
                    implementsInterface = true;
                    break;
                }
            }

            assertTrue(implementsInterface, "The class 'MenuLogicImpl' should implement the interface 'MenuLogic'.");
        } catch (ClassNotFoundException e) {
            fail("The class 'MenuLogicImpl' could not be found", e);
        }
    }

    @Test
    public void menuShouldHaveAtLeastFiveOptions() {
        MenuLogicImpl menu = new MenuLogicImpl(mock(), mock(), mock(), mock(), mock());
        Set<String> options = menu.options.keySet();
        assertTrue(options.size() >= 5, "The menu should contain at least 5 alternatives.");
    }

    @Test
    public void menuShouldPrintAtLeastFiveOptions() {
        new MenuLogicImpl(mock(), mock(), mock(), mock(), mock()).displayEnglishMenu();
        long count = outContent.toString().lines().count();
        assertTrue(count >= 5, "The menu should print out at least 5 alternatives.");
    }

    @Test
    public void testUserCanChooseSwedishLanguage() {
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        MenuLogicImpl menu = new MenuLogicImpl(mock(),mock(),mock(),mock(),mock());
        menu.chooseLanguage();

        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains("1. English\n2. Svenska"), "Menu should prompt for language selection.");
        assertTrue(consoleOutput.contains("Språk inställt på svenska."), "Menu should confirm Swedish language selection.");
    }
}