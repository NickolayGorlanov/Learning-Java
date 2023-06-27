

import java.util.Random;

public class TopManager implements Employee {
    private final int baseSalary;
    private final Company company;

    public TopManager(Company company) {
        Random random = new Random();
        this.baseSalary = random.nextInt(150000) + 100000;
        this.company = company;
    }


    public int getMonthSalary() {
        return this.company.getIncome() > 10000000 ? this.baseSalary + (int) ((double) this.baseSalary * 1.5) : this.baseSalary;
    }
}
