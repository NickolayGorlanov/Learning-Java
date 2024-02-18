package practice;

import java.util.Scanner;


public class TrucksAndContainers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Получение количества коробок от пользователя
        int boxes = scanner.nextInt();

        int boxesPerContainer = 27;
        int containersPerTruck = 12;

        int truckCount = 0;
        int containerCount = 0;
        int boxCount = 0;

        int requiredTrucks = (int) Math.ceil((double) boxes / (containersPerTruck * boxesPerContainer));
        int requiredContainers = (int) Math.ceil((double) boxes / boxesPerContainer);

        // Вывод грузовиков и контейнеров
        while (boxCount < boxes) {
            System.out.println("Грузовик: " + (truckCount + 1));
            truckCount++;

            for (int i = 0; i < containersPerTruck; i++) {
                System.out.println("\tКонтейнер: " + (containerCount + 1));
                containerCount++;

                for (int j = 0; j < boxesPerContainer && boxCount < boxes; j++) {
                    System.out.println("\t\tЯщик: " + (boxCount + 1));
                    boxCount++;
                }
                if (boxCount >= boxes) {
                    break; // Останавливаем вывод, если выгружены все ящики
                }
            }

            if (boxCount >= boxes) {
                break; // Останавливаем вывод, если выгружены все ящики
            }
        }

        System.out.println("Необходимо:");
        System.out.println("грузовиков - " + requiredTrucks + " шт.");
        System.out.println("контейнеров - " + requiredContainers + " шт.");
    }
}
