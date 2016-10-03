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

    public Role getRole(){
        return this.role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public static User authenticate(String username, String password){
        User user = UserDB.authenticateInDB(username, password);
        return user;
    }

    public static User getUser(int id){
        return UserDB.getUserFromDB(id);
    }

    public static Collection<User> getAllUsers(){
        return UserDB.getAllUsersFromDB();
    }

    public static void addUser(String username, String password, int roleId){
        UserDB.addUserToDB(username, password, roleId);
    }

    public static void updateUser(int id, String username, String oldPassword, String newPassword, Role role){
        UserDB.updateUserInDB(id, username, oldPassword, newPassword, role);
    }

    public static void updateUser(int id, String username, int roleId){
        UserDB.updateUserInDB(id, username, roleId);
    }

    public static void deleteUser(int id){
        UserDB.deleteUserFromDB(id);
    }
}
