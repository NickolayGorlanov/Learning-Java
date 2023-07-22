import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private static final Logger LOGGER = LogManager.getLogger(CustomerStorage.class);
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");

        try {
            if (components.length != 4) {
                throw new InvalidDataFormatException("Invalid number of components in the input data.");
            }

            String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];

            if (!isValidPhoneNumber(components[INDEX_PHONE])) {
                throw new InvalidPhoneNumberException("Invalid phone number format.");
            }

            if (!isValidEmail(components[INDEX_EMAIL])) {
                throw new InvalidEmailException("Invalid email format.");
            }

            storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
            LOGGER.info("Customer '{}' added successfully.", name);
        } catch (InvalidDataFormatException | InvalidPhoneNumberException | InvalidEmailException e) {
            LOGGER.error("Error adding customer: {}", e.getMessage());


        }

    }


    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Проверка формата телефонного номера
         if (phoneNumber.matches("\\+\\d{11}")) {
            return true;  // Верный формат
        } else {
            LOGGER.error("Invalid phone number format: {}", phoneNumber);
            return false; // Неверный формат
        }
    }

    private boolean isValidEmail(String email) {
        // Проверка формата email

        if (email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")) {
            return true;  // Верный формат
        } else {
            LOGGER.error("Invalid email format: {}", email);
            return false; // Неверный формат
        }
    }






    public static class InvalidDataFormatException extends RuntimeException {
        public InvalidDataFormatException(String message) {
            super(message);
        }
    }

    public static class InvalidPhoneNumberException extends RuntimeException {
        public InvalidPhoneNumberException(String message) {
            super(message);
        }
    }

    public static class InvalidEmailException extends RuntimeException {
        public InvalidEmailException(String message) {
            super(message);
        }
    }
}
