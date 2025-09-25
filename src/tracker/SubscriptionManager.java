package tracker;

import java.util.ArrayList;

public class SubscriptionManager {
    private ArrayList<Subscription> subscriptions;

    public SubscriptionManager() {
        subscriptions = new ArrayList<>();
    }

    // CREATE: add subscription
    public void addSubscription(Subscription s) {
        subscriptions.add(s);
    }

    // READ: list all
    public void listSubscriptions() {
        for (Subscription s : subscriptions) {
            s.display();
        }
    }

    // UPDATE by ID
    public void updateSubscription(int id, double newCost, String newDate) {
        for (Subscription s : subscriptions) {
            if (s.getId() == id) {
                s.setCost(newCost);
                s.setRenewalDate(newDate);
                System.out.println("Subscription ID " + id + " updated successfully!");
                return;
            }
        }
        System.out.println("Subscription not found!");
    }

    // DELETE by ID
    public void removeSubscription(int id) {
        for (int i = 0; i < subscriptions.size(); i++) {
            if (subscriptions.get(i).getId() == id) {
                subscriptions.remove(i);
                System.out.println("Subscription ID " + id + " removed successfully!");
                return;
            }
        }
        System.out.println("Subscription not found!");
    }

    // TOTAL cost
    public double totalCost() {
        double total = 0;
        for (Subscription s : subscriptions) {
            total += s.getCost();
        }
        return total;
    }

    // Getter for all subscriptions (for frontend/db integration)
    public ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }
}
