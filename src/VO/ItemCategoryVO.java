package VO;

import BL.ItemCategory;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by Anton on 2016-09-30.
 */
public class ItemCategoryVO {
    private int id;
    private String name;
    private int parentId;

    public ItemCategoryVO(ItemCategory category){
        this.id = category.getId();
        this.name = category.getName();
        this.parentId = category.getParentId();
    }

    public static ItemCategoryVO getCategoryById(int id){
        return new ItemCategoryVO(ItemCategory.getCategoryByID(id));
    }

    public static Collection<ItemCategoryVO> getAllCategories(){
        Vector<ItemCategoryVO> categories = new Vector<>();

        for(ItemCategory cat : ItemCategory.getAllCategories()){
            categories.add(new ItemCategoryVO(cat));
        }

        return categories;
    }

    public static Collection<ItemCategoryVO> getCategoriesWithParent(ItemCategoryVO parent){
        Collection<ItemCategoryVO> allCategories = getAllCategories();
        Vector<ItemCategoryVO> toShow = new Vector<>();
        for(ItemCategoryVO cat : allCategories){
            if(cat.getId() == parent.getId()){
                toShow.add(cat);
            }
        }
        return toShow;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getParent(){
        return this.parentId;
    }


}
