package college.jdbc;

import com.google.gson.Gson;
import spark.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class JDBCGET {
    //Ссылка, логин и пароль для входа в БД
    private final String url = "jdbc:mysql://dbmassage.cvt4fjl91axe.us-west-2.rds.amazonaws.com:3306/dbmassage?autoReconnect=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8";
    private final String login = "fromsi";
    private final String password = "vlad43284328";
    //Переменные для работы с БД в Java
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private HashMap<String, String> hashMap;
    private ArrayList<HashMap<String, String>> arrayList;

    //Конструктор
    public JDBCGET() {
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

    public String printFriends(Request request) {
        try {
            resultSet = statement.executeQuery("select idfriend from friends where friends.iduser="+request.queryParams("iduser"));
            arrayList = new ArrayList<>();
            while (resultSet.next()) {
                hashMap = new HashMap<>();
                hashMap.put("idfriend", resultSet.getString("idfriend"));
                arrayList.add(hashMap);
            }
            connection.close();
            //Возвращаем ответ
            return new Gson().toJson(arrayList);
        } catch (Exception e) {
            //Возвращаем ответ
            return "Error SQL select 1";
        }
    }

    public String printMessage(Request request) {
        try {
            resultSet = statement.executeQuery("select idincoming, idoutgoing, content, message.date from message where idincoming="+request.queryParams("idincoming")+" AND idoutgoing="+request.queryParams("idoutgoing"));
            arrayList = new ArrayList<>();
            while (resultSet.next()) {
                hashMap = new HashMap<>();
                hashMap.put("idincoming", resultSet.getString("idincoming"));
                hashMap.put("idoutgoing", resultSet.getString("idoutgoing"));
                hashMap.put("content", resultSet.getString("content"));
                hashMap.put("date", resultSet.getString("date"));
                arrayList.add(hashMap);
            }
            statement.close();
//            connection.close();
            //Возвращаем ответ
            return new Gson().toJson(arrayList);
        } catch (Exception e) {
            //Возвращаем ответ
            return "Error SQL select 1";
        }
    }

    public String printUser(Request request) {
        try {
            resultSet = statement.executeQuery("select avatar,nick,login,password,number from users where iduser="+request.queryParams("iduser"));
            arrayList = new ArrayList<>();
            while (resultSet.next()) {
                hashMap = new HashMap<>();
                hashMap.put("avatar", resultSet.getString("avatar"));
                hashMap.put("nick", resultSet.getString("nick"));
                hashMap.put("number", resultSet.getString("number"));
                arrayList.add(hashMap);
            }
            connection.close();
            //Возвращаем ответ
            return new Gson().toJson(arrayList);
        } catch (Exception e) {
            //Возвращаем ответ
            return "Error SQL select 1";
        }
    }
    public String printLogin(Request request) {
        try {
            resultSet = statement.executeQuery("select users.iduser from users where users.login='"+request.queryParams("login")+"' AND users.password='"+request.queryParams("password")+"'");
            while (resultSet.next()) {
                hashMap = new HashMap<>();
                hashMap.put("iduser", resultSet.getString("iduser"));
            }
            connection.close();
            //Возвращаем ответ
            return new Gson().toJson(hashMap);
        } catch (Exception e) {
            //Возвращаем ответ
            return "Error SQL select 1";
        }
    }
}