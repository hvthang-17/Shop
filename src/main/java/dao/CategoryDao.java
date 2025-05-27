package dao;

import model.Category;
import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {

    // Lấy số lượng sản phẩm theo category, set vào category
	private void queryCategoryProductAmount(Category category) {
	    String query = "SELECT COUNT(*) FROM product WHERE fk_category_id = ? AND product_is_deleted = 0";
	    try (Connection connection = new Database().getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setInt(1, category.getId());

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                int count = resultSet.getInt(1);
	                System.out.println("▶ DEBUG: Category " + category.getName() + " có " + count + " sản phẩm");
	                category.setTotalCategoryProduct(count);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("▶ ERROR đếm sản phẩm: " + e.getMessage());
	    }
	}


    // Lấy Category theo id
    public Category getCategory(int categoryId) {
        Category category = null;
        String query = "SELECT * FROM category WHERE category_id = ?";
        try (Connection connection = new Database().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, categoryId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    category = new Category();
                    category.setId(resultSet.getInt("category_id"));
                    category.setName(resultSet.getString("category_name"));
                }
            }

            if (category != null) {
                queryCategoryProductAmount(category);
            }

        } catch (SQLException e) {
            System.out.println("Get category by id error: " + e.getMessage());
        }
        return category;
    }

    // Lấy tất cả category
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String query = "SELECT * FROM category";

        try (Connection connection = new Database().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("category_name"));
                list.add(category);
            }

            for (Category category : list) {
                queryCategoryProductAmount(category);
            }

        } catch (SQLException e) {
            System.out.println("Get all categories error: " + e.getMessage());
        }
        return list;
    }
    
    public void insertCategory(Category category) {
        String query = "INSERT INTO category (category_name) VALUES (?)";
        try (Connection connection = new Database().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Insert category error: " + e.getMessage());
        }
    }

    public void updateCategory(Category category) {
        String query = "UPDATE category SET category_name = ? WHERE category_id = ?";
        try (Connection connection = new Database().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Update category error: " + e.getMessage());
        }
    }

    public void deleteCategory(int id) {
        String query = "DELETE FROM category WHERE category_id = ?";
        try (Connection connection = new Database().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete category error: " + e.getMessage());
        }
    }
}
