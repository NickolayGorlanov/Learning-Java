package DataCollector;

public class SubwayStationDataCsv {
    private final String name;
    private final String date;


    public SubwayStationDataCsv(String name, String date) {
        this.name = name;
        this.date = date;

    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }


}
