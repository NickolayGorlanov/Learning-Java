import java.util.Random;

public class Operator implements Employee {

    private final int baseSalary;
    private Company company;


    public Operator(Company company) {
        this.company = company;
        Random random = new Random();
        this.baseSalary = random.nextInt(50_000) + 50_000; // Зарплата от 50,000 до 99,999 руб.
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
