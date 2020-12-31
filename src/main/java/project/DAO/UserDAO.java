package project.DAO;

import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.validation.ValidationException;
import project.models.User;

public class UserDAO {

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

    public static User findByName(String userName){
        return User.findFirst("username = ?", userName);
    }
    public static void modifyDisplayName(String userName,String newDisplayName) {
        findByName(userName).set("display_name", newDisplayName).saveIt();
    }
    public static LazyList<User> getAllUsers(){
        return User.findAll();
    }
}
