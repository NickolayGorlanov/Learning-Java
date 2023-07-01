import java.util.List;

public class Main {

    private static final String STAFF_TXT = "AdvancedOOPFeatures/homework_1/data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        //TODO Метод должен вернуть сотрудника с максимальной зарплатой среди тех,
        // кто пришёл в году, указанном в переменной year
        Employee maxSalaryEmployee = null;
        int maxSalary = 0;

        for (Employee employee : staff) {
            if (employee.getWorkStart().getYear() + 1900 == year) {
                if (employee.getSalary() > maxSalary) {
                    maxSalary = employee.getSalary();
                    maxSalaryEmployee = employee;
                }
            }
        }

        return maxSalaryEmployee;
    }
}
