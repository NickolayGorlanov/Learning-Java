//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Random;

public class TopManager implements Employee {
    private final int baseSalary;


    public TopManager() {
        Random random = new Random();
        this.baseSalary = random.nextInt(150000) + 100000;

    }


    public int getMonthSalary() {
        return this.baseSalary;
    }
}
