package project;

//import com.beerboy.ss.SparkSwagger;
import io.github.manusant.ss.SparkSwagger;
import org.javalite.activejdbc.Base;
import project.Endpoints.GameEndpoint;
import project.Endpoints.UserEndpoint;
import spark.Service;


import java.io.IOException;
import java.util.Arrays;

import static spark.Spark.*;

public class App {
    /**
     * Number of port.
     */
    private static final int PORT = 55555;

    /**
     * Spark Service.
     */
    private static Service spark = Service.ignite().port(PORT);

    public static void main(String[] args) throws IOException {
        get("/hello", (req, res) -> "Hello World");
        SparkSwagger.of(spark, "src/main/resources/" + SparkSwagger.CONF_FILE_NAME)
                .before(
                        (request, response) -> {
                            Base.open();
                        })
                .after((request, response) -> {
                            Base.close();
                })
                .endpoints(() -> Arrays.asList(
                        new UserEndpoint(),
                        new GameEndpoint()))
                .generateDoc();
    }
}
