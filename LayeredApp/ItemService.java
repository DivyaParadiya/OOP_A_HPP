
import java.util.ArrayList;

public class ItemService {
    private ArrayList<Item> items = new ArrayList<>();

    public void addItem(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Invalid item name");

        items.add(new Item(name));
    }

    public int getCount() {
        return items.size();
    }

    public void performAction(String action) {
        if (!action.equals("ADD") && !action.equals("SHOW"))
            throw new IllegalArgumentException("Invalid action");
    }

    public void showItems() {
        for (Item item : items)
            System.out.println(item.getName());
    }
}
