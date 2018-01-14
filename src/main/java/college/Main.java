package college;

import college.jdbc.*;

import java.net.URISyntaxException;
import java.util.List;

import static spark.Spark.*;

public class Main {
    private static List<UsersCBK> usersCBK;
    public static void main(String[] args) throws URISyntaxException {


        usersCBK = new JDBCBEGIN().addNotifAll();
        new REST().start(usersCBK);
    }

}
