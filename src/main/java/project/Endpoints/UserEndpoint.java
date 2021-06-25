package project.Endpoints;




import io.github.manusant.ss.SparkSwagger;
import io.github.manusant.ss.rest.Endpoint;
//import com.beerboy.ss.rest.Endpoint;
//import com.beerboy.ss.SparkSwagger;
import com.google.gson.Gson;
import org.javalite.activejdbc.LazyList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.DAO.UserDAO;
import project.models.User;


import java.util.Map;

import static io.github.manusant.ss.descriptor.EndpointDescriptor.endpointPath;
import static io.github.manusant.ss.descriptor.MethodDescriptor.path;
//import static com.beerboy.ss.descriptor.EndpointDescriptor.endpointPath;
//import static com.beerboy.ss.descriptor.MethodDescriptor.path;


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
                            LazyList<User> users = UserDAO.getAllUsers().limit(limit);
                            return users.toJson(true);
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

                            return user.toJson(true);
                            //return true;
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
                            UserDAO.modifyDisplayName(bodyParams.get("username").toString(),
                                    bodyParams.get("newDisplayName").toString());

                            return true;
                        }
                )
                .get(
                        path("/profile")
                                .withDescription("Loads all profile info of a player.")
                                .withQueryParam()
                                .withName("userName")
                                .withObject(String.class)
                                .withDescription("user name").and(),
                        (req, res) -> {
                            String userName = (req.queryParams("userName"));
                            return UserDAO.findByName(userName).toJson(true);
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