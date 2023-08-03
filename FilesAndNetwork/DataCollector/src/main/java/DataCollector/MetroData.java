package DataCollector;

import java.util.List;

public class MetroData {
    private final List<SubwayStationData> stations;
    private final List<SubwayLine> lines;

    public MetroData(List<SubwayStationData> stations, List<SubwayLine> lines) {
        this.stations = stations;
        this.lines = lines;
    }

    public List<SubwayStationData> getStations() {
        return stations;
    }

    public List<SubwayLine> getLines() {
        return lines;
    }
}
