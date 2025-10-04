package tracker;



import java.sql.*;
import java.util.*;


public class SubscriptionDAO {

    public static void addSubscription(int userId, Subscription sub) {
        String sql = "INSERT INTO subscriptions (user_id, name, category, cost, renewal_date, frequency) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, sub.getName());
            ps.setString(3, sub.getCategory());
            ps.setDouble(4, sub.getCost());
            ps.setString(5, sub.getRenewalDate());
            ps.setString(6, sub.getFrequency());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Subscription> getSubscriptions(int userId) {
        List<Subscription> list = new ArrayList<>();
        String sql = "SELECT * FROM subscriptions WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subscription sub = new Subscription(
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("cost"),
                        rs.getString("renewal_date"),
                        rs.getString("frequency")
                );
                list.add(sub);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean deleteSubscription(int userId, String name) {
        String sql = "DELETE FROM subscriptions WHERE user_id = ? AND name = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, name);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
