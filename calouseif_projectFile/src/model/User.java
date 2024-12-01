package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Connect;

public class User {
	private static Connect con = Connect.getInstance();
	
	private int userId;
	private String username;
	private String password;
	private String phoneNumber;
	private String address;
	private String role;
	public User(int userId, String username, String password, String phoneNumber, String address, String role) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.role = role;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public static int register(String username, String password, String phoneNumber, String address, String role) {
		String query = "INSERT INTO users (username, password, phone_number, address, role) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		int result = 0;
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, phoneNumber);
			ps.setString(4, address);
			ps.setString(5, role);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static User login(String user_name, String user_password) {
		String query = "SELECT * FROM users WHERE username = ? and password = ?";
		PreparedStatement ps = con.prepareStatement(query);
		User user = null;
		try {
			ps.setString(1, user_name);
			ps.setString(2, user_password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String phoneNumber = rs.getString("phone_number");
				String address = rs.getString("address");
				String role = rs.getString("role");
				user = new User(id, username, password, phoneNumber, address, role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
}
