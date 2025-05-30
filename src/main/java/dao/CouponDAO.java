package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.Coupon;
import util.Database;

@Repository
public class CouponDAO {

    public Coupon getCouponById(int id) {
        String sql = "SELECT * FROM coupons WHERE id = ?";
        try (Connection conn = new Database().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Coupon(
                    rs.getInt("id"),
                    rs.getString("code"),
                    rs.getInt("discount_percent"),
                    rs.getDate("expiry_date")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Coupon> getAllCoupons() {
        List<Coupon> list = new ArrayList<>();
        String sql = "SELECT * FROM coupons";
        try (Connection conn = new Database().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Coupon(
                    rs.getInt("id"),
                    rs.getString("code"),
                    rs.getInt("discount_percent"),
                    rs.getDate("expiry_date")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addCoupon(Coupon c) {
        String sql = "INSERT INTO coupons (code, discount_percent, expiry_date) VALUES (?, ?, ?)";
        try (Connection conn = new Database().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getCode());
            ps.setInt(2, c.getDiscountPercent());
            ps.setDate(3, c.getExpiryDate());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCoupon(Coupon c) {
        String sql = "UPDATE coupons SET code = ?, discount_percent = ?, expiry_date = ? WHERE id = ?";
        try (Connection conn = new Database().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getCode());
            ps.setInt(2, c.getDiscountPercent());
            ps.setDate(3, c.getExpiryDate());
            ps.setInt(4, c.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCoupon(int id) {
        String sql = "DELETE FROM coupons WHERE id = ?";
        try (Connection conn = new Database().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isCodeExists(String code) {
        String sql = "SELECT COUNT(*) FROM coupons WHERE code = ?";
        try (Connection conn = new Database().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Coupon getCouponByCode(String code) {
        String sql = "SELECT * FROM coupons WHERE code = ?";

        try (Connection conn = new Database().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return extractCoupon(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Coupon extractCoupon(ResultSet rs) throws SQLException {
        return new Coupon(
            rs.getInt("id"),
            rs.getString("code"),
            rs.getInt("discount_percent"),
            rs.getDate("expiry_date")
        );
    }
}
