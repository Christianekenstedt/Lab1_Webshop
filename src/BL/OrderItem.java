package BL;

import DAL.OrderItemDB;

import java.util.Collection;

/**
 * Created by chris on 2016-09-30.
 */
public class OrderItem {
    private Item item;
    private int amount;

    protected OrderItem(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public static Collection<OrderItem> getItemsFromOrder(int id){
        return OrderItemDB.getItems(id);
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
