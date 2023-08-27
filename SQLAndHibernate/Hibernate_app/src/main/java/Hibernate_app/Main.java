package Hibernate_app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String sqlScriptFile = "/home/user/IdeaProjects/Hibernate_app/src/main/resources/skillbox_dump_wfk.sql"; // Path to the SQL script

        // Create Hibernate session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession();
             BufferedReader reader = new BufferedReader(new FileReader(sqlScriptFile))) {

            session.beginTransaction();

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Processing line: " + line);
                if (line.startsWith("INSERT INTO `PurchaseList`")) {
                    System.out.println("Processing line: " + line);

                    int startIndex = line.indexOf("(");
                    int endIndex = line.lastIndexOf(")");

                    if (startIndex != -1 && endIndex != -1) {
                        String valuesPart = line.substring(startIndex + 1, endIndex);
                        String[] values = valuesPart.split(",");
                        System.out.println("Extracted values: " + Arrays.toString(values));

                        if (values.length >= 5) {
                            String studentName = values[1].replaceAll("'", "").trim();
                            String courseName = values[2].replaceAll("'", "").trim();
                            int price = Integer.parseInt(values[3].trim());
                            String subscriptionDateStr = values[4].replaceAll("'", "").trim();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime subscriptionDateTime = LocalDateTime.parse(subscriptionDateStr, formatter);

                            PurchaseList purchaseList = new PurchaseList(studentName, courseName, price, subscriptionDateTime);
                            session.save(purchaseList);
                            System.out.println("Processing line: " + line);
                        } else {
                            System.out.println("Skipping line due to insufficient values: " + line);
                        }
                    } else {
                        System.out.println("Skipping line due to invalid format: " + line);
                    }
                }
            }

            session.getTransaction().commit();

            System.out.println("Data from the dump file inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
