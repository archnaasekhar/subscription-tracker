package tracker;

public class Main {
    public static void main(String[] args) {
        SubscriptionManager manager = new SubscriptionManager();

        // Add subscriptions
        manager.addSubscription(new Subscription(1, "Netflix", "Entertainment", 499.0, "2025-09-30", "Monthly"));
        manager.addSubscription(new Subscription(2, "Spotify", "Music", 399.0, "2025-09-08", "Monthly"));

        System.out.println("\n--- Subscriptions ---");
        manager.listSubscriptions();

        // Update
        manager.updateSubscription(1, 599.0, "2025-10-30");

        System.out.println("\n--- After Update ---");
        manager.listSubscriptions();

        // Delete
        manager.removeSubscription(2);

        System.out.println("\n--- After Delete ---");
        manager.listSubscriptions();

        // Total cost
        System.out.println("\nTotal Cost: " + manager.totalCost());
    }
}
