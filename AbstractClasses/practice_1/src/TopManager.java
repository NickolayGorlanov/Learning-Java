import java.util.Random;

public class TopManager implements Employee {
    private final int baseSalary;
    private Company company;

    public TopManager(Company company) {
        Random random = new Random();
        this.baseSalary = random.nextInt(150_000) + 100_000; // Зарплата от 100,000 до 249,999 руб.
        this.company = company;
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
        if (company.getIncome() > 10_000_000) {
            return baseSalary + (int) (baseSalary * 1.5); // Зарплата с бонусом
        } else {
            return baseSalary;
        }
    }
}
