package DAL;

import BL.Item;

import java.sql.Connection;

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

    public static void postItem(String name){
        //TODO: Save item to db.

        Connection con = DBManager.getConnection();
        System.out.println("skapar: " + name);
    }
}
