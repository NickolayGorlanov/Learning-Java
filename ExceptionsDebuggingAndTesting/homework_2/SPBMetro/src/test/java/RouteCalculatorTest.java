import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import core.Station;
import core.Line;

public class RouteCalculatorTest {
    private RouteCalculator calculator;
    private StationIndex stationIndex;

    @BeforeEach
    public void setUp() {
        stationIndex = new StationIndex();
        for (int i = 1; i <= 5; i++) {
            Line line = new Line(i, "Line " + i);
            for (int j = 1; j <= 18; j++) {
                Station station = new Station("Station " + j, line);
                stationIndex.addStation(station);
                line.addStation(station);
            }
            stationIndex.addLine(line);
        }

        // Add connections
        List<List<String>> connections = List.of(
                List.of("Невский проспект", "Гостиный двор"),
                List.of("Площадь Восстания", "Маяковская"),
                List.of("Сенная площадь", "Спасская", "Садовая"),
                List.of("Владимирская", "Достоевская"),
                List.of("Пушкинская", "Звенигородская"),
                List.of("Площадь Александра Невского", "Площадь Александра Невского")
        );

        for (List<String> connection : connections) {
            List<Station> stations = new ArrayList<>();
            for (String stationName : connection) {
                Station station = stationIndex.getStation(stationName);
                if (station == null) {
                    // Если станция не найдена, выводим сообщение об ошибке
                    System.err.println("Station not found: " + stationName);
                    continue;
                }
                stations.add(station);
            }
            stationIndex.addConnection(stations);
        }


        calculator = new RouteCalculator(stationIndex);
    }

    @Test
    public void testGetShortestRoute_SameLine() {
        Line line1 = stationIndex.getLine(1);
        Station from = stationIndex.getStation("Девяткино");
        Station to = stationIndex.getStation("Лесная");

        List<Station> route = calculator.getShortestRoute(from, to);

        assertEquals(6, route.size());
        assertEquals(from, route.get(0));
        assertEquals(stationIndex.getStation("Гражданский проспект"), route.get(1));
        assertEquals(stationIndex.getStation("Академическая"), route.get(2));
        assertEquals(stationIndex.getStation("Политехническая"), route.get(3));
        assertEquals(stationIndex.getStation("Площадь Мужества"), route.get(4));
        assertEquals(to, route.get(5));
    }

    @Test
    public void testGetShortestRoute_OneConnection() {
        Line line1 = stationIndex.getLine(1);
        Line line2 = stationIndex.getLine(2);
        Station from = stationIndex.getStation("Девяткино");
        Station to = stationIndex.getStation("Проспект Просвещения");

        List<Station> route = calculator.getShortestRoute(from, to);

        assertEquals(3, route.size());
        assertEquals(from, route.get(0));
        assertEquals(stationIndex.getStation("Гражданский проспект"), route.get(1));
        assertEquals(to, route.get(2));
    }

    @Test
    public void testGetShortestRoute_TwoConnections() {
        Line line1 = stationIndex.getLine(1);
        Line line2 = stationIndex.getLine(2);
        Line line3 = stationIndex.getLine(3);
        Station from = stationIndex.getStation("Девяткино");
        Station to = stationIndex.getStation("Парк Победы");

        List<Station> route = calculator.getShortestRoute(from, to);

        assertEquals(7, route.size());
        assertEquals(from, route.get(0));
        assertEquals(stationIndex.getStation("Гражданский проспект"), route.get(1));
        assertEquals(stationIndex.getStation("Академическая"), route.get(2));
        assertEquals(stationIndex.getStation("Политехническая"), route.get(3));
        assertEquals(stationIndex.getStation("Площадь Мужества"), route.get(4));
        assertEquals(stationIndex.getStation("Лесная"), route.get(5));
        assertEquals(to, route.get(6));
    }

    @Test
    public void testGetShortestRoute_SameStation() {
        Line line1 = stationIndex.getLine(1);
        Station from = stationIndex.getStation("Девяткино");

        List<Station> route = calculator.getShortestRoute(from, from);

        assertNotNull(route);
        assertEquals(1, route.size());
        assertEquals(from, route.get(0));
    }

    @Test
    public void testGetShortestRoute_AdjacentStations() {
        Line line1 = stationIndex.getLine(1);
        Station from = stationIndex.getStation("Девяткино");
        Station to = stationIndex.getStation("Гражданский проспект");

        List<Station> route = calculator.getShortestRoute(from, to);

        assertNotNull(route);
        assertEquals(2, route.size());
        assertEquals(from, route.get(0));
        assertEquals(to, route.get(1));
    }

    @Test
    public void testGetShortestRoute_NoConnection() {
        Line line1 = stationIndex.getLine(1);
        Line line2 = stationIndex.getLine(2);
        Station from = stationIndex.getStation("Девяткино");
        Station to = stationIndex.getStation("Комендантский проспект");

        List<Station> route = calculator.getShortestRoute(from, to);

        assertNull(route);
    }

    // Add more test methods to cover other scenarios
}
