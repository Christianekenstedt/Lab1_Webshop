package DAL;

import BL.Item;

import java.sql.*;

/**
 * Created by Christian on 2016-09-26.
 */
public class ItemDB extends Item {

    private ItemDB(int id, String name){
        super(id,name);
    }

    public static Item getItem(int id){
        //TODO: get item from db.
        return new ItemDB(id,"Skjorta");
    }

    /***
     * TODO: parameters or a full "item" object
     * @param name
     */
    public static void newItem(String name, int amount, int category){
        //TODO: Save item to db.

        Connection con = DBManager.getConnection();

        String query = "INSERT INTO Item (name, inStock, category) VALUES(?, ?, ?)";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, amount);
            stmt.setInt(3, -1);
            //// TODO: 2016-09-28 : execute 

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
