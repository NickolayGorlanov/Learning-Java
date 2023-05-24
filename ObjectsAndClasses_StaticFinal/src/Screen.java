public class Screen {
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
