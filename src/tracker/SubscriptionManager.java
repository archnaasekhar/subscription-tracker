package tracker;

import java.util.ArrayList;

public class SubscriptionManager {
    private ArrayList<Subscription> subscriptions;

    // constructor
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

    // UPDATE: update cost or renewal date by name
    public void updateSubscription(String name, double newCost, String newDate) {
        for (Subscription s : subscriptions) {
            if (s.getName().equalsIgnoreCase(name)) {
                s.setCost(newCost);
                s.setRenewalDate(newDate);
                System.out.println(name + " updated successfully!");
                return;
            }
        }
        System.out.println("Subscription not found!");
    }

    // DELETE: remove by name
    public void removeSubscription(String name) {
        for (int i = 0; i < subscriptions.size(); i++) {
            if (subscriptions.get(i).getName().equalsIgnoreCase(name)) {
                subscriptions.remove(i);
                System.out.println(name + " removed successfully!");
                return;
            }
        }
        System.out.println("Subscription not found!");
    }

    // TOTAL: calculate total cost
    public double totalCost() {
        double total = 0;
        for (Subscription s : subscriptions) {
            total += s.getCost();
        }
        return total;
    }
}
