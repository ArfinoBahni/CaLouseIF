package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Connect;

public class Item {
//	private static Connect con = Connect.getInstance();
	
	private int item_id;
	private int seller_id;
	private String item_name;
	private String item_category;
	private String item_size;
	private int item_price;
	private String item_status;
	public Item(int item_id, int seller_id, String item_name, String item_category, String item_size, int item_price,
			String item_status) {
		super();
		this.item_id = item_id;
		this.seller_id = seller_id;
		this.item_name = item_name;
		this.item_category = item_category;
		this.item_size = item_size;
		this.item_price = item_price;
		this.item_status = item_status;
	}
	
	public int getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_category() {
		return item_category;
	}
	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	public String getItem_size() {
		return item_size;
	}
	public void setItem_size(String item_size) {
		this.item_size = item_size;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	public String getItem_status() {
		return item_status;
	}
	public void setItem_status(String item_status) {
		this.item_status = item_status;
	}
	
	public static ArrayList<Item> getAllApprovedItems() {
		ArrayList<Item> i = new ArrayList<Item>();
		Connect con = Connect.getInstance();
		String query = "SELECT * FROM items WHERE STATUS = 'approved'";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int itemId = rs.getInt("item_id");
				int sellerId = rs.getInt("seller_id");
				String itemName = rs.getString("item_name");
				String itemCategory = rs.getString("item_category");
				String itemSize = rs.getString("item_size");
				int itemPrice = rs.getInt("item_price");
				String itemStatus = rs.getString("status");
				i.add(new Item(itemId, sellerId, itemName, itemCategory, itemSize, itemPrice, itemStatus));
;			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public static ArrayList<Item> getApprovedSellerItems(int sellerId) {
		ArrayList<Item> i = new ArrayList<Item>();
		Connect con = Connect.getInstance();
		String query = "SELECT * FROM items WHERE STATUS = 'approved' AND seller_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, sellerId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int itemId = rs.getInt("item_id");
				int seller_id = rs.getInt("seller_id");
				String itemName = rs.getString("item_name");
				String itemCategory = rs.getString("item_category");
				String itemSize = rs.getString("item_size");
				int itemPrice = rs.getInt("item_price");
				String itemStatus = rs.getString("status");
				i.add(new Item(itemId, seller_id, itemName, itemCategory, itemSize, itemPrice, itemStatus));
;			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public static void UploadItem(int sellerId, String itemName, String itemCategory, String itemSize, int itemPrice) {
		Connect con = Connect.getInstance();
		String query = "INSERT INTO items (seller_id, item_name, item_category, item_size, item_price) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, sellerId);
			ps.setString(2, itemName);
			ps.setString(3, itemCategory);
			ps.setString(4, itemSize);
			ps.setInt(5, itemPrice);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void EditItem(String itemName, String itemCategory, String itemSize, int itemPrice, int itemId) {
		Connect con = Connect.getInstance();
		String query = "UPDATE items SET item_name = ?, item_category = ?, item_size = ?, item_price = ? WHERE item_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setString(1, itemName);
			ps.setString(2, itemCategory);
			ps.setString(3, itemSize);
			ps.setInt(4, itemPrice);
			ps.setInt(5, itemId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void DeleteItem(int itemId) {
		Connect con = Connect.getInstance();
		String query = "DELETE FROM items WHERE item_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, itemId);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Item> getAllPendingItems() {
		ArrayList<Item> i = new ArrayList<Item>();
		Connect con = Connect.getInstance();
		String query = "SELECT * FROM items WHERE STATUS = 'pending'";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int itemId = rs.getInt("item_id");
				int sellerId = rs.getInt("seller_id");
				String itemName = rs.getString("item_name");
				String itemCategory = rs.getString("item_category");
				String itemSize = rs.getString("item_size");
				int itemPrice = rs.getInt("item_price");
				String itemStatus = rs.getString("status");
				i.add(new Item(itemId, sellerId, itemName, itemCategory, itemSize, itemPrice, itemStatus));
;			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public static void ApproveItem(Item item) {
		Connect con = Connect.getInstance();
		String query = "UPDATE items SET status = 'approved' WHERE item_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, item.item_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void DeclineItem(Item item) {
		Connect con = Connect.getInstance();
		String query = "UPDATE items SET status = 'declined' WHERE item_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, item.item_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
