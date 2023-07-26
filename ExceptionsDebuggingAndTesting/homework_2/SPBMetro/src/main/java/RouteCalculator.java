import core.Station;

import java.util.*;

public class RouteCalculator {
    private final StationIndex stationIndex;

    private static final double INTER_STATION_DURATION = 2.5;
    private static final double INTER_CONNECTION_DURATION = 3.5;

    public RouteCalculator(StationIndex stationIndex) {
        this.stationIndex = stationIndex;
    }

    public List<Station> getShortestRoute(Station from, Station to) {
        if (from == null || to == null) {
            return Collections.emptyList();
        }

        List<Station> route = new ArrayList<>();
        if (from.getLine().equals(to.getLine())) {
            route.addAll(getRouteOnTheLine(from, to));
        } else {
            List<Station> routeWithOneConnection = getRouteWithOneConnection(from, to);
            List<Station> routeWithTwoConnections = getRouteWithTwoConnections(from, to);

            if (routeWithOneConnection == null && routeWithTwoConnections == null) {
                return Collections.emptyList(); // No route found
            } else if (routeWithOneConnection == null) {
                route.addAll(routeWithTwoConnections);
            } else if (routeWithTwoConnections == null) {
                route.addAll(routeWithOneConnection);
            } else {
                route.addAll(getShortest(routeWithOneConnection, routeWithTwoConnections));
            }
        }

        return route;
    }

    private List<Station> getShortest(List<Station> route1, List<Station> route2) {
        double duration1 = calculateDuration(route1);
        double duration2 = calculateDuration(route2);
        return duration1 <= duration2 ? route1 : route2;
    }

    public static double calculateDuration(List<Station> route) {
        double duration = 0;
        Station previousStation = null;
        for (Station station : route) {
            if (previousStation != null) {
                duration += previousStation.getLine().equals(station.getLine()) ?
                        INTER_STATION_DURATION : INTER_CONNECTION_DURATION;
            }
            previousStation = station;
        }
        return duration;
    }

    private List<Station> getRouteOnTheLine(Station from, Station to) {
        if (!from.getLine().equals(to.getLine())) {
            return Collections.emptyList();
        }
        List<Station> route = new ArrayList<>();
        List<Station> stations = from.getLine().getStations();
        int startIndex = stations.indexOf(from);
        int endIndex = stations.indexOf(to);
        if (startIndex == -1 || endIndex == -1) {
            return Collections.emptyList();
        }
        if (startIndex <= endIndex) {
            route.addAll(stations.subList(startIndex, endIndex + 1));
        } else {
            route.addAll(stations.subList(endIndex, startIndex + 1));
            Collections.reverse(route);
        }
        return route;
    }

    private List<Station> getRouteWithOneConnection(Station from, Station to) {
        if (from.getLine().equals(to.getLine())) {
            return null;
        }

        Set<Station> visitedStations = new HashSet<>();
        Queue<List<Station>> queue = new LinkedList<>();
        List<Station> initialRoute = new ArrayList<>();
        initialRoute.add(from);
        queue.add(initialRoute);

        while (!queue.isEmpty()) {
            List<Station> currentRoute = queue.poll();
            Station lastStation = currentRoute.get(currentRoute.size() - 1);

            if (lastStation.equals(to)) {
                return currentRoute;
            }

            visitedStations.add(lastStation);

            for (Station nextStation : stationIndex.getConnectedStations(lastStation)) {
                if (!visitedStations.contains(nextStation)) {
                    List<Station> newRoute = new ArrayList<>(currentRoute);
                    newRoute.add(nextStation);
                    queue.add(newRoute);
                }
            }
        }

        return null;
    }

    private List<Station> getRouteWithTwoConnections(Station from, Station to) {
        if (from.getLine().equals(to.getLine())) {
            return Collections.emptyList();
        }

        List<Station> bestRoute = null;
        double shortestDuration = Double.MAX_VALUE;

        for (Station station1 : stationIndex.getConnectedStations(from)) {
            for (Station station2 : stationIndex.getConnectedStations(to)) {
                if (station1.getLine().equals(station2.getLine())) {
                    List<Station> route1 = getRouteOnTheLine(from, station1);
                    List<Station> route2 = getRouteOnTheLine(station2, to);
                    if (route1 != null && route2 != null) {
                        List<Station> combinedRoute = new ArrayList<>(route1);
                        combinedRoute.addAll(route2);
                        double duration = calculateDuration(combinedRoute);
                        if (duration < shortestDuration) {
                            bestRoute = combinedRoute;
                            shortestDuration = duration;
                        }
                    }
                }
            }
        }

        return bestRoute;
    }
}
