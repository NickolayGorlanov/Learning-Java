package Hibernate_app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;


public class Main {
    public static void main(String[] args) {
        String jdbcUrlHibernateApp = "jdbc:mariadb://localhost:3306/Hibernate_app";

        String user = "root";
        String password = "kol99";

        String sqlScriptFile = "/home/user/IdeaProjects/Hibernate_app/src/main/resources/skillbox_dump_wfk.sql"; // Путь к SQL-скрипту

        try (Connection connectionHibernateApp = DriverManager.getConnection(jdbcUrlHibernateApp, user, password);

             Statement statement = connectionHibernateApp.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(sqlScriptFile))) {



            String[] createTableScripts = {
                    "CREATE TABLE Students (" +
                            "    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT," +
                            "    name VARCHAR(45)," +
                            "    age INT UNSIGNED," +
                            "    registration_date DATETIME" +
                            ");",

                    "CREATE TABLE Teachers (" +
                            "    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT," +
                            "    name VARCHAR(45)," +
                            "    salary INT UNSIGNED," +
                            "    age INT UNSIGNED" +
                            ");",

                    "CREATE TABLE Courses (" +
                            "    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT," +
                            "    name VARCHAR(500)," +
                            "    duration INT UNSIGNED," +
                            "    type ENUM('DESIGN', 'PROGRAMMING', 'MARKETING', 'MANAGEMENT', 'BUSINESS')," +
                            "    description VARCHAR(500)," +
                            "    teacher_id INT UNSIGNED," +
                            "    student_count INT UNSIGNED," +
                            "    price INT UNSIGNED," +
                            "    price_per_hour FLOAT," +
                            "    FOREIGN KEY (teacher_id) REFERENCES Teachers(id)" +
                            ");",

                    "CREATE TABLE Subscriptions (" +
                            "    student_id INT UNSIGNED," +
                            "    course_id INT UNSIGNED," +
                            "    subscription_date DATETIME," +
                            "    PRIMARY KEY (student_id, course_id)," +
                            "    FOREIGN KEY (student_id) REFERENCES Students(id)," +
                            "    FOREIGN KEY (course_id) REFERENCES Courses(id)" +
                            ");",

                    "CREATE TABLE PurchaseList (" +
                            "    student_name VARCHAR(500)," +
                            "    course_name VARCHAR(500)," +
                            "    price INT," +
                            "    subscription_date DATETIME" +
                            ");"
            };

            for (String createTableScript : createTableScripts) {
                connectionHibernateApp.createStatement().executeUpdate(createTableScript);
            }

            String insertDataScript = "INSERT INTO PurchaseList (student_name, course_name, price, subscription_date) VALUES (?, ?, ?, ?)";

            try (PreparedStatement insertStatement = connectionHibernateApp.prepareStatement(insertDataScript)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("INSERT INTO `PurchaseList`")) {
                        String valuesPart = line.substring(line.indexOf("(") + 1, line.lastIndexOf(")"));
                        String[] values = valuesPart.split(",");

                        String studentName = values[0].replaceAll("'", "").trim();
                        String courseName = values[1].replaceAll("'", "").trim();
                        int price = Integer.parseInt(values[2].trim());
                        String subscriptionDateStr = values[3].replaceAll("'", "").trim();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        java.util.Date parsedDate = dateFormat.parse(subscriptionDateStr);
                        java.sql.Date subscriptionDate = new java.sql.Date(parsedDate.getTime());

                        ((PreparedStatement) insertStatement).setString(1, studentName);
                        insertStatement.setString(2, courseName);
                        insertStatement.setInt(3, price);
                        insertStatement.setDate(4, subscriptionDate);

                        insertStatement.executeUpdate();
                    }
                }

                System.out.println("Data from dump file inserted successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Tables created and SQL script executed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}