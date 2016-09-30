package BL;

import DAL.ItemCategoryDB;

import java.util.Collection;

/**
 * Created by Anton on 2016-09-28.
 */
public class ItemCategory {
    private int id;
    private int parentId;
    private String name;

    protected ItemCategory(int id, int parentId, String name){
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public int getParentId(){
        return parentId;
    }

    public String getName(){
        return name;
    }

    public static ItemCategory getCategoryByID(int id){
        return ItemCategoryDB.getCategoryByIdFromDB(id);
    }

    public static Collection<ItemCategory> getAllCategories(){
        return ItemCategoryDB.getAllCategoriesFromDB();
    }

    public static void addCategory(int parentId, String name){
        ItemCategoryDB.addItemCategoryToDB(name, parentId);
    }

    public static void deleteCategory(int categoryId) {
        ItemCategoryDB.removeItemCategoryFromDB(categoryId);
    }
}
