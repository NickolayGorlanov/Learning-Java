package DataCollector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSearcher {
    private final List<String> jsonFiles;
    private final List<String> csvFiles;

    public FileSearcher() {
        jsonFiles = new ArrayList<>();
        csvFiles = new ArrayList<>();
    }

    public void searchFiles(String folderPath) throws IOException {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path: " + folderPath);
            return;
        }

        searchInFolder(folder);
    }

    private void searchInFolder(File folder) {
        File[] files = folder.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                searchInFolder(file); // Рекурсивный вызов для обхода вложенных папок
            } else {
                String fileName = file.getName().toLowerCase();
                if (fileName.endsWith(".json")) {
                    jsonFiles.add(file.getAbsolutePath());
                } else if (fileName.endsWith(".csv")) {
                    csvFiles.add(file.getAbsolutePath());
                }
            }
        }
    }

    public List<String> getJsonFiles() {
        return jsonFiles;
    }

    public List<String> getCsvFiles() {
        return csvFiles;
    }
}
