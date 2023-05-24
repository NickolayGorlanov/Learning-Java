public class Main {
    public static void main(String[] args) {
        Processor processor1 = new Processor("AMD", 2.8, 6, 0.9);
        Memory memory1 = new Memory("DDR4", 16, 0.5);
        Storage storage1 = new Storage("NVMe", 2000, 0.4);
        Screen screen1 = new Screen(15.6, "IPS", 0.5);
        Keyboard keyboard1 = new Keyboard("Mechanical", true, 0.7);

        Processor processor2 = new Processor("Intel", 3.2, 4, 0.8);
        Memory memory2 = new Memory("DDR3", 8, 0.4);
        Storage storage2 = new Storage("SSD", 500, 0.5);
        Screen screen2 = new Screen(17.3, "TN", 0.8);
        Keyboard keyboard2 = new Keyboard("Membrane", false, 0.6);

        Computer computer1 = new Computer(processor1, memory1, storage1, screen1, keyboard1);
        Computer computer2 = new Computer(processor2, memory2, storage2, screen2, keyboard2);

        System.out.println();
        computer1.printComputerDetails();
        System.out.println();
        computer2.printComputerDetails();
    }
}
