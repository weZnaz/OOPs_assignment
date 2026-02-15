import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {

    private final Queue<Order> orders = new LinkedList<>();

    // Producer method
    public synchronized void addOrder(Order order) {
        orders.add(order);
        System.out.println("📦 Order-" + order.getOrderId() + " added to queue");

        notify();   // Wake up waiting delivery agent
    }

    // Consumer method
    public synchronized Order getOrder() {

        while (orders.isEmpty()) {
            try {
                wait();   // Wait if no order
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return orders.poll();
    }
}
