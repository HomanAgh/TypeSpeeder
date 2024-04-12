package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

public class PatchTest {

    @Test
    public void testPatchClassExists() {
        try {
            Class.forName("Patch");
        } catch (ClassNotFoundException e) {
            throw new AssertionError("Patch class should exist.", e);
        }
    }

    @Test
    public void testPatchProperties() {
        try {
            Class<?> someClass = Class.forName("se.ju23.typespeeder.entity.Patch");

            Field patchVersion = someClass.getDeclaredField("version");
            assertNotNull(patchVersion, "Field 'version' should exist in the Patch class.");
            assertTrue(patchVersion.getType().equals(String.class), "Field 'patchVersion' should be of type String.");

            Field releaseDateTime = someClass.getDeclaredField("releaseDate");
            assertNotNull(releaseDateTime, "Field 'releaseDateTime' should exist in Patch class.");

            assertTrue(releaseDateTime.getType().equals(LocalDateTime.class), "Field 'releaseDate' should be of type LocalDateTime.");

            Object instance = someClass.getDeclaredConstructor().newInstance();
            LocalDateTime dateTimeValue = (LocalDateTime) releaseDateTime.get(instance);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTimeValue.format(formatter);
            assertEquals("2024-01-01 00:00:00", formattedDateTime, "'releaseDate' field should have format 'yyyy-MM-dd HH:mm:ss'.");

            Method getterMethod = someClass.getDeclaredMethod("getReleaseDate");
            assertNotNull(getterMethod, "Getter method for field 'releaseDateTime' should exist.");


        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e) {
            fail("Error occurred while testing properties of Patch.", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
