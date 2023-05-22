public class Main {
    public static void main(String[] args) {
        Processor processor1 = new Processor(2.4, 2, "ProcessorModel", 0.5);
        Processor processor2 = new Processor(2.8, 2, "ProcessorModel2", 0.6);

        Ram ram1 = new Ram("RamModel1", 8, 0.2);
        Ram ram2 = new Ram("RamModel2", 16, 0.4);
        Ram ram3 = new Ram("RamModel3", 4, 0.3);
        Ram ram4 = new Ram("RamModel4", 8, 0.6);

        HardDisc hardDisc1 = new HardDisc("HardDiscModel1", 500, 0.8);
        HardDisc hardDisc2 = new HardDisc("HardDiscModel2", 1000, 1.2);
        HardDisc hardDisc3 = new HardDisc("HardDiscModel3", 250, 0.5);
        HardDisc hardDisc4 = new HardDisc("HardDiscModel4", 500, 1.0);

        Screen screen1 = new Screen("ScreenModel", 15.6, 1.0);
        Screen screen2 = new Screen("ScreenModel2", 13.3, 0.8);

        Keyboard keyboard1 = new Keyboard("KeyboardModel", 0.3);
        Keyboard keyboard2 = new Keyboard("KeyboardModel2", 0.4, true);

        Computer computer1 = new Computer("Vendor1", "ComputerName1");
        computer1.setProcessor(processor1);
        computer1.addRam(ram1);
        computer1.addRam(ram2);
        computer1.addHardDisc(hardDisc1);
        computer1.addHardDisc(hardDisc2);
        computer1.setScreen(screen1);
        computer1.setKeyboard(keyboard1);

        Computer computer2 = new Computer("Vendor2", "ComputerName2");
        computer2.setProcessor(processor2);
        computer2.addRam(ram3);
        computer2.addRam(ram4);
        computer2.addHardDisc(hardDisc3);
        computer2.addHardDisc(hardDisc4);
        computer2.setScreen(screen2);
        computer2.setKeyboard(keyboard2);

        System.out.println(computer1);
        System.out.println("\nTotal weight: " + computer1.getTotalWeight());

        System.out.println(computer2);
        System.out.println("\nTotal weight: " + computer2.getTotalWeight());
    }
}
