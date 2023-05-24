public class Keyboard {
    private final String type;
    private final boolean backlight;
    private final double weight;

    public Keyboard(String type, boolean backlight, double weight) {
        this.type = type;
        this.backlight = backlight;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Backlight: " + (backlight ? "Yes" : "No") + ", Weight: " + weight + " kg";
    }
}
