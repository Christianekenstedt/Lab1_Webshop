package VO;

import BL.User;
import DAL.UserDB;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by Anton on 2016-09-29.
 */
public class UserVO {
    private int id;
    private String username;
    private RoleVO role;

    private UserVO(User user){
        System.out.println(user.getRole().getName());
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = new RoleVO(user.getRole());
    }

    public static UserVO authenticate(String username, String password){
        User user  = User.authenticate(username, password);
        if(user != null)
            return new UserVO(user);
        else{
            return null;
        }
    }

    public static UserVO getUserByID(int id){
        User user = User.getUser(id);
        if(user != null)
            return new UserVO(user);
        else
            return null;
    }

    public static void addUser(String username, String password, int roleId){
        User.addUser(username, password, roleId);
    }

    public static void deleteUser(int userId){
        //Perform check so that it is actually an admin before we get here
        User.deleteUser(userId);
    }

    public static void updateUser(int id, String username, int roleId){
        User.updateUser(id, username, roleId);
    }

    public static Collection<UserVO> getAllUsers(){
        Vector<UserVO> users = new Vector<>();

        for(User user : User.getAllUsers()){
            users.add(new UserVO(user));
        }
        return users;

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public RoleVO getRole() {
        return role;
    }
}
