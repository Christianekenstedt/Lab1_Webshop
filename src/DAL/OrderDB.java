package DAL;

import BL.Order;
import BL.ShoppingCartItem;
import BL.User;

import java.sql.*;
import java.util.Vector;

/**
 * Created by chris on 2016-09-28.
 */
public class OrderDB extends Order{

    public OrderDB(int id, User owner, int status){
        super(id,owner,status);
    }
    public static Order getOrderFromDB(int id) {
        return null;
    }

    public static Vector<Order> getOrdersFromDB(User owner){
        return null;
    }

    public static void addOrderToDB(User owner){
        PreparedStatement orderStmt = null;
        PreparedStatement itemsStmt = null;

        Connection con = DBManager.getConnection();
        String newOrderQuery = "INSERT INTO Order (owner,status) VALUES (?)";
        String itemsToOrderQuery = "INSERT INTO OrderItem (order,item) VALUES (?,?)";


        try {
            int auto_id = -1;
            con.setAutoCommit(false);
            orderStmt = con.prepareStatement(newOrderQuery, Statement.RETURN_GENERATED_KEYS);
            //orderStmt.setInt(1,owner.getId());
            orderStmt.setInt(1,3);
            orderStmt.executeQuery();

            ResultSet rs = orderStmt.getGeneratedKeys();
            rs.next();
            auto_id = rs.getInt(1);

            itemsStmt = con.prepareStatement(itemsToOrderQuery);

            /*for(ShoppingCartItem item : owner.getShoppingCart().getItems()){
                itemsStmt.setInt(1,auto_id);
                itemsStmt.setInt(2,5);
                itemsStmt.execute();
            }*/

            for(int i = 0; i < 5; i++){
                itemsStmt.setInt(1,auto_id);
                itemsStmt.setInt(2,5);
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
            try{
                if(newOrderQuery != null) orderStmt.close();
                if (itemsToOrderQuery != null) itemsStmt.close();
                con.setAutoCommit(true);
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }

        }

    }

    public static void updateOrder(int id){

    }
}
