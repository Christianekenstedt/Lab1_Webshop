package VO;

import BL.Order;
import BL.User;

import java.util.Vector;

/**
 * Created by chris on 2016-09-28.
 */
public class OrderVO {
    private int id;
    private User owner;
    private int status;

    public OrderVO(Order order) {
        this.id = order.getId();
        this.owner = order.getOwner();
        this.status = order.getStatus();
    }

    public static OrderVO viewOrder(int id){return new OrderVO(Order.getOrder(id));}

    public static void postOrder(int userId){
        Order.addOrder(User.getUser(userId));
    }

    public static Vector<OrderVO> viewOrders(int id){
        Vector<OrderVO> orders = new Vector<>();
        for(Order o: Order.getOrders(id)){
            orders.add(new OrderVO(o));
        }
        return orders;
    }

    public static void updateOrder(int id){
        Order.updateOrder(id,User.getUser(Order.getOrder(id).getOwner().getId()),3);
    }

    public static Vector<OrderVO> getAllOrders(){
        Vector<OrderVO> orders = new Vector<>();
        for(Order o: Order.getAllOrders()){
            orders.add(new OrderVO(o));
        }
        return orders;
    }

    public int getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public int getStatus() {
        return status;
    }
}
