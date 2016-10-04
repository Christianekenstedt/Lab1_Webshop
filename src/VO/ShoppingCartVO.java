package VO;

import BL.ShoppingCart;
import BL.ShoppingCartItem;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by Anton on 2016-09-29.
 */
public class ShoppingCartVO {
    private Collection<ShoppingCartItemVO> items;
    private int id;

    private ShoppingCartVO(ShoppingCart cart){
        Vector<ShoppingCartItemVO> itemsToAdd = new Vector<>();
        for(ShoppingCartItem item: cart.getItems()){
            itemsToAdd.add(new ShoppingCartItemVO(item));
        }
        items = itemsToAdd;
        this.id = cart.getId();
    }

    public static void addItemToCart(int userId, int itemId, int amount){
        ShoppingCart.addItemToCart(userId, itemId, amount);
    }

    public static void removeItemFromCart(int cartItemId){
        ShoppingCart.removeItemFromCart(cartItemId);
    }

    public static ShoppingCartVO getCartByUser(int userId){
        return new ShoppingCartVO(ShoppingCart.getCartByUser(userId));
    }

    public Collection<ShoppingCartItemVO> getItems(){
        return items;
    }

    public float getTotalPrice(){
        float sum = 0;
        for(ShoppingCartItemVO item : items){
            sum += item.getAmount() * item.getItem().getPrice();
        }
        return sum;
    }
}
