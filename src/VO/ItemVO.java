package VO;

import BL.*;

import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by Christian on 2016-09-26.
 */
public class ItemVO {
    private String name;
    private int id;
    private int amount;

    private ItemVO(Item item){
        this.id = item.getId();
        this.name = item.getName();
        this.amount = item.getAmount();
    }

    public static ItemVO get(int id){
        return new ItemVO(Item.get(id));
    }

    public static Vector<ItemVO> getAll(){
        Vector<ItemVO> items = new Vector<>();
        for(Item item : Item.getAll()){
            items.add(new ItemVO(item));
        }
        return items;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAmount(){return amount;}
}
