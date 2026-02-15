public class Order {

    private static int count = 0;
    private final int orderId;

    public Order() {
        this.orderId = ++count;
        System.out.println("🧾 Order Placed: Order-" + orderId);
    }

    public int getOrderId() {
        return orderId;
    }
}
