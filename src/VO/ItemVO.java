package VO;

import BL.*;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by Christian on 2016-09-26.
 */
public class ItemVO {
    private String name;
    private int id;
    private int amount;
    private ItemCategoryVO category;

    public ItemVO(Item item){
        this.id = item.getId();
        this.name = item.getName();
        this.amount = item.getAmount();
        this.category = new ItemCategoryVO(item.getCategory());

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

    public static Collection<ItemVO> getItemsByCategory(ItemCategoryVO category){
        if(category == null)
            return getAll();

        Vector<ItemVO> toShow = new Vector<>();

        for(ItemVO item: getAll()){
            if(item.getCategory() != null){
                if(item.getCategory().getId() == category.getId()){
                    toShow.add(item);
                }
            }
        }
        return toShow;
    }

    public ItemCategoryVO getCategory(){
        return this.category;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAmount(){return amount;}
}
