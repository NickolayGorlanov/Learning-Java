public class Computer {
    private final Processor processor;
    private final Memory memory;
    private final Storage storage;
    private final Screen screen;
    private final Keyboard keyboard;

    public enum ScreenType {
        IPS(15.6, 0.5),
        TN(17.3, 0.8),
        OLED(17.3, 0.8);

        private final double diagonal;
        private final double weight;

        ScreenType(double diagonal, double weight) {
            this.diagonal = diagonal;
            this.weight = weight;
        }

        public double getDiagonal() {
            return diagonal;
        }

        public double getWeight() {
            return weight;
        }
    }

    public enum KeyboardType {
        MECHANICAL("Mechanical", true, 0.7),
        MEMBRANE("Membrane", false, 0.6);

        private final String type;
        private final boolean backlight;
        private final double weight;

        KeyboardType(String type, boolean backlight, double weight) {
            this.type = type;
            this.backlight = backlight;
            this.weight = weight;
        }

        public String getType() {
            return type;
        }

        public boolean hasBacklight() {
            return backlight;
        }

        public double getWeight() {
            return weight;
        }
    }

    public enum MemoryType {
        DDR2(0.3, 4),
        DDR3(0.4, 8),
        DDR4(0.5, 16);

        private final double weight;
        private final int capacity;

        MemoryType(double weight, int capacity) {
            this.weight = weight;
            this.capacity = capacity;
        }

        public double getWeight() {
            return weight;
        }

        public int getCapacity() {
            return capacity;
        }
    }

    public enum StorageType {
        HDD(0.7, 1000),
        SSD(0.5, 500),
        NVMe(0.4, 2000);

        private final double weight;
        private final int capacity;

        StorageType(double weight, int capacity) {
            this.weight = weight;
            this.capacity = capacity;
        }

        public double getWeight() {
            return weight;
        }

        public int getCapacity() {
            return capacity;
        }
    }

    public enum ProcessorType {
        INTEL("Intel", 3.2, 4, 0.8),
        AMD("AMD", 2.8, 6, 0.9),
        ARM("ARM", 2.0, 2, 0.5);

        private final String type;
        private final double frequency;
        private final int cores;
        private final double weight;

        ProcessorType(String type, double frequency, int cores, double weight) {
            this.type = type;
            this.frequency = frequency;
            this.cores = cores;
            this.weight = weight;
        }

        public String getType() {
            return type;
        }

        public double getFrequency() {
            return frequency;
        }

        public int getCores() {
            return cores;
        }

        public double getWeight() {
            return weight;
        }

        public Processor toProcessor() {
            return new Processor(type, frequency, cores, weight);
        }
    }

    public static class Processor {
        private final String type;
        private final double frequency;
        private final int cores;
        private final double weight;

        private Processor(String type, double frequency, int cores, double weight) {
            this.type = type;
            this.frequency = frequency;
            this.cores = cores;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Type: " + type + ", Frequency: " + frequency + " GHz, Cores: " + cores + ", Weight: " + weight + " kg";
        }
    }

    public static class Memory {
        private final String type;
        private final int capacity;
        private final double weight;

        private Memory(String type, int capacity, double weight) {
            this.type = type;
            this.capacity = capacity;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Type: " + type + ", Capacity: " + capacity + " GB, Weight: " + weight + " kg";
        }
    }

    public static class Storage {
        private final String type;
        private final int capacity;
        private final double weight;

        private Storage(String type, int capacity, double weight) {
            this.type = type;
            this.capacity = capacity;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Type: " + type + ", Capacity: " + capacity + " GB, Weight: " + weight + " kg";
        }
    }

    public static class Screen {
        private final double diagonal;
        private final String type;
        private final double weight;

        public Screen(double diagonal, String type, double weight) {
            this.diagonal = diagonal;
            this.type = type;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Diagonal: " + diagonal + " inches, Type: " + type + ", Weight: " + weight + " kg";
        }
    }

    public static class Keyboard {
        private final String type;
        private final boolean hasBacklight;
        private final double weight;

        public Keyboard(String type, boolean hasBacklight, double weight) {
            this.type = type;
            this.hasBacklight = hasBacklight;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Type: " + type + ", Backlight: " + (hasBacklight ? "Yes" : "No") + ", Weight: " + weight + " kg";
        }
    }

    public Computer(Processor processor, MemoryType memoryType, StorageType storageType, ScreenType screenType, KeyboardType keyboardType) {
        this.processor = processor;
        this.memory = new Memory(memoryType.toString(), memoryType.getCapacity(), memoryType.getWeight());
        this.storage = new Storage(storageType.toString(), storageType.getCapacity(), storageType.getWeight());
        this.screen = new Screen(screenType.getDiagonal(), screenType.name(), screenType.getWeight());
        this.keyboard = new Keyboard(keyboardType.getType(), keyboardType.hasBacklight(), keyboardType.getWeight());
    }

    @Override
    public String toString() {
        return "Processor: " + processor.toString() + "\n" +
                "Memory: " + memory.toString() + "\n" +
                "Storage: " + storage.toString() + "\n" +
                "Screen: " + screen.toString() + "\n" +
                "Keyboard: " + keyboard.toString();
    }
}
