package ru.netology.i18n;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.*;

class LocalizationServiceImplTest {
    private static long suiteStartTime;
    private static long testStartTime;

    @BeforeAll
    public static void initSuite(){
        suiteStartTime = System.nanoTime();
        System.out.println("Running LocalizationServiceImpl test");
    }

    @AfterAll
    public static void completeSuite(){
        System.out.println("LocalizationServiceImpl test completed: " +
                (System.nanoTime() - suiteStartTime));
    }
    @BeforeEach
    public void initTest(){
        System.out.println("Starting new test");
        long testStartTime = System.nanoTime();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test completed: " +
                (System.nanoTime() - testStartTime));
    }
    @Test
    void localeTestRussia(){
        var expected = "Добро пожаловать";
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        var actual = localizationService.locale(RUSSIA);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void localeTestUSA(){
        var expected = "Welcome";
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        var actual = localizationService.locale(USA);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void localeTestThirdCountry(){
        var expected = "Welcome";
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        var actual = localizationService.locale(GERMANY);
        Assertions.assertEquals(actual, expected);
    }
}