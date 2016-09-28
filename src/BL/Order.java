package BL;

import DAL.OrderDB;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by chris on 2016-09-28.
 */
public class Order {
    private int id;
    private User owner;
    //TODO: Kanske ha status som enum?
    private int status;

    protected Order(int id, User owner, int status){
        this.id = id;
        this.owner = owner;
        this.status = status;
    }

    public static Order getOrder(int id){
        return OrderDB.getOrderFromDB(id);
    }

    public static Collection<Order> getOrders(User owner){
        return OrderDB.getOrdersFromDB(owner);
    }

    public static void addOrder(User owner){
        OrderDB.addOrderToDB(owner);
    }

    public static void deleteOrder(int id){
        OrderDB.deleteOrder(id);
    }

    public static void updateOrder(int id, User owner, int status ){
        OrderDB.updateOrder(id,owner,status);
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
