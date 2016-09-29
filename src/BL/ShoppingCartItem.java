package BL;

/**
 * Created by chris on 2016-09-28.
 */
public class ShoppingCartItem {
    private String name;
    private int id;
    private int amount;
    private ItemCategory category;

    protected ShoppingCartItem(Item item) {
        this.name = item.getName();
        this.id = item.getId();
        this.amount = item.getAmount();
        this.category = item.getCategory();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public ItemCategory getCategory() {
        return category;
    }
}
