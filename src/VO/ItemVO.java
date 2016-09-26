package VO;

import BL.Item;

import java.util.Hashtable;

/**
 * Created by Christian on 2016-09-26.
 */
public class ItemVO {
    private String name;
    private int id;

    private ItemVO(Item item){
        this.id = item.getId();
        this.name = item.getName();
    }

    public static ItemVO viewItem(int id){
        return new ItemVO(Item.getItem(id));
    }

    public static void postItem(String name){
        Item.postItem(name);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
