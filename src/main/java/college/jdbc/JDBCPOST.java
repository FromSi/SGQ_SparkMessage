package college.jdbc;

import spark.Request;

import java.sql.*;

public class JDBCPOST {
    //Ссылка, логин и пароль для входа в БД
    private final String url = "jdbc:mysql://dbmassage.cvt4fjl91axe.us-west-2.rds.amazonaws.com:3306/dbmassage?autoReconnect=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8";
    private final String login = "fromsi";
    private final String password = "vlad43284328";
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
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "success";
        } else {
            return "error";
        }
    }
}
