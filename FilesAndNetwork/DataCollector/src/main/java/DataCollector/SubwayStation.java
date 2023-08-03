package DataCollector;

public class SubwayStation {
    private final String name;
    private final String lineNumber;

    public SubwayStation(String name, String lineNumber) {
        this.name = name;
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return "Station: " + name + " (Line: " + lineNumber + ")";
    }
}