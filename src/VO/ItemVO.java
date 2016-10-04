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
    private float price;

    public ItemVO(Item item){
        this.id = item.getId();
        this.name = item.getName();
        this.amount = item.getAmount();
        this.category = new ItemCategoryVO(item.getCategory());
        this.price = item.getPrice();
    }

    public static void addItem(String name, int amount, int categoryId, float price){
        Item.addItem(name,amount,ItemCategory.getCategoryByID(categoryId), price);
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

    public static void updateItem(int id, String newName, int newCategoryId, int newAmount, float price){
        Item.update(id, newName, newAmount, newCategoryId, price);
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

    public float getPrice(){return price;}

    public static void deleteItem(int deleteItemId) {
        Item.delete(deleteItemId);
    }

}
