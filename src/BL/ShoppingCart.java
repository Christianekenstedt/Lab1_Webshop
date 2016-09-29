package BL;

import java.util.Vector;

/**
 * Created by chris on 2016-09-28.
 */
public class ShoppingCart {
    private Vector<ShoppingCartItem> items;
    private User owner;

    protected ShoppingCart(Vector<ShoppingCartItem> items, User owner) {
        this.items = items;
        this.owner = owner;
    }

    public Vector<ShoppingCartItem> getItems() {
        return items;
    }

    public User getOwner() {
        return owner;
    }

}
