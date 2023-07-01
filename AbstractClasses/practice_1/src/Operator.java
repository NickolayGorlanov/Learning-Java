

import java.util.Random;

public class Operator implements Employee {
    private final int baseSalary;

    public Operator() {
        Random random = new Random();
        this.baseSalary = random.nextInt(50000) + 30000;
    }


    public int getMonthSalary() {
        return this.baseSalary;
    }
}
