package VO;

import BL.ShoppingCartItem;

/**
 * Created by Anton on 2016-09-29.
 */
public class ShoppingCartItemVO {
    private ItemVO item;
    private int id;
    private int amount;

    public ShoppingCartItemVO(ShoppingCartItem itm){
        this.item = new ItemVO(itm.getItem());
        this.id = itm.getId();
        this.amount = itm.getAmount();
    }

    public int getId(){
        return id;
    }

    public ItemVO getItem(){
        return item;
    }

    public int getAmount(){
        return this.amount;
    }
}
