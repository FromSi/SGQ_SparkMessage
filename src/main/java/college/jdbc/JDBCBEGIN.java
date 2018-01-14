package college.jdbc;

import college.UsersCBK;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCBEGIN {
    private URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
    private final String url = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
    private final String login = dbUri.getUserInfo().split(":")[0];
    private final String password = dbUri.getUserInfo().split(":")[1];
    //Переменные для работы с БД в Java
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public JDBCBEGIN() throws URISyntaxException {
        try {
            //Соединение с БД в Java
            connection = DriverManager.getConnection(url, login, password);
            //Передаем управление БД statement
            statement = connection.createStatement();
        } catch (SQLException e) {
            //Возвращаем ответ в консоль
            System.out.println("Error SQL Connecting");
        }
    }

    public List<UsersCBK> addNotifAll(){
        List<UsersCBK> usersCBKS = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("select iduser from users");
            while (resultSet.next()) {
                usersCBKS.add(new UsersCBK(resultSet.getInt("iduser")));
            }
            //Возвращаем ответ
            return usersCBKS;
        } catch (Exception e) {
            //Возвращаем ответ
            return usersCBKS;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
