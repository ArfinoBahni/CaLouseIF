package controller;

import java.util.ArrayList;

import model.Item;
import model.Transaction;

public class TransactionController {
	
	public static void PurchaseItem(int UserId, int ItemId) {
		Transaction.PurchaseItem(UserId, ItemId);
	}
	
	public static ArrayList<Item> getPurchaseHistory(int userID) {
		return Transaction.getPurchaseHistory(userID);
	}
}
