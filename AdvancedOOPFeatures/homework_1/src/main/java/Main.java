import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        sortBySalaryAndAlphabet(staff);
        System.out.println(staff);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        staff.sort(
                Comparator.comparing(
                                Employee::getSalary,
                                Comparator.nullsLast(Comparator.naturalOrder())
                        )
                        .thenComparing(
                                Employee::getName,
                                String.CASE_INSENSITIVE_ORDER
                        )
        );
    }



}