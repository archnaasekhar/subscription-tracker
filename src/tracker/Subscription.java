package tracker;

public class Subscription {
    private int id;
    private String name;
    private String category;
    private double cost;
    private String renewalDate;
    private String frequency;

    public Subscription(int id, String name, String category, double cost, String renewalDate, String frequency) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.renewalDate = renewalDate;
        this.frequency = frequency;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getCost() { return cost; }
    public String getRenewalDate() { return renewalDate; }
    public String getFrequency() { return frequency; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setCost(double cost) { this.cost = cost; }
    public void setRenewalDate(String renewalDate) { this.renewalDate = renewalDate; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    // Display method
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Category: " + category +
                ", Cost: " + cost + ", Renewal Date: " + renewalDate +
                ", Frequency: " + frequency);
    }
}
