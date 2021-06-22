package project.Endpoints;





import com.beerboy.ss.rest.Endpoint;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.DAO.UserDAO;
import project.models.User;
import com.beerboy.ss.SparkSwagger;

import java.util.Map;

import static com.beerboy.ss.descriptor.EndpointDescriptor.endpointPath;
import static com.beerboy.ss.descriptor.MethodDescriptor.path;


/**
 *
 *
 *
 */
public class UserEndpoint implements Endpoint {

    /**
     *
     */
    private static final String NAME_SPACE = "/user";

    /**
     *
     */
    private static final int LIMIT = 15;

    /**
     *
     */
    static final Logger LOGGER = LoggerFactory.getLogger(GameEndpoint.class);

    /**
     *
     */
    @Override
    public void bind(SparkSwagger restApi) {
        restApi
                .endpoint(
                        endpointPath(NAME_SPACE).withDescription("All User utilities"),
                        (q, a) -> LOGGER.info("Received request for Game Rest API"))
                .get(
                        path("/show_users")
                                .withDescription("Get all users")
                                .withQueryParam()
                                .withName("limit")
                                .withObject(Integer.class)
                                .withDescription("The limit of records to be pulled. DEFAULT 15").and()
                                .withResponseType(User.class),
                        (req, res) -> {
                            int limit = req.queryParams("limit") != null
                                    ? Integer.parseInt(req.queryParams("limit"))
                                    : LIMIT;
                            return ("a list with users ");
                        }
                )
                .post(
                        path("/create")
                                .withDescription("creates the user")
                                .withRequestType(User.class)
                                .withResponseType(User.class),
                        (req, res) -> {
                            Map<String, Object> bodyParams = new Gson().fromJson(req.body(), Map.class);
                            User user = UserDAO.createUser(bodyParams.get("username").toString(),
                                    bodyParams.get("password").toString(),
                                    bodyParams.get("email").toString(),
                                    bodyParams.get("displayName").toString());

                            //return user.toJson(true);
                            return true;
                        }
                )
                .post(
                        path("/updateName")
                                .withDescription("changes the display name")
                                .withRequestType(User.class)
                                .withResponseType(User.class),
                        (req, res) -> {
                            Map<String, Object> bodyParams = new Gson().fromJson(
                                    req.body(), Map.class);


                            return true;
                        }
                )
                .get(
                        path("/changeName")
                                .withDescription("name change (useless?)")
                                .withQueryParam()
                                .withName("limit")
                                .withObject(Integer.class)
                                .withDescription("The limit of records. DEFAULT 15").and()
                                .withResponseType(User.class),
                        (req, res) -> {
                            int limit = req.queryParams("limit") != null
                                    ? Integer.parseInt(req.queryParams("limit"))
                                    : LIMIT;
                            return ("{}");
                        }
                );
    }
}