package BL;

import DAL.ItemDB;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by Christian on 2016-09-26.
 */
public abstract class Item {
    private String name;
    private int id;
    private int amount;
    private ItemCategory category;

    protected Item(int id, String name, int amount, ItemCategory category){
        this.name=name;
        this.id=id;
        this.amount = amount;
        this.category = category;
    }

    public static Collection<Item> getAll(){
        return ItemDB.getAllFromDB();
    }

    public static Item get(int id){
        return ItemDB.getFromDB(id);
    }

    public static void addItem(String name, int amount, ItemCategory category){
        ItemDB.addItemToDB(name, amount, category);
    }

    public static void delete(int id){
        ItemDB.deleteFromDB(id);
    }

    public static void update(int id, String name, int amount, ItemCategory category){
        ItemDB.updateInDB(id, name, amount, category);
    }

    public ItemCategory getCategory(){
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public int getAmount(){
        return this.amount;
    }
}
