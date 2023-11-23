import org.example.Order;
import org.example.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class OrderTests {
    Product product = new Product(1, "test", 1.00);
    Product product2 = new Product(2, "test2", 2.00);
    Product product3 = new Product(3, "test3", 3.00);
    List<Product> orderItems = List.of(product, product2, product3);

    Order order;

    @BeforeEach
    public void init() {
        order = new Order(orderItems, 6.00);

    }

    @Test
    public void testgetOrderDetails() {
        var expected =
            "test 1.0\n" +
            "test2 2.0\n" +
            "test3 3.0\n" +
            "Total price: 6.0";

        var actual = order.getOrderDetails();

        assertEquals(expected, actual);


    }
}
