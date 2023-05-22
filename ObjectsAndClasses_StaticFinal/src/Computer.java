import java.util.ArrayList;
import java.util.List;

public class Computer {
    private final String vendor;
    private final String name;
    private Processor processor;
    private final List<Ram> ramList;
    private final List<HardDisc> hardDiscsList;
    private Screen screen;
    private Keyboard keyboard;

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
        this.ramList = new ArrayList<>();
        this.hardDiscsList = new ArrayList<>();
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public void addRam(Ram ram) {
        ramList.add(ram);
    }

    public void addHardDisc(HardDisc hardDisc) {
        hardDiscsList.add(hardDisc);
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public double getTotalWeight() {
        double totalWeight = 0.0;
        for (Ram ram : ramList) {
            totalWeight += ram.getWeight();
        }
        for (HardDisc hardDisc : hardDiscsList) {
            totalWeight += hardDisc.getWeight();
        }
        totalWeight += screen.getWeight();
        totalWeight += keyboard.getWeight();
        return totalWeight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Computer{\n");
        sb.append("  vendor='").append(vendor).append("'\n");
        sb.append("  name='").append(name).append("'\n");
        sb.append("  processor=").append(processor).append("\n");
        sb.append("  ramList=").append(ramList).append("\n");
        sb.append("  hardDiscsList=").append(hardDiscsList).append("\n");
        sb.append("  screen=").append(screen).append("\n");
        sb.append("  keyboard=").append(keyboard).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
