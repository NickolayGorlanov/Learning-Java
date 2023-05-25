public class Memory {
    public enum MemoryType {
        DDR2, DDR3, DDR4
    }
    private final String type;
    private final int capacity;
    private final double weight;

    public Memory(String type, int capacity, double weight) {
        this.type = type;
        this.capacity = capacity;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Capacity: " + capacity + " GB, Weight: " + weight + " kg";
    }
}
