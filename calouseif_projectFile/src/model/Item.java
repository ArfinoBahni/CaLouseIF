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
	
}
