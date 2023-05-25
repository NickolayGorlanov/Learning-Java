public class Screen {
    public enum MatrixType {
        TN,
        VA,
        IPS
    }

    private final double diagonal;
    private final MatrixType matrixType;
    private final double weight;

    public Screen(double diagonal, MatrixType matrixType, double weight) {
        this.diagonal = diagonal;
        this.matrixType = matrixType;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Diagonal: " + diagonal + " inches, Matrix Type: " + matrixType.name() + ", Weight: " + weight + " kg";
    }
}
