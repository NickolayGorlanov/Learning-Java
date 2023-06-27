

import java.util.Random;

public class Operator implements Employee {
    private final int baseSalary;

    public Operator(Company company) {
        Random random = new Random();
        this.baseSalary = random.nextInt(50000) + '썐';
    }


    public int getMonthSalary() {
        return this.baseSalary;
    }
}
