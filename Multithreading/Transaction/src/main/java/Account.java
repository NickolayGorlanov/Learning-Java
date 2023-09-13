import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private long money;
    private String accNumber;

    private final Lock lock = new ReentrantLock(); // Поле lock для синхронизации
    private boolean blocked = false;

    public Account(String accNumber) {
        this.accNumber = accNumber;
    }

    public boolean isBlocked() {
        return blocked;
    }


    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }


    public Lock getLock() {
        return lock;
    }

}
