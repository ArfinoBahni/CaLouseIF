package controller;

import java.util.ArrayList;

import model.Item;
import model.Wishlist;

public class WishlistContoller {
	public static void addToWishlist(int user_id, int item_id) {
		Wishlist.addToWishlist(user_id, item_id);
	}
	
	public static ArrayList<Item> getWishlist(int userId) {
		return Wishlist.getWishlist(userId);
	}
	
	public static void deleteWishlistAll(int itemId) {
		Wishlist.deleteWishlistAll(itemId);
	}
	
	public static void deleteWishlistUser(int itemId, int user_id) {
		Wishlist.deleteWishlistUser(itemId, user_id);
	}
}
