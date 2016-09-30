package DAL;

import BL.Item;
import BL.ShoppingCart;
import BL.ShoppingCartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

/**
 * Created by Anton on 2016-09-29.
 */
public class ShoppingCartItemDB extends ShoppingCartItem {
    private ShoppingCartItemDB(int id, Item item, int amount, int cartId){
        super(id, item, amount, cartId);
    }

    public static Collection<ShoppingCartItem> getItemsInCartFromDB(int cartId){
        Vector<ShoppingCartItem> items = new Vector<>();

        Collection<Item> itemsInDB = Item.getAll();

        Connection conn = DBManager.getConnection();

        String query = "SELECT * FROM ShoppingCartItem WHERE  cart = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, cartId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int amount = rs.getInt("amount");
                Item item = getItemById(itemsInDB, rs.getInt("item"));
                items.add(new ShoppingCartItemDB(id, item, amount, cartId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(conn);
        }

        return items;
    }

    /***
     * Helper function to find a specific Item in a Collection of items.
     * @param items The collection of items.
     * @param itemId The id of the item to find.
     * @return The found item, else null.
     */
    private static Item getItemById(Collection<Item> items, int itemId){
        for(Item i : items){
            if(i.getId() == itemId)
                return i;
        }
        return null;
    }
}
