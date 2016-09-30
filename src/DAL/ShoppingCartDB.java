package DAL;

import BL.Item;
import BL.ShoppingCart;
import BL.ShoppingCartItem;
import BL.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

/**
 * Created by Anton on 2016-09-29.
 */
public class ShoppingCartDB extends ShoppingCart {
    private ShoppingCartDB(int id, Collection<ShoppingCartItem> items, User owner){
        super(id, items, owner);
    }

    public static void addCartToDB(User owner){
        Connection conn = DBManager.getConnection();

        String query = "INSERT INTO ShoppingCart (owner) VALUES(?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, owner.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(conn);
        }

    }

    public static ShoppingCartDB getCartByOwner(User owner){
        Connection conn = DBManager.getConnection();

        String query = "SELECT * FROM ShoppingCart WHERE owner = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, owner.getId());
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                ShoppingCartDB cart = new ShoppingCartDB(id, ShoppingCartItemDB.getItemsInCartFromDB(id), owner);
                return cart;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(conn);
        }
        return null;
    }

    public static void addItemToCartInDB(int cartId, int itemId, int quantity){

        Connection conn = DBManager.getConnection();

        String query = "INSERT INTO ShoppingCartItem (cart, item, amount) VALUES(?,?,?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, cartId);
            stmt.setInt(2, itemId);
            stmt.setInt(3, quantity);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(conn);
        }

    }

    public static void removeItemFromCartInDB(int cartId, int itemId){
        Connection conn = DBManager.getConnection();

        String query = "DELETE FROM ShoppingCartItem WHERE cart = ? AND item = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, cartId);
            stmt.setInt(2, itemId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.returnConnection(conn);
        }
    }
}
