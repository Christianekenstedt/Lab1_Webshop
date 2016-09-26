package DAL;

import BL.Item;

/**
 * Created by Christian on 2016-09-26.
 */
public class ItemDB extends Item {

    private ItemDB(int id, String name){
        super(id,name);
    }

    public static Item getItem(int id){
        //TODO: get item from db.
        return new ItemDB(id,"Skjorta");
    }
}
