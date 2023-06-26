import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Компания 1
        Company company1 = new Company();
        for (int i = 0; i < 180; i++) {
            company1.hire(new Operator(company1));
        }
        for (int i = 0; i < 80; i++) {
            company1.hire(new Manager(company1));
        }
        for (int i = 0; i < 10; i++) {
            company1.hire(new TopManager(company1));
        }

        // Вывод списка самых высоких зарплат в компании 1
        List<Employee> topSalaryStaff1 = company1.getTopSalaryStaff(15);
        System.out.println("Список из 10–15 самых высоких зарплат в компании 1:");
        printSalaryList(topSalaryStaff1);

        // Вывод списка самых низких зарплат в компании 1
        List<Employee> lowestSalaryStaff1 = company1.getLowestSalaryStaff(30);
        System.out.println("Список из 30 самых низких зарплат в компании 1:");
        printSalaryList(lowestSalaryStaff1);

        // Увольнение 50% сотрудников в компании 1
        int employeesToFire1 = company1.getEmployees().size() / 2;
        for (int i = 0; i < employeesToFire1; i++) {
            company1.fire(company1.getEmployees().get(i));
        }

        // Вывод списка самых высоких зарплат в компании 1 после увольнения
        topSalaryStaff1 = company1.getTopSalaryStaff(15);
        System.out.println("Список из 10–15 самых высоких зарплат в компании 1 после увольнения:");
        printSalaryList(topSalaryStaff1);

        // Вывод списка самых низких зарплат в компании 1 после увольнения
        lowestSalaryStaff1 = company1.getLowestSalaryStaff(30);
        System.out.println("Список из 30 самых низких зарплат в компании 1 после увольнения:");
        printSalaryList(lowestSalaryStaff1);


        // Компания 2
        Company company2 = new Company();
        for (int i = 0; i < 200; i++) {
            company2.hire(new Operator(company2));
        }
        for (int i = 0; i < 100; i++) {
            company2.hire(new Manager(company2));
        }
        for (int i = 0; i < 20; i++) {
            company2.hire(new TopManager(company2));
        }

        // Вывод списка самых высоких зарплат в компании 2
        List<Employee> topSalaryStaff2 = company2.getTopSalaryStaff(15);
        System.out.println("Список из 10–15 самых высоких зарплат в компании 2:");
        printSalaryList(topSalaryStaff2);

        // Вывод списка самых низких зарплат в компании 2
        List<Employee> lowestSalaryStaff2 = company2.getLowestSalaryStaff(30);
        System.out.println("Список из 30 самых низких зарплат в компании 2:");
        printSalaryList(lowestSalaryStaff2);

        // Увольнение 50% сотрудников в компании 2
        int employeesToFire2 = company2.getEmployees().size() / 2;
        for (int i = 0; i < employeesToFire2; i++) {
            company2.fire(company2.getEmployees().get(i));
        }

        // Вывод списка самых высоких зарплат в компании 2 после увольнения
        topSalaryStaff2 = company2.getTopSalaryStaff(15);
        System.out.println("Список из 10–15 самых высоких зарплат в компании 2 после увольнения:");
        printSalaryList(topSalaryStaff2);

        // Вывод списка самых низких зарплат в компании 2 после увольнения
        lowestSalaryStaff2 = company2.getLowestSalaryStaff(30);
        System.out.println("Список из 30 самых низких зарплат в компании 2 после увольнения:");
        printSalaryList(lowestSalaryStaff2);
    }

    public static void printSalaryList(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(formatSalary(employee.getMonthSalary()));
        }
        System.out.println();
    }

    public static String formatSalary(int salary) {
        return String.format("%d руб.", salary);
    }
}
