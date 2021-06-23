package project.models;

//
// Database structure of users
//
// +------------+-------------+------+-----+-----------------+
// | Field      | Type        | Null | Key | Extra           |
// +------------+-------------+------+-----+-----------------+
// | id          | int         | NO   | PRI | auto_increment |
// | username    | varchar     | NO   |     |                |
// | password    | varchar     | NO   |     |                |
// | email       | varchar     | NO   |     |                |
// | display_name| varchar     | NO   |     |                |
// | games_played| int         | NO   |     |                |
// | games_won   | int         | NO   |     |                |
// | games_lost  | int         | NO   |     |                |
// +------------+-------------+------+-----+-----------------+
//
//
import org.javalite.activejdbc.Model;

public class User extends Model{

    /**
     * wrapper getters and setters:
     * these are not provided by ActiveJDBC,
     * This will provide a safety net to those wishing some compiler static checking.
     * Otherwise , you can just use ActiveJDBC dynamic getters and setters:
     * i.e : userInstance.get("username");
     * more info here https://javalite.io/setters_and_getters
     *
     * This applies to every method on this class.
     */
    public int getGamesPlayed() {
        return getInteger("games_played");
    }

    public int getGamesWon() {
        return getInteger("games_won");
    }

    public int getGamesLost() {
        return getInteger("games_lost");
    }

    public String getDisplayName() {
        return getString("display_name");
    }
    public String getUsername() {
        return getString("username");
    }
    public String getPassword() {
        return getString("password");
    }
    public String getEmail() {
        return getString("email");
    }

    public void setGamesPlayed(int gamesPlayed) {
        set("games_played", gamesPlayed);
    }
    public void setGamesLost(int gamesPlayed) {
        set("games_lost", gamesPlayed);
    }
    public void setGamesWon(int gamesPlayed) {
        set("games_won", gamesPlayed);
    }
    public void setPassword(String newPassword) {
        set("password", newPassword);
    }
}
