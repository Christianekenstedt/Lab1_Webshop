package DAL;

import BL.Role;
import BL.ShoppingCart;
import BL.User;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Collection;
import java.util.Vector;

/**
 * Created by Anton on 2016-09-28.
 */
public class UserDB extends User {
    private UserDB(int id, String name, Role role){
        super(id, name, role);
    }

    public static UserDB authenticateInDB(String username, String password){
        Connection conn = DBManager.getConnection();

        String query = "SELECT id, username, role FROM [User]" +
                " WHERE username = ? AND password = ?";

        try {

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                Role role = Role.getRole(rs.getInt("role"));

                return new UserDB(id, username, role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(conn);
        }

        return null;
    }

    public static UserDB getUserFromDB(int id){
        Connection conn = DBManager.getConnection();

        String query = "SELECT id, username, role FROM [User] WHERE" +
                " id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String username = rs.getString("username");
                Role role = Role.getRole(rs.getInt("role"));
                return new UserDB(id, username, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(conn);
        }
        return null;
    }

    public static Collection<User> getAllUsersFromDB(){
        Vector<User> users = new Vector<>();

        Connection conn = DBManager.getConnection();

        String query = "SELECT id, username, role FROM [User]";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                int roleId = rs.getInt("role");
                Role role = Role.getRole(roleId);
                users.add(new UserDB(id, username, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBManager.returnConnection(conn);
        }

        return users;
    }

    public static void addUserToDB(String username, String password, int roleId){
        Connection conn = DBManager.getConnection();

        String query = "INSERT INTO [User] (username, password, role) VALUES(?,?,?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, roleId);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBManager.returnConnection(conn);
        }

    }

    public static void updateUserInDB(int id, String username, String oldPassword, String password, Role role){
        Connection conn = DBManager.getConnection();

        String query = "UPDATE [User] SET username = ?, password = ?, role = ? WHERE" +
                " id = ? AND password = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, role.getId());
            stmt.setInt(4, id);
            stmt.setString(5, oldPassword);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBManager.returnConnection(conn);
        }
    }

    public static void updateUserInDB(int id, String username, int roleId){
        Connection conn = DBManager.getConnection();

        String query = "UPDATE [User] SET username = ?, role = ? WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setInt(2, roleId);
            stmt.setInt(3, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(conn);
        }
    }

    public static void deleteUserFromDB(int id){
        Connection conn = DBManager.getConnection();

        String query = "DELETE FROM [User] WHERE" +
                " id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("User removed.");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(conn);
        }
    }
}














