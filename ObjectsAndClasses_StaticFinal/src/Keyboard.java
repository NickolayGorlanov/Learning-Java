public class Keyboard {

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
