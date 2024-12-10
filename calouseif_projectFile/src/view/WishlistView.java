package view;

import java.util.ArrayList;

import controller.TransactionController;
import controller.WishlistContoller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Item;
import model.User;
import model.Wishlist;

public class WishlistView extends BorderPane{
	Stage stage;
	Scene scene;
	User user;
	Item item;
	Wishlist wl;
	
	GridPane gp;
	
	TableView<Item> itemTable;
	TableColumn<Item, String> itemName;
	TableColumn<Item, String> itemCategory;
	TableColumn<Item, String> itemSize;
	TableColumn<Item, Integer> itemPrice;
	
	Button backBtn,removeWishlist;
	
	private void WishlistTable() {
		itemTable = new TableView<Item>();
		itemName = new TableColumn<>("Item Name");
		itemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
		itemCategory = new TableColumn<>("Item Category");
		itemCategory.setCellValueFactory(new PropertyValueFactory<>("item_category"));
		itemSize = new TableColumn<>("Item Size");
		itemSize.setCellValueFactory(new PropertyValueFactory<>("item_size"));
		itemPrice = new TableColumn<>("Item Price");
		itemPrice.setCellValueFactory(new PropertyValueFactory<>("item_price"));
		
		itemTable.getColumns().add(itemName);
		itemTable.getColumns().add(itemCategory);
		itemTable.getColumns().add(itemSize);
		itemTable.getColumns().add(itemPrice);
		viewPurchaseHistory();
	}
	
	private void viewPurchaseHistory() {
		itemTable.refresh();
		ArrayList<Item> items = WishlistContoller.getWishlist(user.getUserId());
		ObservableList<Item> itemOL = FXCollections.observableArrayList(items);
		itemTable.setItems(itemOL);
	}
	
	private void init() {
		// TODO Auto-generated method stub
		WishlistTable();
		gp = new GridPane();
		scene = new Scene(this, 700, 500);
		this.setTop(itemTable);
		this.setCenter(gp);
		
		backBtn = new Button("Back to home page");
		removeWishlist = new Button("Remove from wishlist");
		gp.add(removeWishlist, 0, 0);
		gp.add(backBtn, 0, 1);
		
		backBtn.setOnAction(e -> {
			new HomeView(stage, user);
		});
		
		removeWishlist.setOnAction(e -> {
			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
			System.out.println(selectedItem.getItem_id() + " " + user.getUserId());
			if(selectedItem == null) System.out.println("No item selected");
			else WishlistContoller.deleteWishlistUser(selectedItem.getItem_id(), user.getUserId());
			new HomeView(stage, user);
		});
	}
	
	public WishlistView(Stage stage, User user) {
		super();
		this.stage = stage;
		this.user = user;
		init();
		stage.setTitle("Wishlist");
		stage.setScene(scene);
		stage.show();
	}

	
	
}
