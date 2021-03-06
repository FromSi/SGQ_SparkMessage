package college;

import college.jdbc.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class Main {
    private static List<UsersCBK> usersCBK;
    public static void main(String[] args) throws URISyntaxException {
        port(getHerokuAssignedPort());
        usersCBK = new JDBCBEGIN().addNotifAll();
        get("/", (request, response) -> usersCBK.get(0).getIdUser() + " --- "+usersCBK.get(1).getIdUser());

        post("/user", (request, response) -> {
            String str1 = new JDBCPOST().createUser(request);
            usersCBK = new JDBCBEGIN().addNotif(usersCBK);
            return str1;
        });
        post("/dialog", (request, response) -> {
            for (int i = 0; i < usersCBK.size(); i++) {
                if (usersCBK.get(i).getIdUser().equals(request.queryParams("idoutgoing"))) {
                    usersCBK.get(i).setCheck(true);
                    usersCBK.get(i).setNotif(true);
                    return new JDBCPOST().createMS(request);
                }
            }
            return null;
        });
        post("/friend", (request, response) -> new JDBCPOST().createFriends(request));
        get("/dialog", (request, response) -> new JDBCGET().printMessage(request));
        get("/dialog-check", (request, response) -> {
            for (int i = 0; i < usersCBK.size(); i++) {
                if (usersCBK.get(i).getIdUser().equals(request.queryParams("idoutgoing")))
                    if (usersCBK.get(i).isCheck()) {
                        usersCBK.get(i).setCheck(false);
                        return new JDBCGET().printDialogCheck(request);
                    }
            }
            return null;
        });
        get("/dialog-notif", (request, response) -> {
            for (int i = 0; i < usersCBK.size(); i++) {
                if (usersCBK.get(i).getIdUser().equals(request.queryParams("idoutgoing")))
                    if (usersCBK.get(i).isNotif()) {
                        usersCBK.get(i).setNotif(false);
                        return new JDBCGET().printDialogCheck(request);
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
