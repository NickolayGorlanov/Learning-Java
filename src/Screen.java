public class Screen {
    private String model;
    private double size;
    private double weight;

    public Screen(String model, double size, double weight) {
        this.model = model;
        this.size = size;
        this.weight = weight;
    }

    // Далее следуют геттеры для полей
    // и другие методы класса



    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "model='" + model + '\'' +
                ", size=" + size +
                ", weight=" + weight +
                '}';
    }
}
