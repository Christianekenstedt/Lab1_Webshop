package BL;

/**
 * Created by chris on 2016-09-28.
 */
public class ShoppingCartItem {
    private int id;
    private Item item;
    private int amount;
    private int cartId;

    protected ShoppingCartItem(int id, Item item, int amount, int cartId) {
        this.id = id;
        this.item = item;
        this.amount = amount;
        this.cartId = cartId;
    }
}
