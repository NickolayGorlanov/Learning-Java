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
            for (PurchaseList purchase : purchaseList) {
                String student = purchase.getStudent();
                String course = purchase.getCourse();
                LinkedPurchaseList linkedPurchase = new LinkedPurchaseList(student, course);
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
