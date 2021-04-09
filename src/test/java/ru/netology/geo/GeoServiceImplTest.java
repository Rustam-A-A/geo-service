package ru.netology.geo;

import org.junit.jupiter.api.*;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;
import static ru.netology.geo.GeoServiceImpl.NEW_YORK_IP;

class GeoServiceImplTest {
    private static long suiteStartTime;
    private static long testStartTime;

    @BeforeAll
    public static void initSuite(){
        suiteStartTime = System.nanoTime();
        System.out.println("Running GeoServiceImpl test");
    }

    @AfterAll
    public static void completeSuite(){
        System.out.println("GeoServiceImpl test completed: " +
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
    void byIpMoscowAddress(){
        var expected = new Location("Moscow", Country.RUSSIA,  "Lenina", 15);
        GeoServiceImpl geoService = new GeoServiceImpl();
        var actual = geoService.byIp(MOSCOW_IP);
        Assertions.assertEquals(actual.getCountry(), expected.getCountry());
        Assertions.assertEquals(actual.getCity(), expected.getCity());
        Assertions.assertEquals(actual.getBuiling(), expected.getBuiling());
        Assertions.assertEquals(actual.getStreet(), expected.getStreet());
    }
    @Test
    void byIpRussia(){
        String russianIp = "172.99.999.99";
        var expected = new Location("Moscow", Country.RUSSIA, null, 0);
        GeoServiceImpl geoService = new GeoServiceImpl();
        var actual = geoService.byIp(russianIp);
        Assertions.assertEquals(actual.getCountry(), expected.getCountry());
    }

    @Test
    void byIpNewYorkAddress() {
        var expected = new Location("New York", Country.USA, " 10th Avenue", 32);
        GeoServiceImpl geoService = new GeoServiceImpl();
        var actual = geoService.byIp(NEW_YORK_IP);
        Assertions.assertEquals(actual.getCountry(), expected.getCountry());
        Assertions.assertEquals(actual.getCity(), expected.getCity());
        Assertions.assertEquals(actual.getBuiling(), expected.getBuiling());
        Assertions.assertEquals(actual.getStreet(), expected.getStreet());
    }

    @Test
    void byIpUSA(){
        String usaIp = "96.99.999.99";
        var expected = new Location("New York", Country.USA, null, 0);
        GeoServiceImpl geoService = new GeoServiceImpl();
        var actual = geoService.byIp(usaIp);
        Assertions.assertEquals(actual.getCountry(), expected.getCountry());
    }

    @Test
    void byIpThirdCountry(){
        String thirdCountryIp = "33.99.999.99";
        GeoServiceImpl geoService = new GeoServiceImpl();
        var actual = geoService.byIp(thirdCountryIp);
        Assertions.assertNull(actual);
    }
}