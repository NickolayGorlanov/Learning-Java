public class Processor {
    public enum ProcessorType {
        INTEL, AMD, ARM
    }
    private final String type;
    private final double frequency;
    private final int cores;
    private final double weight;

    public Processor(String type, double frequency, int cores, double weight) {
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
