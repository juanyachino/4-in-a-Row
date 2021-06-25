package project.DAO;

import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.validation.ValidationException;
import project.models.User;

public class UserDAO {
    static int DEFAULT_VALUE = 0;
    /**
     * Creates a user in the database
     * @param userName the user name
     * @param password the user password
     * @param email the user email
     * @param displayName the user preferred display name, can be changed.
     * @return the newly created User object.
     */
    public static User createUser(String userName, String password, String email, String displayName) {
        User user = null;
        try {
            user = new User();
            user.set("username", userName);
            user.set("password", password);
            user.set("email", email);
            user.set("display_name", displayName);
            user.set("games_played", DEFAULT_VALUE);
            user.set("games_lost", DEFAULT_VALUE);
            user.set("games_won", DEFAULT_VALUE);
            user.saveIt();
        } catch (ValidationException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return user;
    }

    /**
     * given a user name , finds the User object on the database.
     * @param userName the user's name
     * @return the User object from the database.
     */
    public static User findByName(String userName){
        return User.findFirst("username = ?", userName);
    }

    /**
     * Given a user name, and a new display name, changes the display name for that user.
     * @param userName the user name
     * @param newDisplayName the new display name.
     */
    public static void modifyDisplayName(String userName,String newDisplayName) {
        findByName(userName).setDisplayName(newDisplayName);
    }

    /**
     * given a username, a password, and the new password, it changes this user's password .
     * @param userName
     * @param oldPassword
     * @param newPassword
     */
    public static void modifyPassword(String userName, String oldPassword, String newPassword) throws Exception {
        User user = User.findFirst("username = ?", userName);
        if (user.getPassword().equals(oldPassword)){
            user.setPassword(newPassword);
        } else {
            throw new Exception("password is wrong!");
        }
    }
    /**
     * returns all the registered users.
     * @return a list with the registered Users in the database.
     */
    public static LazyList<User> getAllUsers(){
        return User.findAll();
    }

    /**
     * given a username and a boolean indicating a victory or defeat
     *  it increases their victory/defeat count and matches played count by 1
     * @param userName the given username
     */
    public static void addAMatchPlayedCount(String userName,Boolean victory) {
        User user = User.findFirst("username = ?", userName);
        user.setGamesPlayed(user.getGamesPlayed() + 1);
        if (victory){
            user.setGamesWon(user.getGamesWon() + 1);
        } else {
            user.setGamesLost(user.getGamesLost() + 1);
        }
    }

    /**
     * given a user name, it calculates and return the user's winrate.
     * @param userName the given user name.
     * @return the user's winrate.
     */
    public static double calculatePlayerWinrate(String userName) {
        User currentUser = findByName(userName);
        int gamesPlayed = currentUser.getGamesPlayed();
        int wonGames = currentUser.getGamesWon();
        if (gamesPlayed == 0 ) return 0;
        return wonGames / gamesPlayed;
    }
}
