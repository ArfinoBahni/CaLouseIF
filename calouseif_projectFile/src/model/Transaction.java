package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Connect;

public class Transaction {
	private int transaction_id;
	private int item_id;
	private int buyer_id;
	public Transaction(int transaction_id, int item_id, int buyer_id) {
		super();
		this.transaction_id = transaction_id;
		this.item_id = item_id;
		this.buyer_id = buyer_id;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
	}
	
	public static void PurchaseItem(int UserId, int ItemId) {
		Connect con = Connect.getInstance();
		String query = "INSERT INTO transactions (buyer_id, item_id) VALUES (?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, UserId);
			ps.setInt(2, ItemId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static ArrayList<Item> getPurchaseHistory(int userID) {
		ArrayList<Item> i = new ArrayList<Item>();
		Connect con = Connect.getInstance();
		String query = "SELECT items.item_id, items.item_name, items.item_category, items.item_size, items.item_price "
				+ "FROM items JOIN transactions ON transactions.item_id=items.item_id WHERE transactions.buyer_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int itemId = rs.getInt("item_id");
				int sellerId = rs.getInt("seller_id");
				String itemName = rs.getString("item_name");
				String itemCategory = rs.getString("item_category");
				String itemSize = rs.getString("item_size");
				int itemPrice = rs.getInt("item_price");
				String itemStatus = rs.getString("status");
				int buyerId = rs.getInt("buyer_id");
				i.add(new Item(itemId, sellerId, itemName, itemCategory, itemSize, itemPrice, itemStatus, buyerId));
;			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}