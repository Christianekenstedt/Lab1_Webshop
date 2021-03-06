package VO;

import BL.Item;
import BL.ItemCategory;
import BL.OrderItem;
import DAL.OrderItemDB;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by chris on 2016-09-30.
 */
public class OrderItemVO {
    private int id;
    private String name;
    private int amount;
    private ItemCategory category;

    public OrderItemVO(OrderItem item){
        this.id = item.getItem().getId();
        this.name = item.getItem().getName();
        this.amount = item.getAmount();
        this.category = item.getItem().getCategory();
    }
    public static Collection<OrderItemVO> getItemsFromOrder(int id){
        Vector<OrderItemVO> items = new Vector<>();
        for (OrderItem item: OrderItem.getItemsFromOrder(id)){
            items.add(new OrderItemVO(item));
        }
        return items;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public ItemCategory getCategory() {
        return category;
    }
}
