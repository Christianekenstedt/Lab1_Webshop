package VO;

import BL.Order;
import BL.User;

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

    public static void postOrder(){
        Order.addOrder(new User());
    }
}
