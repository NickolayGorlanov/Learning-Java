package SQLAndHibernate.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AveragePurchasesPerMonth {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mariadb://localhost:3306/SQLAndHibernate";
        String username = "***";
        String password = "***";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sqlQuery = "SELECT pl.course_name, pl.subscription_date " +
                    "FROM PurchaseList pl " +
                    "WHERE YEAR(pl.subscription_date) = 2018 " +
                    "ORDER BY pl.course_name, pl.subscription_date";  // Отсортировать по курсам и датам

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String subscriptionDate = resultSet.getString("subscription_date");

                System.out.println("Course: " + courseName + ", Subscription Date: " + subscriptionDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
