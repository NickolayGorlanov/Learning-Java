
import java.util.Random;

public class Manager implements Employee {
    private final int baseSalary;

    public Manager() {
        Random random = new Random();
        this.baseSalary = random.nextInt(80000) + 70000;
    }


    public int getMonthSalary() {
        return this.baseSalary;
    }
}
