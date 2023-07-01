
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Company {
    private List<Employee> employees;
    private int income;

    public Company() {
        this.employees = new ArrayList();
        this.income = 0;
    }

    public Company(List<Employee> employees, int income) {
        this.employees = employees;
        this.income = income;
    }

    public void hire(Employee employee) {
        this.employees.add(employee);
    }

    public void hireAll(Collection<Employee> employees) {
        this.employees.addAll(employees);
    }

    public void fire(Employee employee) {
        this.employees.remove(employee);
    }

    public int getIncome() {
        return this.income;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        if (count >= 0 && count <= this.employees.size()) {
            List<Employee> sortedEmployees = new ArrayList(this.employees);
            sortedEmployees.sort(Comparator.comparingInt(Employee::getMonthSalary).reversed());
            return sortedEmployees.subList(0, count);
        } else {
            throw new IllegalArgumentException("Invalid count value");
        }
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        if (count >= 0 && count <= this.employees.size()) {
            List<Employee> sortedEmployees = new ArrayList(this.employees);
            sortedEmployees.sort(Comparator.comparingInt(Employee::getMonthSalary));
            return sortedEmployees.subList(0, count);
        } else {
            throw new IllegalArgumentException("Invalid count value");
        }
    }
}
