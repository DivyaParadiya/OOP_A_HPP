
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemServiceTest {

    @Test
    void addingItemIncreasesCount() {
        ItemService service = new ItemService();

        service.addItem("Book");

        assertEquals(1, service.getCount());
    }

    @Test
    void addingTwoItemsIncreasesCountToTwo() {
        ItemService service = new ItemService();

        service.addItem("Book");
        service.addItem("Pen");

        assertEquals(2, service.getCount());
    }

    @Test
    void invalidActionThrowsException() {
        ItemService service = new ItemService();

        assertThrows(IllegalArgumentException.class,
                () -> service.performAction("DELETE"));
    }
}
