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
            String sqlQuery = "SELECT pl.course_name, " +
                    "COUNT(DISTINCT pl.subscription_date) / 8 AS average_purchases_per_month " +
                    "FROM PurchaseList pl " +
                    "WHERE YEAR(pl.subscription_date) = 2018 " +
                    "GROUP BY pl.course_name";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                double averagePurchases = resultSet.getDouble("average_purchases_per_month");

                System.out.println("Course: " + courseName + ", Average Purchases per Month: " + averagePurchases);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
