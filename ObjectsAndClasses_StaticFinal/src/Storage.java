public class Storage {
    public enum StorageType {
        HDD, SSD, NVMe
    }
    private final String type;
    private final int capacity;
    private final double weight;

    public Storage(String type, int capacity, double weight) {
        this.type = type;
        this.capacity = capacity;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Capacity: " + capacity + " GB, Weight: " + weight + " kg";
    }
}
