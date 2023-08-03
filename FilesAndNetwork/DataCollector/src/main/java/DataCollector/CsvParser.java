package DataCollector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    public static List<SubwayStationDataCsv> parseCsvData(String csvFilePath) throws IOException {
        List<SubwayStationDataCsv> subwayStationDataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                // Пропускаем заголовок
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 2) { // Ожидаем два аргумента: имя и дата
                    String name = data[0].trim();
                    String date = data[1].trim();
                    subwayStationDataList.add(new SubwayStationDataCsv(name, date));
                }
            }
        }

        return subwayStationDataList;
    }
}
