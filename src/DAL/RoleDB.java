package DAL;

import BL.Role;

import java.sql.*;
import java.util.Collection;
import java.util.Vector;

/**
 * Created by Anton on 2016-09-28.
 */
public class RoleDB extends Role {

    private RoleDB(int id, String name){
        super(id, name);
    }

    public static Role getFromDB(int id){

        Connection conn = DBManager.getConnection();
        String query = "SELECT * FROM Role WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                String name = rs.getString("name");
                return new RoleDB(id, name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Collection<Role> getAllFromDB(){
        Vector<Role> roles = new Vector<>();
        Connection con = DBManager.getConnection();

        String query = "SELECT * FROM Role";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                roles.add(new RoleDB(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return roles;
    }
}
