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
    private Game actualGame;

    /**
     *
     */
    @Override
    public void bind(SparkSwagger restApi) {
        restApi
                .endpoint(
                        endpointPath(NAME_SPACE).withDescription("All Game utilities"),
                        (q, a) -> LOGGER.info("Received request for Game Rest API"))
                .post(
                        path("/start_game")
                                .withDescription("Starts the game with 2 players")
                                .withRequestType(Game.class)
                                .withResponseType(Game.class),
                        (req, res) -> {
                            Map<String, Object> bodyParams = new Gson().fromJson(req.body(), Map.class);
                            actualGame = new Game(bodyParams.get("player1").toString(),
                                    bodyParams.get("player2").toString());

                            return true;
                        }
                )
                .get(
                        path("/board")
                                .withDescription("gets the updated game board and player's turn")
                                .withQueryParam()
                                .withName("limit")
                                .withObject(Integer.class)
                                .withDescription("The limit of records to be pulled. DEFAULT 15").and()
                                .withResponseType(Game.class),
                        (req, res) -> {
                            int limit = req.queryParams("limit") != null
                                    ? parseInt(req.queryParams("limit"))
                                    : LIMIT;
                            if (actualGame == null)  return "there is no game!";

                            return (actualGame.createGameJSON());
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
                );
    }
}