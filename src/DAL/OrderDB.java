package DAL;

import BL.Order;
import BL.ShoppingCartItem;
import BL.User;

import java.sql.*;
import java.util.Vector;

/**
 * Created by Christian Ekenstedt on 2016-09-28.
 */
public class OrderDB extends Order{

    public OrderDB(int id, User owner, int status){
        super(id,owner,status);
    }
    public static Order getOrderFromDB(int id) {
        PreparedStatement stmt = null;
        PreparedStatement userStmt = null;

        Connection con = DBManager.getConnection();

        String query = "SELECT * FROM ShopOrder WHERE id = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1,id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                //TODO: Catch the user.
                return new OrderDB(rs.getInt("id"),User.getUser(rs.getInt("owner")),rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            if (stmt!=null) try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBManager.returnConnection(con);
        }
        return null;
    }

    public static Vector<Order> getAll(){
        PreparedStatement stmt = null;
        Vector<Order> orders = new Vector<>();
        Connection con = DBManager.getConnection();

        String query = "SELECT * FROM ShopOrder";

        try{
            stmt = con.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                orders.add(new OrderDB(rs.getInt("id"),UserDB.getUser(rs.getInt("owner")),rs.getInt("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt != null) try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(con != null) try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        return orders;
    }

    public static Vector<Order> getOrdersFromDB(User owner){
        PreparedStatement stmt = null;
        Vector<Order> orders = new Vector<>();
        Connection con = DBManager.getConnection();

        String query = "SELECT * FROM ShopOrder WHERE owner = ?";

        try{
            stmt = con.prepareStatement(query);
            stmt.setInt(1,owner.getId());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                //TODO: Kanske verifiera användaren och inte anta att det är den användaren som skickades in.
                orders.add(new OrderDB(rs.getInt("id"),owner,rs.getInt("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt != null) try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            DBManager.returnConnection(con);

        }
        return orders;
    }

    public static void addOrderToDB(User owner){
        PreparedStatement orderStmt = null;
        PreparedStatement itemsStmt = null;

        Connection con = DBManager.getConnection();
        String newOrderQuery = "INSERT INTO ShopOrder (owner) VALUES (?)";
        String itemsToOrderQuery = "INSERT INTO OrderItem (orderId,item) VALUES (?,?)";


        try {
            int auto_id = -1;
            con.setAutoCommit(false);
            orderStmt = con.prepareStatement(newOrderQuery, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1,owner.getId());
            orderStmt.execute();

            ResultSet rs = orderStmt.getGeneratedKeys();
            rs.next();
            auto_id = rs.getInt(1);

            itemsStmt = con.prepareStatement(itemsToOrderQuery);

            for(ShoppingCartItem item : ShoppingCartDB.getCartByOwner(owner).getItems()){
                itemsStmt.setInt(1,auto_id);
                itemsStmt.setInt(2,item.getItem().getId());
                itemsStmt.execute();
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                if (con != null){
                    System.err.println("Transaction is being rolled back!");
                    con.rollback();
                }
            }catch(SQLException ex){
                System.err.println(ex.toString());
            }

        }finally {
            if(newOrderQuery != null) try {
                orderStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (itemsToOrderQuery != null) try {
                itemsStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (con!=null) try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public static void deleteOrder(int id){
        PreparedStatement stmt = null;

        Connection con = DBManager.getConnection();

        String query = "DELETE FROM ShopOrder WHERE id=?";
        //TODO: Se till att allt sammanhängande tas bort vid en deleteOrder().
        try{
            stmt = con.prepareStatement(query);
            stmt.setInt(1,id);

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt != null) try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBManager.returnConnection(con);
        }
    }

    public static void updateOrder(int id, User owner, int status){
        PreparedStatement stmt = null;

        Connection con = DBManager.getConnection();

        String query = "UPDATE ShopOrder set owner = ?, status = ? WHERE id=?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1,owner.getId());
            stmt.setInt(2,status);
            stmt.setInt(3,id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt != null) try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBManager.returnConnection(con);
        }
    }
}
