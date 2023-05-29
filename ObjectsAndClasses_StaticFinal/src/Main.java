public class Main {
    public static void main(String[] args) {

        Processor processor1 = new Processor("AMD", 2.8, 6, 0.9);
        Memory memory1 = new Memory("DDR4", 16, 0.5);
        Storage storage1 = new Storage("NVMe", 2000, 0.4);
        Screen screen1 = new Screen(15.6, MatrixType.IPS, 0.5);
        Keyboard keyboard1 = new Keyboard("Mechanical", true, 0.7);
        Computer computer1 = new Computer(processor1, memory1, storage1, screen1, keyboard1);

        Processor processor2 = new Processor("Intel", 3.2, 4, 0.8);
        Memory memory2 = new Memory("DDR3", 8, 0.4);
        Storage storage2 = new Storage("SSD", 500, 0.5);
        Screen screen2 = new Screen(17.3, MatrixType.TN, 0.8);
        Keyboard keyboard2 = new Keyboard("Membrane", false, 0.6);
        Computer computer2 = new Computer(processor2, memory2, storage2, screen2, keyboard2);

        Processor processor3 = new Processor("ARM", 3.0, 8, 1.0);
        Memory memory3 = new Memory("DDR4", 32, 0.7);
        Storage storage3 = new Storage("HDD", 1000, 0.6);
        Screen screen3 = new Screen(13.3, MatrixType.VA, 0.6);
        Keyboard keyboard3 = new Keyboard("Mechanical", true, 0.7);
        Computer computer3 = new Computer(processor3, memory3, storage3, screen3, keyboard3);


        System.out.println();
        System.out.println("Computer 1:");
        computer1.printComputerDetails();

        System.out.println("\nComputer 2:");
        computer2.printComputerDetails();

        System.out.println("\nComputer 3:");
        computer3.printComputerDetails();
    }
}
