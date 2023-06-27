//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Iterator;
import java.util.List;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Company company1 = new Company();

        int i;
        for(i = 0; i < 180; ++i) {
            company1.hire(new Operator(company1));
        }

        for(i = 0; i < 80; ++i) {
            company1.hire(new Manager(company1));
        }

        for(i = 0; i < 10; ++i) {
            company1.hire(new TopManager(company1));
        }

        List<Employee> topSalaryStaff1 = company1.getTopSalaryStaff(15);
        System.out.println("Список из 10–15 самых высоких зарплат в компании 1:");
        printSalaryList(topSalaryStaff1);
        List<Employee> lowestSalaryStaff1 = company1.getLowestSalaryStaff(30);
        System.out.println("Список из 30 самых низких зарплат в компании 1:");
        printSalaryList(lowestSalaryStaff1);
        int employeesToFire1 = company1.getEmployees().size() / 2;

        for(int j = 0; j < employeesToFire1; ++j) {
            company1.fire((Employee)company1.getEmployees().get(i));
        }

        topSalaryStaff1 = company1.getTopSalaryStaff(15);
        System.out.println("Список из 10–15 самых высоких зарплат в компании 1 после увольнения:");
        printSalaryList(topSalaryStaff1);
        lowestSalaryStaff1 = company1.getLowestSalaryStaff(30);
        System.out.println("Список из 30 самых низких зарплат в компании 1 после увольнения:");
        printSalaryList(lowestSalaryStaff1);
        Company company2 = new Company();

        int k;
        for(k = 0; k < 200; ++k) {
            company2.hire(new Operator(company2));
        }

        for(k = 0; k < 100; ++k) {
            company2.hire(new Manager(company2));
        }

        for(k = 0; k < 20; ++k) {
            company2.hire(new TopManager(company2));
        }

        List<Employee> topSalaryStaff2 = company2.getTopSalaryStaff(15);
        System.out.println("Список из 10–15 самых высоких зарплат в компании 2:");
        printSalaryList(topSalaryStaff2);
        List<Employee> lowestSalaryStaff2 = company2.getLowestSalaryStaff(30);
        System.out.println("Список из 30 самых низких зарплат в компании 2:");
        printSalaryList(lowestSalaryStaff2);
        int employeesToFire2 = company2.getEmployees().size() / 2;

        for(int p = 0; p < employeesToFire2; ++p) {
            company2.fire((Employee)company2.getEmployees().get(p));
        }

        topSalaryStaff2 = company2.getTopSalaryStaff(15);
        System.out.println("Список из 10–15 самых высоких зарплат в компании 2 после увольнения:");
        printSalaryList(topSalaryStaff2);
        lowestSalaryStaff2 = company2.getLowestSalaryStaff(30);
        System.out.println("Список из 30 самых низких зарплат в компании 2 после увольнения:");
        printSalaryList(lowestSalaryStaff2);
    }

    public static void printSalaryList(List<Employee> employees) {
        Iterator var1 = employees.iterator();

        while(var1.hasNext()) {
            Employee employee = (Employee)var1.next();
            System.out.println(formatSalary(employee.getMonthSalary()));
        }

        System.out.println();
    }

    public static String formatSalary(int salary) {
        return String.format("%d руб.", salary);
    }
}
