public class Main {
    public static void main(String[] args) {


        // Создание компонентов клавиатур
        Computer.KeyboardType keyboard1 = Computer.KeyboardType.MECHANICAL;
        Computer.KeyboardType keyboard2 = Computer.KeyboardType.MEMBRANE;

        // Создание типов экранов
        Computer.ScreenType screen1 = Computer.ScreenType.IPS;
        Computer.ScreenType screen2 = Computer.ScreenType.TN;
        Computer.ScreenType screen3 = Computer.ScreenType.OLED;

        // Создание типов процессоров
        Computer.ProcessorType processor1 = Computer.ProcessorType.AMD;
        Computer.ProcessorType processor2 = Computer.ProcessorType.INTEL;
        Computer.ProcessorType processor3 = Computer.ProcessorType.ARM;

        // Создание типов памяти
        Computer.MemoryType memory1 = Computer.MemoryType.DDR4;
        Computer.MemoryType memory2 = Computer.MemoryType.DDR3;
        Computer.MemoryType memory3 = Computer.MemoryType.DDR2;

        // Создание типов хранилищ
        Computer.StorageType storage1 = Computer.StorageType.NVMe;
        Computer.StorageType storage2 = Computer.StorageType.SSD;
        Computer.StorageType storage3 = Computer.StorageType.HDD;

        // Создание объектов компьютеров
        Computer computer1 = new Computer(processor1.toProcessor(), memory1, storage1, screen1, keyboard1);
        Computer computer2 = new Computer(processor2.toProcessor(), memory2, storage2, screen2, keyboard2);
        Computer computer3 = new Computer(processor3.toProcessor(), memory3, storage3, screen3, keyboard1);




        // Вывод спецификаций компьютеров
        System.out.println();
        System.out.println(computer1.toString());
        System.out.println();
        System.out.println(computer2.toString());
        System.out.println();
        System.out.println(computer3.toString());

    }

}