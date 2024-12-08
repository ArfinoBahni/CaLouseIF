package view;

import java.util.ArrayList;

import controller.ItemController;
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

public class HomeView extends BorderPane{
	
	Stage stage;
	User user;
	GridPane gp, gp2;
	Scene scene;
	TableView<Item> itemTable;
	TableColumn<Item, String> itemName;
	TableColumn<Item, String> itemCategory;
	TableColumn<Item, String> itemSize;
	TableColumn<Item, Integer> itemPrice;
	
//	seller buttons
	Button uploadItemBtn, editItemBtn, deleteItemBtn, offersBtn;
	
//	admin buttons
	Button itemRequests;
	
//	buyer buttons
	Button buyBtn, addWishlistBtn, wishlistPageBtn, makeOfferBtn, historyBtn;
	
	
	public void initializeSeller(User user) {
		approvedItemTable();
		
		gp = new GridPane();
		scene = new Scene(this, 700, 500);
		this.setTop(itemTable);
		this.setCenter(gp);
		
		uploadItemBtn = new Button("Upload Item");
		editItemBtn = new Button("Edit Item");
		deleteItemBtn = new Button("Delete Item");
		offersBtn = new Button("Offers");
		
		gp.add(uploadItemBtn, 0, 0);
		gp.add(editItemBtn, 1, 0);
		gp.add(deleteItemBtn, 2, 0);
		gp.add(offersBtn, 3, 0);
		
		uploadItemBtn.setOnAction(e -> {
//			System.out.println("Button click");
			new UploadItemView(stage, user);
		});
		
		editItemBtn.setOnAction(e -> {
			new EditItemView(stage, user);
		});
		
		deleteItemBtn.setOnAction(e -> {
			new DeleteItemView(stage, user);
		});
		
		offersBtn.setOnAction(e -> {
			new SellerOffersView(stage, user);
		});
	}
	
	public void initializeBuyer(User user) {
		approvedItemTable();
		
		gp = new GridPane();
		scene = new Scene(this, 700, 500);
		this.setTop(itemTable);
		this.setCenter(gp);
		
		buyBtn = new Button("Buy Item");
		makeOfferBtn = new Button("Make Offer");
		addWishlistBtn = new Button("Add to wishlist");
		wishlistPageBtn = new Button("View my wishlist");
		historyBtn = new Button("Purchase history");
		
		gp.add(buyBtn, 0, 0);
		gp.add(makeOfferBtn, 1, 0);
		gp.add(addWishlistBtn, 2, 0);
		gp.add(wishlistPageBtn, 3, 0);
		gp.add(historyBtn, 4, 0);
		
		buyBtn.setOnAction(e -> {
			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
			if(selectedItem == null) System.out.println("No item selected");
			else new BuyItemView(stage, user, selectedItem);
		});
		
		historyBtn.setOnAction(e -> {
			new PurchaseHistoryPage(stage, user);
		});
		
		makeOfferBtn.setOnAction(e -> {
			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
			if(selectedItem == null) System.out.println("No item selected");
			else new MakeOfferView(stage, user, selectedItem);
		});
	}
	
	public void initializeAdmin(User user) {
		approvedItemTable();
		
		gp = new GridPane();
		scene = new Scene(this, 700, 500);
		this.setTop(itemTable);
		this.setCenter(gp);
		
		itemRequests = new Button("Item requests");
		
		gp.add(itemRequests, 0, 0);
		
		itemRequests.setOnAction(e -> {
			new ItemRequestView(stage, user);
		});
	}
	
	private void approvedItemTable() {
		// TODO Auto-generated method stub
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
		viewAllItems();
	}
	
	private void viewAllItems() {
		itemTable.refresh();
		ArrayList<Item> items = ItemController.getAllApprovedItems();
		ObservableList<Item> itemOL = FXCollections.observableArrayList(items);
		itemTable.setItems(itemOL);
	}

	public HomeView(Stage stage, User user) {
		this.stage = stage;
		this.user = user;
		String role = user.getRole();
		
		// init and stuff
		if(role.equals("Buyer")) {
//			roleLabel = new Label(role);
			initializeBuyer(user);
			
		}
		if(role.equals("Seller")) {
//			roleLabel = new Label(role);
			initializeSeller(user);		
		}
		if(role.equals("admin")) {
//			roleLabel = new Label(role);
			initializeAdmin(user);
		}

		stage.setTitle("Home");
		stage.setScene(scene);
		stage.show();
	}
}
