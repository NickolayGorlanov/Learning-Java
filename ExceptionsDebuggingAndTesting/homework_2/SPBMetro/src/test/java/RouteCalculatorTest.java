import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import core.Station;
import core.Line;

public class RouteCalculatorTest {
    private RouteCalculator calculator;

    @BeforeEach
    public void setUp() {
        StationIndex stationIndex = Main.createStationIndex(); // Create StationIndex for passing to RouteCalculator constructor
        calculator = new RouteCalculator(stationIndex);
    }

    @Test
    public void testGetShortestRoute_SameLine() {
        Line line1 = new Line(1, "Line 1");
        Station from = new Station("Station 1", line1);
        Station to = new Station("Station 5", line1);

        List<Station> route = calculator.getShortestRoute(from, to);

        assertEquals(3, route.size());
        assertEquals(from, route.get(0));
        assertEquals(new Station("Station 2", line1), route.get(1));
        assertEquals(to, route.get(2));
    }

    @Test
    public void testGetShortestRoute_OneConnection() {
        Line line1 = new Line(1, "Line 1");
        Line line2 = new Line(2, "Line 2");
        Station from = new Station("Station 1", line1);
        Station to = new Station("Station 6", line2);

        List<Station> route = calculator.getShortestRoute(from, to);

        assertEquals(4, route.size());
        assertEquals(from, route.get(0));
        assertEquals(new Station("Station 2", line1), route.get(1));
        assertEquals(new Station("Station 3", line2), route.get(2));
        assertEquals(to, route.get(3));
    }

    // Add more test methods to cover other usage scenarios
}
