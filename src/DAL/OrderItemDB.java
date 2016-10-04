package DAL;

import BL.Item;
import BL.OrderItem;
import VO.ItemVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

/**
 * Created by chris on 2016-09-30.
 */
public class OrderItemDB extends OrderItem{
    public OrderItemDB(Item item, int amount) {
        super(item,amount);
    }

    public static Collection<OrderItem> getItems(int id) {
        Vector<OrderItem> items = new Vector<>();
        PreparedStatement stmt = null;

        Connection con = DBManager.getConnection();

        String query = "SELECT item, amount FROM OrderItem WHERE orderId = ?";
        //TODO: Se till att allt sammanhängande tas bort vid en deleteOrder().
        try{
            stmt = con.prepareStatement(query);
            stmt.setInt(1,id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                items.add(new OrderItemDB(ItemDB.get(rs.getInt("item")),rs.getInt("amount")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt != null) try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (con != null) try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return items;
    }
}
