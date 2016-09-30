package BL;

import DAL.OrderItemDB;

import java.util.Collection;

/**
 * Created by chris on 2016-09-30.
 */
public class OrderItem {
    Item item;

    protected OrderItem(Item item) {
        this.item = item;
    }

    public static Collection<Item> getItemsFromOrder(int id){
        return OrderItemDB.getItems(id);
    }
}
