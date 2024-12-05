package controller;

import java.util.ArrayList;

import model.Item;

public class ItemController {
	
	public static ArrayList<Item> getAllApprovedItems() {
		return Item.getAllApprovedItems();
	}
	
}
