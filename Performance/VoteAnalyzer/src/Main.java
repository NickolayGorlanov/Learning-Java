import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String xmlFileName = "res/data-1572M.xml";
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();
        try {
            // Устанавливаем ручное управление транзакциями и отключаем автокоммит
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        XmlLoader xmlLoader = new XmlLoader(connection);
        xmlLoader.parseXml(xmlFileName);
    }
}
