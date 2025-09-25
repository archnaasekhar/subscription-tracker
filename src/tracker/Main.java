package tracker;

public class Main {
    public static void main(String[] args) {
        SubscriptionManager manager = new SubscriptionManager();

        // Add subscriptions
        manager.addSubscription(new Subscription("Netflix", 499.0, "2025-09-30"));
        manager.addSubscription(new Subscription("Spotify", 399.0, "2025-09-08"));

        System.out.println("\n--- Subscriptions ---");
        manager.listSubscriptions();

        // Update
        manager.updateSubscription("Netflix", 599.0, "2025-10-30");

        System.out.println("\n--- After Update ---");
        manager.listSubscriptions();

        // Delete
        manager.removeSubscription("Spotify");

        System.out.println("\n--- After Delete ---");
        manager.listSubscriptions();

        // Total cost
        System.out.println("\nTotal Cost: " + manager.totalCost());
    }
}
