package DAL;

import BL.ItemCategory;

import java.sql.*;
import java.util.Collection;
import java.util.Vector;

/**
 * Created by Anton on 2016-09-30.
 */
public class ItemCategoryDB extends ItemCategory{

    private ItemCategoryDB(int id, int parent, String name){
        super(id, parent, name);
    }

    public static ItemCategory getCategoryByIdFromDB(int categoryId){
        Connection conn = DBManager.getConnection();

        String query = "SELECT * FROM ItemCategory WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int parentId = rs.getInt("parentCategory");
                String name = rs.getString("name");
                return new ItemCategoryDB(categoryId, parentId, name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(conn);
        }

        return null;
    }

    public static Collection<ItemCategory> getAllCategoriesFromDB(){
        Vector<ItemCategory> categories = new Vector<>();
        Connection conn = DBManager.getConnection();

        String query = "SELECT * FROM ItemCategory";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                int parentId = rs.getInt("parentCategory");
                String name = rs.getString("name");
                categories.add(new ItemCategoryDB(id, parentId, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(conn);
        }
        return categories;
    }


    public static void addItemCategoryToDB(String name, int parentId){
        Connection conn = DBManager.getConnection();

        String query = "INSERT INTO ItemCategory (name, parentCategory) VALUES (?,?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, parentId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(conn);
        }
    }

    public static void removeItemCategoryFromDB(int id){
        Connection conn = DBManager.getConnection();

        String query = "DELETE FROM ItemCategory WHERE id=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(conn);
        }
    }
}
