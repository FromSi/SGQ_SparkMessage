package college;

import college.jdbc.JDBCGET;
import college.jdbc.JDBCPOST;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        get("/", (request, response) -> "Hello World");

        post("/user", (request, response) -> new JDBCPOST().createUser(request));
        post("/dialog", (request, response) -> new JDBCPOST().createMS(request));
        post("/friend", (request, response) -> new JDBCPOST().createFriends(request));
        get("/dialog", (request, response) -> new JDBCGET().printMessage(request));
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
