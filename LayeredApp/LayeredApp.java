
import java.util.Scanner;

public class LayeredApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ItemService service = new ItemService();

        while (true) {
            System.out.println("\n1. Add item");
            System.out.println("2. Show items");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter item name: ");
                String name = sc.nextLine();
                service.addItem(name);
                System.out.println("Item added");
            } else if (choice == 2) {
                service.showItems();
                System.out.println("Total items: " + service.getCount());
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }

        sc.close();
    }
}
