package VO;

import BL.User;

/**
 * Created by Anton on 2016-09-29.
 */
public class UserVO {
    private int id;
    private String username;
    private RoleVO role;

    private UserVO(User user){
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
