package project.DAO;

import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.validation.ValidationException;
import project.models.User;

public class UserDAO {
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
        findByName(userName).set("display_name", newDisplayName).saveIt();
    }

    /**
     * returns all the registered users.
     * @return a list with the registered Users in the database.
     */
    public static LazyList<User> getAllUsers(){
        return User.findAll();
    }

    /**
     * given a user name, it returns the number of games played by that user.
     * @param userName the given user name
     * @return the ammount of games played by this user.
     */
    public static int getTheirGamesPlayed(String userName) {
        return findByName(userName).getGamesPlayed();
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
