package BL;

import java.util.Collection;

/**
 * Created by Anton on 2016-09-28.
 */
public class ItemCategory {
    private int id;
    private ItemCategory parent;

    protected ItemCategory(int id, ItemCategory parent){
        this.id = id;
        this.parent = parent;
    }

    public int getId(){
        return id;
    }

    public ItemCategory getParent(){
        return parent;
    }

    public static Collection<ItemCategory> getAllCategories(){
        return null;
    }

    public static void addCategory(int parentId){
        //// TODO: 2016-09-30
    }
}
