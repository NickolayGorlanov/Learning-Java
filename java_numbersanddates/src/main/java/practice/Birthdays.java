package practice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Birthdays {

    public static void main(String[] args) {
        int day = 31;
        int month = 12;
        int year = 1990;

        System.out.println(collectBirthdays(year, month, day));
    }

    public static String collectBirthdays(int year, int month, int day) {
        StringBuilder sb = new StringBuilder();

        LocalDate birthday = LocalDate.of(year, month, day); // Создаем объект LocalDate для указанной даты рождения
        LocalDate currentDate = LocalDate.now(); // Получаем текущую дату
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - EEE"); // Создаем форматтер для преобразования даты в строку

        int count = 0;
        while (!birthday.isAfter(currentDate)) { // Выполняем цикл, пока дата рождения не станет позже текущей даты
            sb.append(count).append(" - ").append(birthday.format(formatter)).append(System.lineSeparator());//оздание строк с переносом на новую строку
            birthday = birthday.plusYears(1); // Увеличиваем дату рождения на один год
            count++;
        }

        return sb.toString();
    }
}
