public class Keyboard {
    private final String model;
    private final double weight;
    private final boolean backlit;

    public Keyboard(String model, double weight) {
        this.model = model;
        this.weight = weight;
        this.backlit = false; // По умолчанию подсветка отключена
    }

    public Keyboard(String model, double weight, boolean backlit) {
        this.model = model;
        this.weight = weight;
        this.backlit = backlit;
    }

    public String getModel() {
        return model;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isBacklit() {
        return backlit;
    }

    @Override
    public String toString() {
        return "Keyboard{" +
                "model='" + model + '\'' +
                ", weight=" + weight +
                ", backlit=" + backlit +
                '}';
    }
}
