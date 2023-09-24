import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    private static final StringBuilder stringBuilder = new StringBuilder(); // Поле для StringBuilder

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        int threadCount = Runtime.getRuntime().availableProcessors(); // Определяем количество доступных ядер
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        try {
            char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            int fileCount = 10; // Количество файлов
            BufferedWriter[] writers = new BufferedWriter[fileCount];

            for (int i = 0; i < fileCount; i++) {
                writers[i] = new BufferedWriter(new FileWriter("C:\\Users\\New\\IdeaProjects\\Performance\\CarNumberGenerator\\res\\numbers_" + i + ".txt"));
            }

            for (int number = 1; number < 1000; number++) {
                int regionCode = 199;
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            String carNumber = formatCarNumber(firstLetter, number, secondLetter, thirdLetter, regionCode);

                            // Выбираем случайный индекс файла и записываем в него
                            int randomFileIndex = (int) (Math.random() * fileCount);
                            executor.submit(() -> writeToFileWithBuffer(writers[randomFileIndex], carNumber));
                        }
                    }
                }
            }

            for (int i = 0; i < fileCount; i++) {
                writers[i].close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Подождем, пока все потоки завершат работу
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String formatCarNumber(char firstLetter, int number, char secondLetter, char thirdLetter, int regionCode) {
        stringBuilder.setLength(0); // Очищаем StringBuilder
        stringBuilder.append(firstLetter);
        stringBuilder.append(String.format("%03d", number));
        stringBuilder.append(secondLetter);
        stringBuilder.append(thirdLetter);
        stringBuilder.append(String.format("%02d", regionCode));
        return stringBuilder.toString();
    }

    private static void writeToFileWithBuffer(BufferedWriter writer, String carNumber) {
        try {
            writer.write(carNumber);
            writer.newLine(); // Автоматически добавляет символ новой строки
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
