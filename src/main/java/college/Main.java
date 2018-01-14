package college;

import college.jdbc.*;

import java.net.URISyntaxException;
import java.util.List;

import static spark.Spark.*;

public class Main {
    private static List<UsersCBK> usersCBK;

    public static void main(String[] args) throws URISyntaxException {
        port(getHerokuAssignedPort());

        usersCBK = new JDBCBEGIN().addNotifAll();

        get("/", (request, response) -> "Hello World");

        post("/user", (request, response) -> new JDBCPOST().createUser(request));
        post("/dialog", (request, response) -> {
            for (int i = 0; i < usersCBK.size(); i++) {
                if (usersCBK.get(1).getIdUser() == Integer.parseInt(request.queryParams("idoutgoing")))
                    usersCBK.get(1).setNotification(true);
            }
            return new JDBCPOST().createMS(request);
        });
        post("/friend", (request, response) -> new JDBCPOST().createFriends(request));
        get("/dialog", (request, response) -> new JDBCGET().printMessage(request));
        get("/notification", (request, response) -> {
            for (int i = 0; i < usersCBK.size(); i++) {
                if (usersCBK.get(1).getIdUser() == Integer.parseInt(request.queryParams("idincoming")))
                    if (usersCBK.get(1).isNotification()) {
                        usersCBK.get(1).setNotification(false);
                        return new JDBCGET().printMessage(request);
                    }
            }
            return null;
        });
        get("/friend", (request, response) -> new JDBCGET().printFriends(request));
        get("/profile", (request, response) -> new JDBCGET().printUser(request));
        get("/login", (request, response) -> new JDBCGET().printLogin(request));
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

}
