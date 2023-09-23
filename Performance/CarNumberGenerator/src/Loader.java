import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        int threadCount = 4; // Количество потоков
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        try {
            char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            int fileCount = 10; // Количество файлов
            BufferedWriter[] writers = new BufferedWriter[fileCount];

            for (int i = 0; i < fileCount; i++) {
                writers[i] = new BufferedWriter(new FileWriter("res/numbers_" + i + ".txt"));
            }

            for (int number = 1; number < 1000; number++) {
                int regionCode = 199;
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            String carNumber = firstLetter + padNumber(number, 3) +
                                    secondLetter + thirdLetter + padNumber(regionCode, 2);

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

    private static String padNumber(int number, int numberLength) {
        return String.format("%0" + numberLength + "d", number);
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
