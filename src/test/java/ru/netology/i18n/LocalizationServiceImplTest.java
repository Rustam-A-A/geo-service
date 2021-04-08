package ru.netology.i18n;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

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
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");

        var actual = localizationService.locale(RUSSIA);
        assertEquals(expected, actual);
    }
}