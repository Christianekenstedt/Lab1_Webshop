package DAL;

import BL.Item;
import BL.ItemCategory;

import java.sql.*;
import java.util.Vector;

/**
 * Created by Christian on 2016-09-26.
 */
public class ItemDB extends Item {

    private ItemDB(int id, String name, int amount, ItemCategory category, float price){
        super(id, name, amount, category, price);
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
                float price = rs.getFloat("price");
                return new ItemDB(id, name, amount, cat, price);
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
                float price = rs.getFloat("price");
                items.add(new ItemDB(id, name, amount, cat, price));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(con);
        }

        return items;
    }


    public static void addItemToDB(String name, int amount, ItemCategory category, float price){

        Connection con = DBManager.getConnection();

        if(con==null)
            return;

        String query = "INSERT INTO Item (name, inStock, category, price) VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, amount);
            stmt.setInt(3, category.getId());
            stmt.setFloat(4, price);
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

    public static void updateInDB(int id, String name, int amount, int category, float price){
        Connection con = DBManager.getConnection();

        String query = "UPDATE Item SET name = ?, inStock = ?, category = ?, price = ? " +
                "WHERE id = ?";

        try{
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, amount);
            stmt.setInt(3, category);
            stmt.setFloat(4, price);
            stmt.setInt(5, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(con);
        }
    }
}
