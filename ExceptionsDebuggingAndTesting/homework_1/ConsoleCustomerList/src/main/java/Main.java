import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String HELP_TEXT = "Command examples:\n" + COMMAND_EXAMPLES;

    public static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static final Logger ERRORS_LOGGER = LogManager.getLogger("ErrorsLogger");
    public static final Logger QUERIES_LOGGER = LogManager.getLogger("QueriesLogger");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        LOGGER.info("Application started.");

        while (true) {
            try {
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);

                QUERIES_LOGGER.info("Command: {}", command);

                if (tokens[0].equals("add")) {
                    try {
                        executor.addCustomer(tokens[1]);
                    } catch (CustomerStorage.InvalidDataFormatException | CustomerStorage.InvalidPhoneNumberException | CustomerStorage.InvalidEmailException e) {
                        System.err.println("An error occurred: " + e.getMessage());
                        ERRORS_LOGGER.error("Error adding customer: {}", e.getMessage());
                    }
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                } else if (tokens[0].equals("remove")) {
                    executor.removeCustomer(tokens[1]);
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help")) {
                    System.out.println(HELP_TEXT);
                } else if (tokens[0].equals("exit")) {
                    break; // Выход из цикла при вводе команды "exit"
                } else {
                    System.out.println(COMMAND_ERROR);
                    ERRORS_LOGGER.error("Invalid command: {}", command);
                }
            } catch (RuntimeException e) {
                System.err.println("An error occurred: " + e.getMessage());
                ERRORS_LOGGER.error("Error executing command: {}", e.getMessage());
            }
        }

        LOGGER.info("Application stopped.");
    }
}
