package tracker;
import tracker.Subscription;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

public class SubscriptionApp extends JFrame {

    private String currentUser;
    private int currentUserId;
    private SubscriptionManager manager;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SubscriptionApp().setVisible(true));
    }

    public SubscriptionApp() {
        setTitle("Subscription Management System");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        showLoginPage();
    }

    /* ========== LOGIN PAGE ========== */
    private void showLoginPage() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(224, 240, 255));
            }
        };
        panel.setBorder(new EmptyBorder(30, 80, 30, 80));

        JLabel title = new JLabel("Subscription Management System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 51, 102));
        panel.add(title, BorderLayout.NORTH);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        loginPanel.setOpaque(false);

        JLabel userLbl = new JLabel("Username:");
        JLabel passLbl = new JLabel("Password:");
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = createStyledButton("Login", new Color(0, 102, 204));
        JButton createBtn = createStyledButton("Create Account", new Color(51, 153, 255));

        loginPanel.add(userLbl);
        loginPanel.add(userField);
        loginPanel.add(passLbl);
        loginPanel.add(passField);
        loginPanel.add(loginBtn);
        loginPanel.add(createBtn);

        panel.add(loginPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);

        // Login
        loginBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword());
            int userId = UserDAO.login(username, password);
            if (userId != -1) {
                currentUser = username;
                currentUserId = userId;
                manager = new SubscriptionManager(userId);
                JOptionPane.showMessageDialog(this, "Login successful!");
                openDashboard();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        });

        // Create account
        createBtn.addActionListener(e -> showCreateAccountPage());

        revalidate();
        repaint();
    }

    /* ========== CREATE ACCOUNT PAGE ========== */
    private void showCreateAccountPage() {
        JFrame signupFrame = new JFrame("Create Account");
        signupFrame.setSize(400, 200);
        signupFrame.setLocationRelativeTo(this);
        signupFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton createBtn = createStyledButton("Create", new Color(0, 102, 204));

        signupFrame.add(new JLabel("Username:"));
        signupFrame.add(userField);
        signupFrame.add(new JLabel("Password:"));
        signupFrame.add(passField);
        signupFrame.add(new JLabel());
        signupFrame.add(createBtn);

        createBtn.addActionListener(e -> {
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword());
            if (UserDAO.register(user, pass)) {
                JOptionPane.showMessageDialog(signupFrame, "Account created successfully!");
                signupFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(signupFrame, "Username already exists!");
            }
        });

        signupFrame.setVisible(true);
    }

    /* ========== DASHBOARD ========== */
    private void openDashboard() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Dashboard - " + currentUser, SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(0, 51, 102));
        add(title, BorderLayout.NORTH);

        JPanel sidebar = new JPanel(new GridLayout(6, 1, 10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(0, 51, 102));
            }
        };
        sidebar.setBorder(new EmptyBorder(15, 10, 15, 10));

        JButton addBtn = createStyledButton("Add Subscription", new Color(51, 153, 255));
        JButton listBtn = createStyledButton("List Subscriptions", new Color(0, 102, 204));
        JButton deleteBtn = createStyledButton("Delete Subscription", new Color(0, 76, 153));
        JButton reminderBtn = createStyledButton("Reminders", new Color(0, 128, 255));
        JButton costBtn = createStyledButton("Total Cost", new Color(0, 102, 204));
        JButton logoutBtn = createStyledButton("Logout", new Color(0, 51, 102));

        sidebar.add(addBtn);
        sidebar.add(listBtn);
        sidebar.add(deleteBtn);
        sidebar.add(reminderBtn);
        sidebar.add(costBtn);
        sidebar.add(logoutBtn);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(224, 240, 255));
        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContent(content, "Welcome, " + currentUser + "! Choose an option from the sidebar.");

        add(sidebar, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);

        addBtn.addActionListener(e -> showAddForm(content));
        listBtn.addActionListener(e -> showList(content));
        deleteBtn.addActionListener(e -> showDelete(content));
        reminderBtn.addActionListener(e -> showReminders(content));
        costBtn.addActionListener(e -> showTotalCost(content));
        logoutBtn.addActionListener(e -> {
            currentUser = null;
            showLoginPage();
        });

        revalidate();
        repaint();
    }

    private JButton createStyledButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        return btn;
    }

    private void setContent(JPanel content, String msg) {
        content.removeAll();
        JLabel label = new JLabel(msg, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        label.setForeground(new Color(0, 51, 102));
        content.add(label, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    /* ========== ADD FORM ========== */
    private void showAddForm(JPanel content) {
        content.removeAll();
        content.setLayout(new GridLayout(6, 2, 10, 10));
        content.setBackground(new Color(224, 240, 255));

        JTextField nameField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField costField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField freqField = new JTextField();
        JButton addBtn = createStyledButton("Add", new Color(0, 102, 204));

        content.add(new JLabel("Name:"));
        content.add(nameField);
        content.add(new JLabel("Category:"));
        content.add(categoryField);
        content.add(new JLabel("Cost:"));
        content.add(costField);
        content.add(new JLabel("Renewal Date (dd-MM-yyyy):"));
        content.add(dateField);
        content.add(new JLabel("Frequency:"));
        content.add(freqField);
        content.add(new JLabel());
        content.add(addBtn);

        addBtn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String cat = categoryField.getText().trim();
                double cost = Double.parseDouble(costField.getText());
                String date = dateField.getText().trim();
                String freq = freqField.getText().trim();

                Subscription sub = new Subscription(name, cat, cost, date, freq);
                manager.addSubscription(sub);
                JOptionPane.showMessageDialog(this, "Subscription added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please check the fields.");
            }
        });

        content.revalidate();
        content.repaint();
    }

    /* ========== LIST SUBSCRIPTIONS ========== */
    private void showList(JPanel content) {
        content.removeAll();
        content.setLayout(new BorderLayout());
        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setEditable(false);
        area.setBackground(new Color(224, 240, 255));
        area.setForeground(new Color(0, 51, 102));

        List<Subscription> subs = manager.getSubscriptions();
        if (subs.isEmpty()) {
            area.setText("No subscriptions found.");
        } else {
            for (Subscription s : subs) {
                area.append(s + "\n\n");
            }
        }

        content.add(new JScrollPane(area), BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    /* ========== DELETE SUBSCRIPTION ========== */
    private void showDelete(JPanel content) {
        content.removeAll();
        content.setLayout(new GridLayout(2, 2, 10, 10));
        content.setBackground(new Color(224, 240, 255));

        JTextField nameField = new JTextField();
        JButton delBtn = createStyledButton("Delete", new Color(0, 76, 153));

        content.add(new JLabel("Enter Subscription Name to Delete:"));
        content.add(nameField);
        content.add(new JLabel());
        content.add(delBtn);

        delBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (manager.deleteSubscription(name)) {
                JOptionPane.showMessageDialog(this, "Subscription deleted!");
            } else {
                JOptionPane.showMessageDialog(this, "Subscription not found!");
            }
        });

        content.revalidate();
        content.repaint();
    }

    /* ========== REMINDERS ========== */
    private void showReminders(JPanel content) {
        content.removeAll();
        content.setLayout(new BorderLayout());

        List<Subscription> subs = manager.getSubscriptions();
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setBackground(new Color(224, 240, 255));
        area.setForeground(new Color(0, 51, 102));

        if (subs.isEmpty()) {
            area.setText("No subscriptions to check reminders.");
        } else {
            boolean found = false;
            double total = 0;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date today = new Date();
                for (Subscription s : subs) {
                    Date renewDate = sdf.parse(s.getRenewalDate());
                    long diff = renewDate.getTime() - today.getTime();
                    long daysLeft = diff / (1000 * 60 * 60 * 24);

                    if (daysLeft <= 7 && daysLeft >= 0) {
                        found = true;
                        total += s.getCost();
                        area.append("Name: " + s.getName() + "\n");
                        area.append("Category: " + s.getCategory() + "\n");
                        area.append("Renewal Date: " + s.getRenewalDate() + " | Days Left: " + daysLeft + "\n");
                        area.append("Cost: " + s.getCost() + " | Frequency: " + s.getFrequency() + "\n\n");
                    }
                }
                if (!found) area.setText("No upcoming renewals in next 7 days.");
                else area.append("Total upcoming cost: ₹ " + total);
            } catch (Exception e) {
                area.setText("Error parsing dates! Use dd-MM-yyyy format.");
            }
        }

        content.add(new JScrollPane(area), BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    /* ========== TOTAL COST ========== */
    private void showTotalCost(JPanel content) {
        content.removeAll();
        double total = 0;
        for (Subscription s : manager.getSubscriptions()) total += s.getCost();
        setContent(content, "Total Monthly Cost: ₹ " + total);
    }
}
