import java.util.*;

public class Company {
    private List<Employee> employees;
    private int income;

    public Company() {
        this.employees = new ArrayList<>();
        this.income = 0;
    }

    public Company(List<Employee> employees, int income) {
        this.employees = employees;
        this.income = income;
    }

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll(Collection<Employee> employees) {
        this.employees.addAll(employees);
    }

    public void fire(Employee employee) {
        employees.remove(employee);
    }

    public int getIncome() {
        return income;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        if (count < 0 || count > employees.size()) {
            throw new IllegalArgumentException("Invalid count value");
        }
        List<Employee> sortedEmployees = new ArrayList<>(employees);
        sortedEmployees.sort(Comparator.comparingInt(Employee::getMonthSalary).reversed());
        return sortedEmployees.subList(0, count);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        if (count < 0 || count > employees.size()) {
            throw new IllegalArgumentException("Invalid count value");
        }
        List<Employee> sortedEmployees = new ArrayList<>(employees);
        sortedEmployees.sort(Comparator.comparingInt(Employee::getMonthSalary));
        return sortedEmployees.subList(0, count);
    }
}



