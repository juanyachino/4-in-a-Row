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
// +------------+-------------+------+-----+-----------------+
//
//
import org.javalite.activejdbc.Model;

public class User extends Model{

    private String username;
    private String password;
    private String email;
    private String displayName;

    public String getDisplayName() {
        return displayName;
    }
}
