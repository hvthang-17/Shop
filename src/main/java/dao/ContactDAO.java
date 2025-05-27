package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.Contact;
import util.Database;

@Repository 
public class ContactDAO {

	public boolean insertContact(Contact contact) {
	    try (Connection connection = new Database().getConnection()) {
	        String sql = "INSERT INTO contacts (first_name, last_name, email, subject, message) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setString(1, contact.getFirstName());
	        ps.setString(2, contact.getLastName());
	        ps.setString(3, contact.getEmail());
	        ps.setString(4, contact.getSubject());
	        ps.setString(5, contact.getMessage());
	        int rows = ps.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public void deleteContact(int id) {
		String sql = "DELETE FROM contacts WHERE id = ?";
		try (Connection connection = new Database().getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<>();
		String sql = "SELECT id, first_name, last_name, email, subject, message, created_at FROM contacts";

		try (Connection connection = new Database().getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
			    Contact contact = new Contact();
			    contact.setId(rs.getInt("id"));
			    contact.setFirstName(rs.getString("first_name"));
			    contact.setLastName(rs.getString("last_name"));
			    contact.setEmail(rs.getString("email"));
			    contact.setSubject(rs.getString("subject"));
			    contact.setMessage(rs.getString("message"));
			    contact.setCreatedAt(rs.getTimestamp("created_at"));  
			    contactList.add(contact);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contactList;
	}
}
