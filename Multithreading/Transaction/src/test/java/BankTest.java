import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {

    @Test
    public void testBankOperations() throws InterruptedException {
        Bank bank = new Bank();

        // Создаем два счета
        Account account1 = new Account("A123");
        Account account2 = new Account("B456");

        account1.setAccNumber("A123");
        account2.setAccNumber("B456");

        account1.setMoney(100000); // Исходный баланс: 100 000 долларов на счете1
        account2.setMoney(50000);  // Исходный баланс: 50 000 долларов на счете2

        bank.addAccount(account1);
        bank.addAccount(account2);

        // Проверяем балансы до транзакции
        assertEquals(100000, bank.getBalance("A123"));
        assertEquals(50000, bank.getBalance("B456"));

        // Выполняем мошенническую транзакцию
        bank.transfer("A123", "B456", 60000);

        // Проверяем балансы после мошеннической транзакции
        assertEquals(40000, bank.getBalance("A123"));
        assertEquals(110000, bank.getBalance("B456"));

        // Проверяем, что счета заблокированы
        assertTrue(account1.isBlocked());
        assertTrue(account2.isBlocked());
    }
}
