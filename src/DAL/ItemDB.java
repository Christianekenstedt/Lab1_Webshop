package DAL;

import BL.Item;
import BL.ItemCategory;

import java.sql.*;
import java.util.Vector;

/**
 * Created by Christian on 2016-09-26.
 */
public class ItemDB extends Item {

    private ItemDB(int id, String name, int amount, ItemCategory category){
        super(id, name, amount, category);
    }

    public static Item getFromDB(int id){
        Connection con = DBManager.getConnection();
        String query = "SELECT * FROM Item WHERE id = ?";

        try{
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String name = rs.getString("name");
                int amount = rs.getInt("inStock");
                ItemCategory cat = ItemCategory.getCategoryByID(rs.getInt("category"));
                return new ItemDB(id, name, amount, cat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(con);
        }

        return null;
    }

    public static Vector<Item> getAllFromDB(){
        Vector<Item> items = new Vector();

        Connection con = DBManager.getConnection();

        String query = "SELECT * FROM Item";

        try{
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                int amount = rs.getInt("inStock");
                String name = rs.getString("name");
                ItemCategory cat = ItemCategory.getCategoryByID(rs.getInt("category"));
                items.add(new ItemDB(id, name, amount, cat));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(con);
        }

        return items;
    }


    public static void addItemToDB(String name, int amount, ItemCategory category){

        Connection con = DBManager.getConnection();

        if(con==null)
            return;

        String query = "INSERT INTO Item (name, inStock, category) VALUES(?, ?, ?)";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, amount);
            stmt.setInt(3, category.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(con);
        }
    }

    public static void deleteFromDB(int id){
        Connection con = DBManager.getConnection();

        String query = "DELETE FROM Item WHERE id = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Item removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateInDB(int id, String name, int amount, int category){
        Connection con = DBManager.getConnection();

        String query = "UPDATE Item SET name = ?, inStock = ?, category = ? " +
                "WHERE id = ?";

        try{
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, amount);
            stmt.setInt(3, category);
            stmt.setInt(4, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(con);
        }
    }
}
