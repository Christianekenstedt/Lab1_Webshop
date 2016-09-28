package BL;

import DAL.UserDB;

import java.util.Collection;

/**
 * Created by chris on 2016-09-28.
 */
public class User {
    private int id;
    private String username;
    private Role role;

    protected User(int id, String username, Role role){
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public static void authenticate(String username, String password){
        User user = UserDB.authenticateInDB(username, password);
        if(user != null){
            // success
        }else{
            // user does not exist
        }
    }

    public static User getUser(int id){
        return UserDB.getUserFromDB(id);
    }

    public static Collection<User> getAllUsers(){
        return UserDB.getAllUsersFromDB();
    }

    public static void addUser(String username, String password, Role role){
        UserDB.addUserToDB(username, password, role);
    }

    public static void updateUser(int id, String username, String oldPassword, String newPassword, Role role){
        UserDB.updateUserInDB(id, username, oldPassword, newPassword, role);
    }

    public static void deleteUser(int id){
        UserDB.deleteUserFromDB(id);
    }
}
