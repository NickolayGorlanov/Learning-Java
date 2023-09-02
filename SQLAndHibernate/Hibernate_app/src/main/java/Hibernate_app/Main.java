package Hibernate_app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        createSkillboxDatabase(); // Создаем базу данных
        createDatabaseTables();
        loadDatabaseDump();
        executeHibernateApplication();
    }

    private static void createSkillboxDatabase() {
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "kol89";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            // Создаем базу данных skillbox, если она не существует
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS skillbox");
        } catch (Exception e) {
            logger.error("Error creating database: " + e.getMessage(), e);
        }
    }


    private static void createDatabaseTables() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Course.class) // Добавляем сущности Hibernate
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(Subscription.class)
                .addAnnotatedClass(PurchaseList.class)
                .addAnnotatedClass(LinkedPurchaseList.class) // Добавляем класс LinkedPurchaseList
                .getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        session.close();
        sessionFactory.close();
    }

    private static void loadDatabaseDump() {
        String dumpFilePath = "skillbox_dump_wfk.sql"; // путь к SQL-скрипту
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String password = "kol89";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            InputStream inputStream = Main.class.getResourceAsStream("/" + dumpFilePath);
            if (inputStream == null) {
                throw new FileNotFoundException("SQL dump file not found.");
            }

            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            StringBuilder query = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                query.append(line).append("\n");
                if (line.trim().endsWith(";")) {
                    statement.executeUpdate(query.toString());
                    query.setLength(0); // Очищаем строку запроса
                }
            }
        } catch (Exception e) {
            logger.error("Error loading database dump: " + e.getMessage(), e);
        }
    }

    private static void executeHibernateApplication() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(Subscription.class)
                .addAnnotatedClass(PurchaseList.class)
                .addAnnotatedClass(LinkedPurchaseList.class)
                .getMetadataBuilder().build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Пример использования findStudentByName и findCourseByName
            Student student = findStudentByName(session);
            Course course = findCourseByName(session);

            if (student != null && course != null) {
                // Создайте LinkedPurchaseList и установите студента и курс
                LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
                linkedPurchaseList.setStudent(student);
                linkedPurchaseList.setCourse(course);

                // Сохраните LinkedPurchaseList в базу данных
                session.save(linkedPurchaseList);

                // Фиксируем транзакцию
                transaction.commit();
            }
        } catch (Exception e) {
            // В случае ошибки откатываем транзакцию
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error executing Hibernate application: " + e.getMessage(), e);
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    private static Student findStudentByName(Session session) {
        try {
            TypedQuery<Student> query = session.createQuery("FROM Student WHERE name = :studentName", Student.class);
            query.setParameter("studentName", "student_id");
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Если студент с указанным именем не найден, вернуть null или обработать исключение
            return null;
        }
    }

    private static Course findCourseByName(Session session) {
        try {
            TypedQuery<Course> query = session.createQuery("FROM Course WHERE name = :courseName", Course.class);
            query.setParameter("courseName", "course_id");
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Если курс с указанным именем не найден, вернуть null или обработать исключение
            return null;
        }
    }
}
