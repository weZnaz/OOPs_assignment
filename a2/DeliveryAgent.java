public class DeliveryAgent extends Thread {

    private final OrderQueue orderQueue;
    private final String agentName;

    public DeliveryAgent(String name, OrderQueue queue) {
        this.agentName = name;
        this.orderQueue = queue;
    }

    public void run() {

        while (true) {

            Order order = orderQueue.getOrder();

            System.out.println("🚚 " + agentName +
                    " delivering Order-" + order.getOrderId());

            try {
                Thread.sleep(2000);  // Simulate delivery time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("✅ " + agentName +
                    " delivered Order-" + order.getOrderId());
        }
    }
}
