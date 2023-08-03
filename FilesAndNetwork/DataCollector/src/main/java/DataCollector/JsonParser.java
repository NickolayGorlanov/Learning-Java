package DataCollector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JsonParser {
    public static List<SubwayStationData> parseJsonData(String jsonData) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Double.class, (JsonDeserializer<Double>) (json, typeOfT, context) -> {
                    String numberString = json.getAsString().replace(",", ".");
                    try {
                        return Double.parseDouble(numberString);
                    } catch (NumberFormatException e) {
                        throw new JsonParseException("Некорректный формат числа: " + numberString, e);
                    }
                })
                .create();

        try {
            return gson.fromJson(jsonData, new TypeToken<List<SubwayStationData>>() {}.getType());
        } catch (JsonParseException e) {
            System.err.println("Ошибка при разборе JSON-файла: " + e.getMessage());
            return null;
        }
    }
}
