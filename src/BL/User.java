package BL;

/**
 * Created by chris on 2016-09-28.
 */
public class User {
    private int id;
    private String username;
    //TODO: Role också.
    private ShoppingCart shoppingCart;

    public User(String username){
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
