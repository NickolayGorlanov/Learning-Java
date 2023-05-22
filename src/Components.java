import java.util.ArrayList;
import java.util.List;

public class Components {
    private final List<Computer> computers;

    public Components() {
        this.computers = new ArrayList<>();
    }

    public void addComputer(Computer computer) {
        computers.add(computer);
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public double getTotalWeight() {
        double totalWeight = 0;
        for (Computer computer : computers) {
            totalWeight += computer.getTotalWeight();
        }
        return totalWeight;
    }
}
