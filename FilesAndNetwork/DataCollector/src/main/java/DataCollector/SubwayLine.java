package DataCollector;

public class SubwayLine {
    private String name;
    private String number;

    public SubwayLine(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Line: " + name + " (Number: " + number + ")";
    }
}