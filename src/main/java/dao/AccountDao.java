package dao;

import model.Account;
import util.Database;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Base64;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {

 private String getBase64Image(Blob blob) throws SQLException, IOException {
     try (InputStream inputStream = blob.getBinaryStream();
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
         byte[] buffer = new byte[4096];
         int bytesRead;
         while ((bytesRead = inputStream.read(buffer)) != -1) {
             outputStream.write(buffer, 0, bytesRead);
         }
         byte[] imageBytes = outputStream.toByteArray();
         return Base64.getEncoder().encodeToString(imageBytes);
     }
 }

 private Account queryGetAccount(String query, Object... params) {
     Account account = null;
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         try (Connection connection = new Database().getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(query)) {

             for (int i = 0; i < params.length; i++) {
                 preparedStatement.setObject(i + 1, params[i]);
             }

             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                 if (resultSet.next()) {
                     account = new Account();
                     account.setId(resultSet.getInt("account_id"));
                     account.setUsername(resultSet.getString("account_name"));
                     account.setPassword(resultSet.getString("account_password"));
                     account.setIsSeller(resultSet.getInt("account_is_seller"));
                     account.setIsAdmin(resultSet.getInt("account_is_admin"));
                     account.setAddress(resultSet.getString("account_address"));
                     account.setFirstName(resultSet.getString("account_first_name"));
                     account.setLastName(resultSet.getString("account_last_name"));
                     account.setEmail(resultSet.getString("account_email"));
                     account.setPhone(resultSet.getString("account_phone"));

                     Blob imgBlob = resultSet.getBlob("account_image");
                     if (imgBlob != null) {
                         account.setBase64Image(getBase64Image(imgBlob));
                     } else {
                         account.setBase64Image(null);
                     }
                 }
             }
         }
     } catch (ClassNotFoundException | SQLException | IOException e) {
         System.out.println("AccountDao query error: " + e.getMessage());
     }
     return account;
 }

 public Account getAccount(int accountId) {
     String query = "SELECT * FROM account WHERE account_id = ?";
     return queryGetAccount(query, accountId);
 }

 public Account checkLoginAccount(String username, String password) {
     String query = "SELECT * FROM account WHERE account_name = ? AND account_password = ?";
     return queryGetAccount(query, username, password);
 }

 public boolean checkUsernameExists(String username) {
     String query = "SELECT * FROM account WHERE account_name = ?";
     return queryGetAccount(query, username) != null;
 }

 public void createAccount(String username, String password, InputStream image) {
     String query = "INSERT INTO account (account_name, account_password, account_image, account_is_seller, account_is_admin) VALUES (?, ?, ?, 0, 0)";
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         try (Connection connection = new Database().getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setString(1, username);
             preparedStatement.setString(2, password);
             if (image != null) {
                 preparedStatement.setBlob(3, image);
             } else {
                 preparedStatement.setNull(3, Types.BLOB);
             }
             preparedStatement.executeUpdate();
         }
     } catch (ClassNotFoundException | SQLException e) {
         System.out.println("Create account error: " + e.getMessage());
     }
 }

 public void editProfileInformation(int accountId, String firstName, String lastName, String address,
                                    String email, String phone, InputStream image) {
     String query = "UPDATE account SET account_first_name = ?, account_last_name = ?, account_address = ?, account_email = ?, account_phone = ?, account_image = ? WHERE account_id = ?";
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         try (Connection connection = new Database().getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setString(1, firstName);
             preparedStatement.setString(2, lastName);
             preparedStatement.setString(3, address);
             preparedStatement.setString(4, email);
             preparedStatement.setString(5, phone);
             preparedStatement.setBlob(6, image);
             preparedStatement.setInt(7, accountId);
             preparedStatement.executeUpdate();
         }
     } catch (ClassNotFoundException | SQLException e) {
         System.out.println("Edit profile error: " + e.getMessage());
     }
 }

 public void updateProfileInformation(int accountId, String firstName, String lastName, String address,
                                      String email, String phone) {
     String query = "UPDATE account SET account_first_name = ?, account_last_name = ?, account_address = ?, account_email = ?, account_phone = ? WHERE account_id = ?";
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         try (Connection connection = new Database().getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setString(1, firstName);
             preparedStatement.setString(2, lastName);
             preparedStatement.setString(3, address);
             preparedStatement.setString(4, email);
             preparedStatement.setString(5, phone);
             preparedStatement.setInt(6, accountId);
             preparedStatement.executeUpdate();
         }
     } catch (ClassNotFoundException | SQLException e) {
         System.out.println("Update profile error: " + e.getMessage());
     }
 }
}
