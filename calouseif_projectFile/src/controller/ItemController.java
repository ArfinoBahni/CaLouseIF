package controller;

import java.util.ArrayList;

import model.Item;

public class ItemController {
	
	public static ArrayList<Item> getAllApprovedItems() {
		return Item.getAllApprovedItems();
	}
	public static ArrayList<Item> getOfferedItems(int sellerId) {
		return Item.getOfferedItems(sellerId);
	}
	
	public static void UploadItem(int sellerId, String itemName, String itemCategory, String itemSize, int itemPrice) {
		if(itemName.length() < 3 || itemCategory.length() < 3 || itemSize.isEmpty() || itemSize.isEmpty() || itemPrice <= 0) {
			System.out.println("Invalid Input");
			return;
		}
		else {
			Item.UploadItem(sellerId, itemName, itemCategory, itemSize, itemPrice);
		}
	}
	
	public static void EditItem(String itemName, String itemCategory, String itemSize, int itemPrice, int itemId) {
		if(itemName.length() < 3 || itemCategory.length() < 3 || itemSize.isEmpty() || itemSize.isEmpty() || itemPrice <= 0) {
			System.out.println("Invalid Input");
			return;
		}
		else {
			Item.EditItem(itemName, itemCategory, itemSize, itemPrice, itemId);
		}
	}
	
	public static void DeleteItem(int itemId) {
		Item.DeleteItem(itemId);
	}
	
	public static ArrayList<Item> getAllPendingItems() {
		return Item.getAllPendingItems();
	}
	
	public static ArrayList<Item> getApprovedSellerItems(int sellerId) {
		return Item.getApprovedSellerItems(sellerId);
	}
	
	public static void ApproveItem(Item item) {
		if(item == null) {
			System.out.println("No item selected");
		}
		else {
			Item.ApproveItem(item);
		}
	}
	
	public static void DeclineItem(Item item) {
		if(item == null) {
			System.out.println("No item selected");
		}
		else {
			Item.DeclineItem(item);
		}
	}
	
	public static void DeclineOffer(Item item) {
		if(item == null) {
			System.out.println("No item selected");
		}
		else {
			Item.DeclineOffer(item);
		}
	}
	
	public static void AcceptOffer(Item item) {
		if(item == null) {
			System.out.println("No item selected");
		}
		else {
			Item.AcceptOffer(item);
			TransactionController.createTransaction(item);
		}
	}
	
	public static void makeOffer(Item item, int offerPrice) {
		if(offerPrice <= item.getOffer_price()) {
			System.out.println("Offer price cannot be lower than current offer price");
		}
		else {
			Item.makeOffer(item.getItem_id(), item.getOffering_user_id(), offerPrice);
		}
	}
	
	
}
