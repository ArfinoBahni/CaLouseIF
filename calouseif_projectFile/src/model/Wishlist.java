package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Connect;

public class Wishlist {
	private int wishlist_id, user_id, item_id;

	public Wishlist(int wishlist_id, int user_id, int item_id) {
		super();
		this.wishlist_id = wishlist_id;
		this.user_id = user_id;
		this.item_id = item_id;
	}

	public int getWishlist_id() {
		return wishlist_id;
	}

	public void setWishlist_id(int wishlist_id) {
		this.wishlist_id = wishlist_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	
	public static void addToWishlist(int user_id, int item_id) {
		Connect con = Connect.getInstance();
		String query = "INSERT INTO wishlist (user_id, item_id) VALUES (?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, user_id);
			ps.setInt(2, item_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Item> getWishlist(int userId) {
		ArrayList<Item> i = new ArrayList<Item>();
		Connect con = Connect.getInstance();
		String query = "SELECT items.item_id, items.item_name, items.item_category, items.item_size, items.item_price "
				+ "FROM wishlist JOIN items ON wishlist.item_id=items.item_id WHERE wishlist.user_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int wishlistId = rs.getInt("item_id");
				String itemName = rs.getString("item_name");
				String itemCategory = rs.getString("item_category");
				String itemSize = rs.getString("item_size");
				int itemPrice = rs.getInt("item_price");
				i.add(new Item(wishlistId,itemName, itemCategory, itemSize, itemPrice));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	//delete wishlist for all (when item is purchased)
	public static void deleteWishlistAll(int itemId) {
		Connect con = Connect.getInstance();
		String query = "DELETE FROM wishlist WHERE item_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, itemId);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// delete wishlist per user
	public static void deleteWishlistUser(int itemId, int user_id) {
		Connect con = Connect.getInstance();
		String query = "DELETE FROM wishlist WHERE item_id = ? AND user_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, itemId);
			ps.setInt(2, user_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
