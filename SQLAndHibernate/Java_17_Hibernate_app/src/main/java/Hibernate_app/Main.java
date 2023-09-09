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

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata() // Создаем метаданные на основе конфигурации
                .getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Считываем данные из таблицы PurchaseList
            TypedQuery<PurchaseList> purchaseListQuery = session.createQuery("FROM PurchaseList", PurchaseList.class);
            List<PurchaseList> purchaseList = purchaseListQuery.getResultList();

            // Заполняем таблицу LinkedPurchaseList
            // Заполняем таблицу LinkedPurchaseList
            for (PurchaseList purchase : purchaseList) {
                // Получаем studentId и courseId из PurchaseList
                Long studentId = purchase.getStudentId(); // Предположим, что у PurchaseList есть метод getStudentId()
                Long courseId = purchase.getCourseId(); // Предположим, что у PurchaseList есть метод getCourseId()

                // Создаем объект LinkedPurchaseListKey
                LinkedPurchaseListKey key = new LinkedPurchaseListKey();
                key.setStudentId(studentId);
                key.setCourseId(courseId);

                // Создаем объект LinkedPurchaseList с использованием ключа
                LinkedPurchaseList linkedPurchase = new LinkedPurchaseList();
                linkedPurchase.setId(key);

                // Устанавливаем связи с Student и Course
                linkedPurchase.setStudent(session.get(Student.class, studentId)); // Получаем объект Student из базы данных
                linkedPurchase.setCourse(session.get(Course.class, courseId)); // Получаем объект Course из базы данных

                session.save(linkedPurchase);
            }


            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
