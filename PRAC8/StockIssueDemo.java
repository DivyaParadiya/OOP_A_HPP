
import java.util.*;

class OutOfStockException extends Exception {
    private int shortfall;

    OutOfStockException(int shortfall) {
        super("Insufficient stock. Shortfall: " + shortfall);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {
    private Map<String, Integer> stock = new HashMap<>();

    Warehouse() {
        stock.put("Pen", 10);
        stock.put("Book", 5);
        stock.put("Pencil", 8);
    }

    public void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException("Quantity must be positive");
        }

        int available = stock.getOrDefault(item, 0);

        if (qty > available) {
            throw new OutOfStockException(qty - available);
        }

        stock.put(item, available - qty);
        System.out.println("Issued " + qty + " " + item);
    }
}

public class StockIssueDemo {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        String[][] requests = {
            {"Pen", "3"},
            {"Book", "8"},
            {"Pencil", "0"},
            {"Pen", "4"},
            {"Book", "2"}
        };

        for (String[] request : requests) {
            try {
                warehouse.issue(request[0], Integer.parseInt(request[1]));
            } catch (OutOfStockException e) {
                System.out.println(e.getMessage());
            } catch (InvalidQuantityException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
