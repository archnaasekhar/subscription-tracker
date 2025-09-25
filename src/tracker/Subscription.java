package tracker;

public class Subscription {
    private String name;
    private double cost;
    private String renewalDate;

    // constructor
    public Subscription(String name, double cost, String renewalDate) {
        this.name = name;
        this.cost = cost;
        this.renewalDate = renewalDate;
    }

    // getters and setters
    public String getName() {
        return name;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setRenewalDate(String renewalDate) {
        this.renewalDate = renewalDate;
    }

    // display method
    public void display() {
        System.out.println("Subscription: " + name +
                ", Cost: " + cost +
                ", Renewal Date: " + renewalDate);
    }
}
