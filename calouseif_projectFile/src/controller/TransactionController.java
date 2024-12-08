package controller;

import java.util.ArrayList;

import model.Item;
import model.Transaction;

public class TransactionController {
	
	public static void purchaseItem(int UserId, Item item) {
		Transaction.purchaseItem(UserId, item.getItem_id());
//		DeclineItem supaya ngga muncul di table lagi (udh dibeli ceritanya)
		ItemController.DeclineItem(item);
	}
	
	public static void createTransaction(Item item) {
		Transaction.createTransaction(item);
//		DeclineItem supaya ngga muncul di table lagi (udh dibeli ceritanya)
		ItemController.DeclineItem(item);
	}
	
	public static ArrayList<Item> getPurchaseHistory(int userID) {
		return Transaction.getPurchaseHistory(userID);
	}
}
