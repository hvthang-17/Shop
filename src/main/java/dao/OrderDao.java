package dao;

import model.CartProduct;
import model.Order;
import model.Product;
import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

    private final ProductDao productDao;
    private final AccountDao accountDao;

    @Autowired
    public OrderDao(ProductDao productDao, AccountDao accountDao) {
        this.productDao = productDao;
        this.accountDao = accountDao;
    }

    // Lấy order_id mới nhất trong bảng `order`
    public int getLastOrderId() {
        String query = "SELECT order_id FROM `order` ORDER BY order_id DESC LIMIT 1";
        int orderId = 0;

        try (Connection connection = new Database().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                orderId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Get last order id error: " + e.getMessage());
        }
        return orderId;
    }

    // Tạo chi tiết đơn hàng (order_detail) với danh sách sản phẩm
    private void createOrderDetail(Connection connection, int orderId, List<CartProduct> cartProducts) throws SQLException {
        String query = "INSERT INTO order_detail (fk_order_id, fk_product_id, product_quantity, product_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (CartProduct cartProduct : cartProducts) {
                // Giảm số lượng sản phẩm trong kho
                productDao.decreaseProductAmount(cartProduct.getProduct().getId(), cartProduct.getQuantity());

                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, cartProduct.getProduct().getId());
                preparedStatement.setInt(3, cartProduct.getQuantity());
                preparedStatement.setDouble(4, cartProduct.getPrice());
                preparedStatement.executeUpdate();
            }
        }
    }

    // Tạo order mới, đồng thời tạo order_detail trong transaction
    public void createOrder(int accountId, double totalPrice, List<CartProduct> cartProducts) {
        String query = "INSERT INTO `order` (fk_account_id, order_total) VALUES (?, ?)";
        Connection connection = null;

        try {
            connection = new Database().getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, accountId);
                preparedStatement.setDouble(2, totalPrice);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating order failed, no rows affected.");
                }

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int orderId = generatedKeys.getInt(1);
                        createOrderDetail(connection, orderId, cartProducts);
                    } else {
                        throw new SQLException("Creating order failed, no ID obtained.");
                    }
                }
            }

            connection.commit();
        } catch (SQLException e) {
            System.out.println("Create order error: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println("Rollback failed: " + ex.getMessage());
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Closing connection error: " + e.getMessage());
                }
            }
        }
    }

    // Lấy danh sách CartProduct theo productId trong order_detail
    public List<CartProduct> getSellerOrderDetail(int productId) {
        List<CartProduct> list = new ArrayList<>();
        String query = "SELECT * FROM order_detail WHERE fk_product_id = ?";

        try (Connection connection = new Database().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int productIdFromDb = resultSet.getInt("fk_product_id");
                    Product product = productDao.getProduct(productIdFromDb);
                    int productQuantity = resultSet.getInt("product_quantity");
                    double productPrice = resultSet.getDouble("product_price");

                    list.add(new CartProduct(product, productQuantity, productPrice));
                }
            }
        } catch (SQLException e) {
            System.out.println("Get seller order detail error: " + e.getMessage());
        }
        return list;
    }

    // Lấy lịch sử order của một account
    public List<Order> getOrderHistory(int accountId) {
        List<Order> list = new ArrayList<>();
        String query = "SELECT * FROM `order` WHERE fk_account_id = ?";

        try (Connection connection = new Database().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, accountId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("order_id");
                    double orderTotal = resultSet.getDouble("order_total");
                    Date orderDate = resultSet.getDate("order_date");

                    list.add(new Order(orderId, orderTotal, orderDate));
                }
            }
        } catch (SQLException e) {
            System.out.println("Get order history error: " + e.getMessage());
        }
        return list;
    }

    // Lấy chi tiết đơn hàng theo orderId
    public List<CartProduct> getOrderDetailHistory(int orderId) {
        List<CartProduct> list = new ArrayList<>();
        String query = "SELECT * FROM order_detail WHERE fk_order_id = ?";

        try (Connection connection = new Database().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int productId = resultSet.getInt("fk_product_id");
                    Product product = productDao.getProduct(productId);
                    int quantity = resultSet.getInt("product_quantity");
                    double price = resultSet.getDouble("product_price");

                    list.add(new CartProduct(product, quantity, price));
                }
            }
        } catch (SQLException e) {
            System.out.println("Get order detail history error: " + e.getMessage());
        }
        return list;
    }
}
