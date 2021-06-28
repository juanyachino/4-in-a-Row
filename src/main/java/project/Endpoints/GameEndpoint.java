package project.Endpoints;

import io.github.manusant.ss.SparkSwagger;
import io.github.manusant.ss.rest.Endpoint;
//import com.beerboy.ss.rest.Endpoint;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import com.beerboy.ss.SparkSwagger;
import project.DAO.UserDAO;
import project.logic.Game;

import java.util.Map;
import static io.github.manusant.ss.descriptor.EndpointDescriptor.endpointPath;
import static io.github.manusant.ss.descriptor.MethodDescriptor.path;
import static java.lang.Integer.parseInt;
//import static com.beerboy.ss.descriptor.EndpointDescriptor.endpointPath;
//import static com.beerboy.ss.descriptor.MethodDescriptor.path;

/**
 *
 *
 *
 */
public class GameEndpoint implements Endpoint {

    /**
     *
     */
    private static final String NAME_SPACE = "/game";

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
                        endpointPath(NAME_SPACE).withDescription("All Game utilities"),
                        (q, a) -> LOGGER.info("Received request for Game Rest API"))
                .get(
                        path("/board")
                                .withDescription("gets the updated game board")
                                .withQueryParam()
                                .withName("limit")
                                .withObject(Integer.class)
                                .withDescription("The limit of records to be pulled. DEFAULT 15").and()
                                .withResponseType(Game.class),
                        (req, res) -> {
                            int limit = req.queryParams("limit") != null
                                    ? parseInt(req.queryParams("limit"))
                                    : LIMIT;
                            return ("{}");
                        }
                )
                .post(
                        path("/insert")
                                .withDescription("Commits the piece insertion")
                                .withRequestType(Game.class)
                                .withResponseType(Game.class),
                        (req, res) -> {
                            Map<String, Object> bodyParams = new Gson().fromJson(req.body(), Map.class);
                            Game.insertPiece(parseInt(bodyParams.get("column").toString()),
                                   Game.getActualPlayer());

                            return true;
                        }
                )
                .post(
                        path("/start")
                                .withDescription("starts the game with 2 players")
                                .withRequestType(Game.class)
                                .withResponseType(Game.class),
                        (req, res) -> {
                            Map<String, Object> bodyParams = new Gson().fromJson(
                                    req.body(), Map.class);

                            //Controller.getInstance().getGameLogic().getModeAttack().moveArmies(idPlayer,
                              //      territoryAId, territoryBId, armiesCount);
                            return true;
                        }
                );
    }
}