package college.jdbc;

import spark.Request;

import java.sql.*;

public class JDBCPOST {
    //Ссылка, логин и пароль для входа в БД
    private final String url = "jdbc:mysql://eu-cdbr-west-01.cleardb.com:3306/heroku_4af59489dcca747?autoReconnect=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8";
    private final String login = "b9f7039a09def9";
    private final String password = "ca1f4a27";
    //Переменные для работы с БД в Java
    private Connection connection;
    private Statement statement;

    public JDBCPOST() {
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

    public String createUser(Request request) {
        if (request.queryParams("avatar").length() >= 4 &&
                request.queryParams("nick").length() >= 4 &&
                request.queryParams("login").length() >= 4 &&
                request.queryParams("password").length() >= 4 &&
                request.queryParams("number").length() >= 4){
            try {
                statement.execute("insert into users (avatar,nick,login,password,number) values ('" +
                        request.queryParams("avatar") + "', '" +
                        request.queryParams("nick") + "', '" +
                        request.queryParams("login") + "', '" +
                        request.queryParams("password") + "', '" +
                        request.queryParams("number") + "')");
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "success";
        } else {
            return "error";
        }
    }

    public String createMS(Request request) {
        if (request.queryParams("idincoming").length() >= 1 &&
                request.queryParams("idoutgoing").length() >= 1 &&
                request.queryParams("content").length() >= 4 &&
                request.queryParams("date").length() >= 4 ){
            try {
                statement.execute("insert into message (idincoming,idoutgoing,content,date) values (" +
                        request.queryParams("idincoming") + ", " +
                        request.queryParams("idoutgoing") + ", '" +
                        request.queryParams("content") + "', '" +
                        request.queryParams("date") + "')");
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "success";
        } else {
            return "error";
        }
    }
    public String createFriends(Request request) {
        if (request.queryParams("iduser").length() >= 1 &&
                request.queryParams("idfriend").length() >= 1){
            try {
                statement.execute("insert into friends (iduser,idfriend) values ('" +
                        request.queryParams("iduser") + "', '" +
                        request.queryParams("idfriend") + "')");
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "success";
        } else {
            return "error";
        }
    }
}
