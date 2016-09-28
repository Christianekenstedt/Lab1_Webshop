package BL;

import DAL.OrderDB;

import java.util.Vector;

/**
 * Created by chris on 2016-09-28.
 */
public class Order {
    private int id;
    private User owner;
    //TODO: Kanske ha status som enum?
    private String status;

    protected Order(int id, User owner, String status){
        this.id = id;
        this.owner = owner;
        this.status = status;
    }

    public static Order getOrder(int id){
        return OrderDB.getOrder(id);
    }

    public static Vector<Order> getOrders(User owner){
        return OrderDB.getOrders(owner);
    }

    public void postOrder(User owner){
        OrderDB.addOrder(owner);
    }
    public int getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public String getStatus() {
        return status;
    }
}
