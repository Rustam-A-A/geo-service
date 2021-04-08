package ru.netology.sender;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;
import static ru.netology.geo.GeoServiceImpl.NEW_YORK_IP;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

public class MessageSenderImplTest {

    private static long suiteStartTime;
    private static long testStartTime;

    @BeforeAll
    public static void initSuite(){
        suiteStartTime = System.nanoTime();
        System.out.println("Running MessageSenderImpl test");
    }

    @AfterAll
    public static void completeSuite(){
        System.out.println("MessageSenderImpl test completed: " +
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
    void sendMessageIfUSAAddressTest(){
        Map<String, String> ip = new HashMap<String, String>();
        ip.put(IP_ADDRESS_HEADER, NEW_YORK_IP);
        var expected = "Welcome";

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(USA)).thenReturn("Welcome");

        Location locationUSA = new Location ("New York", USA, " 10th Avenue", 32);
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(NEW_YORK_IP)).thenReturn(locationUSA);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);
        var actual = messageSender.send(ip);
        assertEquals(expected, actual);
    }
    @Test
    void sendMessageIfUSATest(){
        Map<String, String> ip = new HashMap<String, String>();
        ip.put(IP_ADDRESS_HEADER, "96.*");
        var expected = "Welcome";

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(USA)).thenReturn("Welcome");

        Location locationUSA = new Location ("New York", Country.USA, null,  0);
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("96.*")).thenReturn(locationUSA);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);
        var actual = messageSender.send(ip);
        assertEquals(expected, actual);
    }

    @Test
    void sendMessageIfRussiaAddressTest(){
        Map<String, String> ip = new HashMap<String, String>();
        ip.put(IP_ADDRESS_HEADER, MOSCOW_IP);
        var expected = "Добро пожаловать";

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");

        Location locationRussia = new Location ("Moscow", Country.RUSSIA, "Lenina", 15);
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(MOSCOW_IP)).thenReturn(locationRussia);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);
        var actual = messageSender.send(ip);
        assertEquals(expected, actual);
    }
        @Test
        void sendMessageIfRussiaTest(){
            Map<String, String> ip = new HashMap<String, String>();
            ip.put(IP_ADDRESS_HEADER, "172.*");
            var expected = "Добро пожаловать";

            LocalizationService localizationService = Mockito.mock(LocalizationService.class);
            Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");

            Location locationRussia = new Location ("Moscow", RUSSIA, null,  0);
            GeoService geoService = Mockito.mock(GeoServiceImpl.class);
            Mockito.when(geoService.byIp("172.*")).thenReturn(locationRussia);

            MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);
            var actual = messageSender.send(ip);
            assertEquals(expected, actual);
        }


}