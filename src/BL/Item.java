package BL;

import DAL.ItemDB;

/**
 * Created by Christian on 2016-09-26.
 */
public class Item {
    private String name;
    private int id;

    protected Item(int id, String name){
        this.name=name;
        this.id=id;
    }

    public static Item getItem(int id){
        return ItemDB.getItem(id);
    }

    public static void postItem(String name){
        ItemDB.postItem(name);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
