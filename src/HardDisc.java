public class HardDisc {
    private String model;
    private int capacity;
    private double weight;

    public HardDisc(String model, int capacity, double weight) {
        this.model = model;
        this.capacity = capacity;
        this.weight = weight;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "HardDisc{" +
                "model='" + model + '\'' +
                ", capacity=" + capacity +
                ", weight=" + weight +
                '}';
    }
}
