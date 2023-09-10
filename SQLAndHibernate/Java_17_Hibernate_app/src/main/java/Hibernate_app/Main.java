package Hibernate_app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.TypedQuery;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Инициализация Hibernate
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        try (SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .getSessionFactoryBuilder()
                .build(); Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Считываем данные из таблицы Subscriptions
            TypedQuery<Subscription> subscriptionQuery = session.createQuery("FROM Subscription", Subscription.class);
            List<Subscription> subscriptions = subscriptionQuery.getResultList();

            // Заполняем таблицу LinkedPurchaseList
            for (Subscription subscription : subscriptions) {
                // Получаем studentId и courseId из Subscription
                Long studentId = subscription.getStudent().getId();
                Long courseId = subscription.getCourse().getId();

                // Создаем объект LinkedPurchaseListKey
                LinkedPurchaseListKey key = new LinkedPurchaseListKey();
                key.setStudentId(studentId);
                key.setCourseId(courseId);

                // Создаем объект LinkedPurchaseList с использованием ключа
                LinkedPurchaseList linkedPurchase = new LinkedPurchaseList();
                linkedPurchase.setId(key);

                // Устанавливаем связи с Student и Course
                linkedPurchase.setStudent(session.get(Student.class, studentId));
                linkedPurchase.setCourse(session.get(Course.class, courseId));

                session.save(linkedPurchase);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry); // Закрытие реестра
        }
    }
}
