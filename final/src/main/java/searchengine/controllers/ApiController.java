package searchengine.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import searchengine.dto.statistics.StatisticsResponse;
import searchengine.model.IndexingService;
import searchengine.services.StatisticsService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final StatisticsService statisticsService;
    private final IndexingService indexingService;

    public ApiController(StatisticsService statisticsService, IndexingService indexingService) {
        this.statisticsService = statisticsService;
        this.indexingService = indexingService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

    @GetMapping("/startIndexing")
    public ResponseEntity<Map<String, Object>> startIndexing() throws Exception {
        boolean success = indexingService.startIndexing(); // Вызов метода запуска индексации из вашего сервиса
        Map<String, Object> response = new HashMap<>();

        if (success) {
            response.put("result", true);
        } else {
            response.put("result", false);
            response.put("error", "Индексация уже запущена");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/stopIndexing")
    public ResponseEntity<Map<String, Object>> stopIndexing() {
        boolean success = indexingService.stop(); // Вызов метода остановки индексации из вашего сервиса
        Map<String, Object> response = new HashMap<>();

        if (success) {
            response.put("result", true);
        } else {
            response.put("result", false);
            response.put("error", "Индексация остановлена пользователем");
        }

        return ResponseEntity.ok(response);
    }
}
