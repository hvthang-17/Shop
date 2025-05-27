package dao;

import model.Account;
import model.Category;
import model.Product;
import util.Database;

import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Repository
public class ProductDao {

    private final AccountDao accountDao = new AccountDao();
    private final CategoryDao categoryDao = new CategoryDao();

    private String getBase64Image(Blob blob) throws SQLException, IOException {
        if (blob == null) return null;
        try (InputStream inputStream = blob.getBinaryStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        }
    }

    private Product extractProduct(ResultSet rs) throws SQLException, IOException {
        int id = rs.getInt("product_id");
        String name = rs.getString("product_name");
        double price = rs.getDouble("product_price");
        String description = rs.getString("product_description");
        boolean isDeleted = rs.getBoolean("product_is_deleted");
        int amount = rs.getInt("product_amount");
        String base64Image = getBase64Image(rs.getBlob("product_image"));
        Category category = categoryDao.getCategory(rs.getInt("fk_category_id"));
        Account account = accountDao.getAccount(rs.getInt("fk_account_id"));

        return new Product(id, name, base64Image, price, description, category, account, isDeleted, amount);
    }

    private List<Product> getListProductQuery(String query, Object... params) {
        List<Product> list = new ArrayList<>();
        try (Connection conn = new Database().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(extractProduct(rs));
                }
            }

        } catch (SQLException | IOException e) {
            System.out.println("getListProductQuery error: " + e.getMessage());
        }
        return list;
    }

    public List<Product> getAllProducts() {
        return getListProductQuery("SELECT * FROM product WHERE product_is_deleted = false");
    }

    public Product getProduct(int productId) {
        String query = "SELECT * FROM product WHERE product_id = ?";
        try (Connection conn = new Database().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return extractProduct(rs);
            }

        } catch (SQLException | IOException e) {
            System.out.println("getProduct error: " + e.getMessage());
        }
        return null;
    }

    public List<Product> getAllCategoryProducts(int categoryId) {
        return getListProductQuery("SELECT * FROM product WHERE fk_category_id = ? AND product_is_deleted = false", categoryId);
    }

    public List<Product> searchProduct(String keyword) {
        return getListProductQuery("SELECT * FROM product WHERE product_name LIKE ? AND product_is_deleted = false", "%" + keyword + "%");
    }

    public List<Product> getSellerProducts(int sellerId) {
        return getListProductQuery("SELECT * FROM product WHERE fk_account_id = ?", sellerId);
    }

    public void removeProduct(int productId) {
        String query = "UPDATE product SET product_is_deleted = true WHERE product_id = ?";
        try (Connection conn = new Database().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("removeProduct error: " + e.getMessage());
        }
    }

    public void addProduct(String name, InputStream image, double price, String description,
                           int categoryId, int sellerId, int amount) {
        String query = "INSERT INTO product (product_name, product_image, product_price, product_description, fk_category_id, fk_account_id, product_is_deleted, product_amount) " +
                       "VALUES (?, ?, ?, ?, ?, ?, false, ?)";
        try (Connection conn = new Database().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setBinaryStream(2, image);
            ps.setDouble(3, price);
            ps.setString(4, description);
            ps.setInt(5, categoryId);
            ps.setInt(6, sellerId);
            ps.setInt(7, amount);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("addProduct error: " + e.getMessage());
        }
    }

    public void editProduct(int productId, String name, InputStream image, double price, String description,
                            int categoryId, int amount) {
        String query = "UPDATE product SET product_name = ?, product_image = ?, product_price = ?, product_description = ?, fk_category_id = ?, product_amount = ? WHERE product_id = ?";
        try (Connection conn = new Database().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setBinaryStream(2, image);
            ps.setDouble(3, price);
            ps.setString(4, description);
            ps.setInt(5, categoryId);
            ps.setInt(6, amount);
            ps.setInt(7, productId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("editProduct error: " + e.getMessage());
        }
    }

    public List<Product> get12ProductsOfPage(int pageIndex) {
        int offset = (pageIndex - 1) * 12;
        return getListProductQuery("SELECT * FROM product WHERE product_is_deleted = false LIMIT ?, 12", offset);
    }

    public int getTotalNumberOfProducts() {
        String query = "SELECT COUNT(*) FROM product WHERE product_is_deleted = false";
        try (Connection conn = new Database().getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("getTotalNumberOfProducts error: " + e.getMessage());
        }
        return 0;
    }

    public void decreaseProductAmount(int productId, int quantity) {
        String query = "UPDATE product SET product_amount = product_amount - ? WHERE product_id = ?";
        try (Connection conn = new Database().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("decreaseProductAmount error: " + e.getMessage());
        }
    }
}
