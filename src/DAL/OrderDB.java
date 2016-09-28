package DAL;

import BL.Item;
import BL.Order;
import BL.ShoppingCartItem;
import BL.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by chris on 2016-09-28.
 */
public class OrderDB {
    public static Order getOrder(int id) {
        return null;
    }

    public static Vector<Order> getOrders(User owner){
        return null;
    }

    public static void addOrder(User owner) throws SQLException {
        //TODO: post a new order to database, what to include, list of items?
        Connection con = DBManager.getConnection();
        //TODO: Kanske göra så att status sätts by default till Recived (1) istället.
        String newOrderQuery = "INSERT INTO Order (owner,status) VALUES (?)";
        String itemsToOrderQuery = "INSERT INTO OrderItem (order,item) VALUES (?,?)";
        PreparedStatement orderStmt = null;
        PreparedStatement itemsStmt = null;

        try {
            int auto_id = -1;
            con.setAutoCommit(false);
            orderStmt = con.prepareStatement(newOrderQuery, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1,owner.getId());
            orderStmt.executeQuery();

            ResultSet rs = orderStmt.getGeneratedKeys();
            rs.next();
            auto_id = rs.getInt(1);

            itemsStmt = con.prepareStatement(itemsToOrderQuery);

            for(ShoppingCartItem item : owner.getShoppingCart().getItems()){
                itemsStmt.setInt(1,auto_id);
                itemsStmt.execute();
                con.commit();
            }
            // Vill vi ha ResultSet här?
        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null){
                System.err.println("Transaction is being rolled back!");
                con.rollback();
            }

        }finally {
            if(newOrderQuery != null) orderStmt.close();
            if (itemsToOrderQuery != null) itemsStmt.close();
            con.setAutoCommit(true);

        }

    }

    public static void updateOrder(int id){

    }
}
