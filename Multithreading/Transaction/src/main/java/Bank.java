import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bank {
    private final Map<String, Account> accounts;
    private final Random random = new Random();
    private final Lock transferLock = new ReentrantLock();
    private final Lock fraudCheckLock = new ReentrantLock(); // Добавлен мьютекс для проверки мошенничества
    private final Logger logger = LoggerFactory.getLogger(Bank.class);
    private Map<String, Integer> fraudAttempts = new HashMap<>();

    public Bank() {
        accounts = new HashMap<>();
    }

    public boolean isFraud(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        if (amount > 50000) {
            // Блокируем поток для выполнения проверки мошенничества
            fraudCheckLock.lock();
            try {

                // Если обнаружено мошенничество, блокируем счета.
                logger.debug("Fraud detected: from {} to {} amount: {}", fromAccountNum, toAccountNum, amount);
                return true;
            } finally {
                // Всегда освобождаем мьютекс после завершения проверки мошенничества
                fraudCheckLock.unlock();
            }
        }
        return false;
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        logger.debug("Starting transfer from {} to {} amount: {}", fromAccountNum, toAccountNum, amount);

        System.out.println("Starting transfer from " + fromAccountNum + " to " + toAccountNum + " amount: " + amount);

        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Invalid account number");
        }

        Lock lock1 = fromAccountNum.compareTo(toAccountNum) < 0 ? fromAccount.getLock() : toAccount.getLock();
        Lock lock2 = fromAccountNum.compareTo(toAccountNum) < 0 ? toAccount.getLock() : fromAccount.getLock();

        try {
            lock1.lock();
            lock2.lock();

            if (isFraud(fromAccountNum, toAccountNum, amount)) {
                // Если обнаружено мошенничество, блокируем счета и обновляем их балансы.
                fromAccount.setBlocked(true);
                toAccount.setBlocked(true);
                fromAccount.setMoney(fromAccount.getMoney() - amount); // Уменьшаем сумму на счете отправителя
                toAccount.setMoney(toAccount.getMoney() + amount);     // Увеличиваем сумму на счете получателя
                logger.debug("Fraud detected during transfer: from {} to {} amount: {}", fromAccountNum, toAccountNum, amount);
                return;
            }

            if (!fromAccount.isBlocked() && !toAccount.isBlocked() && fromAccount.getMoney() >= amount) {
                fromAccount.setMoney(fromAccount.getMoney() - amount);
                toAccount.setMoney(toAccount.getMoney() + amount);
            }
        } finally {
            lock1.unlock();
            lock2.unlock();
        }

        logger.debug("Completed transfer from {} to {} amount: {}", fromAccountNum, toAccountNum, amount);

        // Разблокировка счетов после успешной транзакции
        fromAccount.setBlocked(false);
        toAccount.setBlocked(false);

        System.out.println("Completed transfer from " + fromAccountNum + " to " + toAccountNum);
    }

    public long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        if (account == null) {
            throw new IllegalArgumentException("Invalid account number");
        }
        return account.getMoney();
    }

    public long getSumAllAccounts() {
        long sum = 0;
        for (Account account : accounts.values()) {
            sum += account.getMoney();
        }
        return sum;
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccNumber(), account);
    }
}
