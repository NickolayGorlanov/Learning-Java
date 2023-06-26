import java.util.Random;

public class Manager implements Employee {

    private final int baseSalary;
    private Company company;


    public Manager(Company company) {
        this.company = company;
        Random random = new Random();
        this.baseSalary = random.nextInt(80_000) + 70_000; // Зарплата от 70,000 до 149,999 руб.
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public Company getCompany() {
        return null;
    }

    public int getMonthSalary() {
        return baseSalary;
    }
}
