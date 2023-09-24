import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class XmlLoader extends DefaultHandler {

    public static void main(String[] args) {
        Main.main(args);
    }
    private final Connection connection;
    private PreparedStatement insertStatement;
    private int batchCounter = 0;

    private static final int BATCH_SIZE = 100000;

    public XmlLoader(Connection connection) {
        this.connection = connection;
        try {
            insertStatement = connection.prepareStatement(
                    "INSERT INTO voter_count(name, birthDate, `count`) VALUES (?, ?, 1) " +
                            "ON DUPLICATE KEY UPDATE `count` = `count` + 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void parseXml(String xmlFileName) {
        try (InputStream inputStream = new FileInputStream(xmlFileName)) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(inputStream, this);
            executeBatch(); // Выполняем вставку после обработки всех записей
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("voter".equals(qName)) {
            String name = attributes.getValue("name");
            String birthDayStr = attributes.getValue("birthDay");
            try {
                insertVoterIntoDatabase(name, new SimpleDateFormat("yyyy.MM.dd").parse(birthDayStr));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void insertVoterIntoDatabase(String name, Date birthDate) {
        try {
            insertStatement.setString(1, name);
            insertStatement.setDate(2, new java.sql.Date(birthDate.getTime()));
            insertStatement.addBatch();
            batchCounter++;
            if (batchCounter >= BATCH_SIZE) {
                executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeBatch() {
        try {
            insertStatement.executeBatch();
            insertStatement.clearBatch();
            batchCounter = 0;
            // Добавляем коммит после каждого батча
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            executeBatch();
            if (insertStatement != null) {
                insertStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}