import core.Station;
import core.Line;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RouteCalculatorTest {
    private RouteCalculator calculator;
    private StationIndex stationIndex;
    private List<List<String>> connections; // Define connections at the class level

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
        connections = List.of(
                List.of("Station 1"),
                List.of("Station 2"),
                List.of("Station 3"),
                List.of("Station 4"),
                List.of("Station 5")

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

        Line line5 = stationIndex.getLine(5);
        Station station18 = new Station("Station 18", line5);
        stationIndex.addStation(station18);
        line5.addStation(station18);

        calculator = new RouteCalculator(stationIndex);
    }



    @Test
    public void testGetShortestRoute_SameLine() {
        Station from = stationIndex.getStation("Station 1");
        Station to = stationIndex.getStation("Station 5");

        List<Station> route = calculator.getShortestRoute(from, to);

        assertEquals(5, route.size());
        assertEquals(from, route.get(0));
        assertEquals(stationIndex.getStation("Station 4"), route.get(3));
        assertEquals(to, route.get(4));
    }

    @Test
    public void testGetShortestRoute_OneConnection() {
        Station from = stationIndex.getStation("Station 1");
        Station to = stationIndex.getStation("Station 5");

        List<Station> route = calculator.getShortestRoute(from, to);

        assertNotNull(route);
        assertEquals(5, route.size());
        assertEquals(from, route.get(0));
        assertEquals(stationIndex.getStation("Station 3"), route.get(2));
        assertEquals(to, route.get(4));
    }


    @Test
    public void testGetShortestRoute_TwoConnections() {
        Station from = stationIndex.getStation("Station 1");
        Station to = stationIndex.getStation("Station 5");

        List<Station> route = calculator.getShortestRoute(from, to);

        assertNotNull(route);
        assertEquals(5, route.size());
        assertEquals(from, route.get(0));
        assertEquals(stationIndex.getStation("Station 2"), route.get(1));
        assertEquals(stationIndex.getStation("Station 3"), route.get(2));
        assertEquals(stationIndex.getStation("Station 4"), route.get(3));
        assertEquals(to, route.get(4));
    }


    @Test
    public void testGetShortestRoute_SameStation() {
        Station from = stationIndex.getStation("Station 1");

        List<Station> route = calculator.getShortestRoute(from, from);

        assertNotNull(route);
        assertEquals(1, route.size());
        assertEquals(from, route.get(0));
    }

    @Test
    public void testGetShortestRoute_AdjacentStations() {
        Station from = stationIndex.getStation("Station 1");
        Station to = stationIndex.getStation("Station 2");

        List<Station> route = calculator.getShortestRoute(from, to);

        assertNotNull(route);
        assertEquals(2, route.size());
        assertEquals(from, route.get(0));
        assertEquals(to, route.get(1));
    }

    @Test
    public void testGetShortestRoute_NoConnection() {
        Station from = new Station("Station 3", stationIndex.getLine(3));
        Station to = new Station("Station 18", stationIndex.getLine(5));

        List<Station> route = calculator.getShortestRoute(from, to);

        assertTrue(route.isEmpty());
    }






}
