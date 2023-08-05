package DataCollector;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "https://skillbox-java.github.io/";
        WebPageParser parser = new WebPageParser(url);

        String html;
        List<SubwayLine> subwayLines = new ArrayList<>();
        List<SubwayStation> subwayStations = new ArrayList<>();

        try {
            html = parser.getHTML();
            subwayLines = parser.parseSubwayLines(html);
            subwayStations = parser.parseSubwayStations(html);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        try (FileWriter linesFileWriter = new FileWriter("subway_lines.json")) {
            gson.toJson(subwayLines, linesFileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter stationsFileWriter = new FileWriter("subway_stations.json")) {
            gson.toJson(subwayStations, stationsFileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Данные успешно разобраны и сохранены в JSON-файлах.");

        String folderPath = "/home/user/IdeaProjects/DataCollector/stations-data/data";
        FileSearcher fileSearcher = new FileSearcher();

        try {
            fileSearcher.searchFiles(folderPath);
            List<String> jsonFiles = fileSearcher.getJsonFiles();
            List<String> csvFiles = fileSearcher.getCsvFiles();

            List<SubwayStationData> subwayStationDataList = new ArrayList<>();

            System.out.println("Найденные JSON-файлы:");
            for (String jsonFile : jsonFiles) {
                System.out.println(jsonFile);

                // Чтение JSON данных из файла
                String jsonData;
                try {
                    jsonData = new String(Files.readAllBytes(Paths.get(jsonFile)));
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }

                List<SubwayStationData> stationDataList = JsonParser.parseJsonData(jsonData);
                if (stationDataList != null) {
                    subwayStationDataList.addAll(stationDataList);

                    // Вывод данных из разобранных JSON объектов
                    for (SubwayStationData stationData : stationDataList) {
                        System.out.println("Название станции: " + stationData.getStation_name());
                        String depth = stationData.getDepth();
                        System.out.println("Глубина: " + (depth != null ? depth : "Неизвестно"));
                        System.out.println();
                    }
                }
            }

            System.out.println("\nНайденные CSV-файлы:");
            for (String csvFile : csvFiles) {
                System.out.println(csvFile);

                // Парсинг CSV данных из файла
                List<SubwayStationDataCsv> stationDataList = CsvParser.parseCsvData(csvFile);
                subwayStationDataList.addAll(convertToSubwayStationData(stationDataList));

                // Вывод данных из разобранных CSV строк
                for (SubwayStationDataCsv stationData : stationDataList) {
                    System.out.println("Название станции: " + stationData.getName());
                    System.out.println("Дата открытия: " + stationData.getDate());
                    System.out.println();
                }
            }

            // Создание списка станций по линиям
            MetroData metroData = new MetroData(subwayStationDataList, subwayLines);

            try (FileWriter mapFileWriter = new FileWriter("map.json")) {
                gson.toJson(metroData, mapFileWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Создание списка свойств станций
            MetroData stationsData = new MetroData(subwayStationDataList, null);

            try (FileWriter stationsDataFileWriter = new FileWriter("stations.json")) {
                gson.toJson(stationsData, stationsDataFileWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("\nВсего найдено JSON-файлов: " + jsonFiles.size());
            System.out.println("Всего найдено CSV-файлов: " + csvFiles.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для конвертации List<SubwayStationDataCsv> в List<SubwayStationData>
    private static List<SubwayStationData> convertToSubwayStationData(List<SubwayStationDataCsv> csvDataList) {
        List<SubwayStationData> stationDataList = new ArrayList<>();
        for (SubwayStationDataCsv csvData : csvDataList) {
            SubwayStationData stationData = new SubwayStationData(csvData.getName(), csvData.getDate());

            stationDataList.add(stationData);
        }
        return stationDataList;
    }
}
