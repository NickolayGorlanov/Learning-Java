package DataCollector;

// Класс SubwayStationData для хранения данных о станции метро
public class SubwayStationData {
    private final String station_name;
    private final String depth; // Изменили тип на String

    public SubwayStationData(String station_name, String depth) {
        this.station_name = station_name;
        this.depth = depth;
    }

    public String getStation_name() {
        return station_name;
    }

    public String getDepth() {
        return depth;
    }

}
