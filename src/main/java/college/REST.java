package college;
import static spark.Spark.*;
import college.jdbc.JDBCGET;
import college.jdbc.JDBCPOST;

import java.util.List;

public class REST {

    public void start(List<UsersCBK> usersCBK){
        port(getHerokuAssignedPort());
        get("/", (request, response) -> usersCBK.get(0).getIdUser() + " --- "+usersCBK.get(1).getIdUser());

        post("/user", (request, response) -> new JDBCPOST().createUser(request));
        post("/dialog", (request, response) -> {
            for (int i = 0; i < usersCBK.size(); i++) {
                if (usersCBK.get(i).getIdUser().equals(request.queryParams("idoutgoing"))) {
                    usersCBK.get(i).setNotification(true);
                    return new JDBCPOST().createMS(request);
                }
            }
            return "null";
        });
        post("/friend", (request, response) -> new JDBCPOST().createFriends(request));
        get("/dialog", (request, response) -> new JDBCGET().printMessage(request));
        get("/notification", (request, response) -> {
            for (int i = 0; i < usersCBK.size(); i++) {
                if (usersCBK.get(i).getIdUser().equals(request.queryParams("idincoming")))
                    if (usersCBK.get(i).isNotification()) {
                        usersCBK.get(i).setNotification(false);
                        return new JDBCGET().printMessage(request);
                    }
            }
            return "null";
        });
        get("/friend", (request, response) -> new JDBCGET().printFriends(request));
        get("/profile", (request, response) -> new JDBCGET().printUser(request));
        get("/login", (request, response) -> new JDBCGET().printLogin(request));
    }
    int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}
