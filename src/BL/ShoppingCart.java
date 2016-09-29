package BL;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by chris on 2016-09-28.
 */
public class ShoppingCart {
    private int id;
    private Collection<ShoppingCartItem> items;
    private User owner;

    protected ShoppingCart(int id, Collection<ShoppingCartItem> items, User owner) {
        this.id = id;
        this.items = items;
        this.owner = owner;
    }

    public Collection<ShoppingCartItem> getItems() {
        return items;
    }

    public User getOwner() {
        return owner;
    }

    public int getId(){
        return this.id;
    }

}
