public class Computer {
    private final Processor processor;
    private final Memory memory;
    private final Storage storage;
    private final Screen screen;
    private final Keyboard keyboard;

    public Computer(Processor processor, Memory memory, Storage storage, Screen screen, Keyboard keyboard) {
        this.processor = processor;
        this.memory = memory;
        this.storage = storage;
        this.screen = screen;
        this.keyboard = keyboard;
    }

    public void printComputerDetails() {
        System.out.println("Processor: " + processor.toString());
        System.out.println("Memory: " + memory.toString());
        System.out.println("Storage: " + storage.toString());
        System.out.println("Screen: " + screen.toString());
        System.out.println("Keyboard: " + keyboard.toString());
    }
}
