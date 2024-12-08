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

public class SellerOffersView extends BorderPane{
	Stage stage;
	User user;
	Scene scene;
	GridPane gp;
	
	TableView<Item> itemTable;
	TableColumn<Item, String> itemName;
	TableColumn<Item, String> itemCategory;
	TableColumn<Item, String> itemSize;
	TableColumn<Item, Integer> itemPrice;
	TableColumn<Item, Integer> offerPrice;
	
	Button accept, decline;
	
	private void offeredItemsTable() {
		itemTable = new TableView<Item>();
		itemName = new TableColumn<>("Item Name");
		itemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
		itemCategory = new TableColumn<>("Item Category");
		itemCategory.setCellValueFactory(new PropertyValueFactory<>("item_category"));
		itemSize = new TableColumn<>("Item Size");
		itemSize.setCellValueFactory(new PropertyValueFactory<>("item_size"));
		itemPrice = new TableColumn<>("Item Price");
		itemPrice.setCellValueFactory(new PropertyValueFactory<>("item_price"));
		offerPrice = new TableColumn<>("Offer Price");
		offerPrice.setCellValueFactory(new PropertyValueFactory<>("offer_price"));
		
		itemTable.getColumns().add(itemName);
		itemTable.getColumns().add(itemCategory);
		itemTable.getColumns().add(itemSize);
		itemTable.getColumns().add(itemPrice);
		itemTable.getColumns().add(offerPrice);
		viewSellerItems();
	}
	
	private void viewSellerItems() {
		ArrayList<Item> items = ItemController.getApprovedSellerItems(user.getUserId());
		ObservableList<Item> itemOL = FXCollections.observableArrayList(items);
		itemTable.setItems(itemOL);
		itemTable.refresh();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		offeredItemsTable();
		scene = new Scene(this, 700, 500);
		gp = new GridPane();
		this.setTop(itemTable);
		this.setCenter(gp);
		accept = new Button("Accept Offer");
		decline = new Button("Decline Offer");
		gp.add(accept, 0, 0);
		gp.add(decline, 1, 0);
		
		accept.setOnAction(e -> {
			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
			ItemController.AcceptOffer(selectedItem);
			new HomeView(stage, user);
		});
		
		decline.setOnAction(e -> {
			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
			ItemController.DeclineOffer(selectedItem);
			new HomeView(stage, user);
		});
	}

	public SellerOffersView(Stage stage, User user) {
		super();
		this.stage = stage;
		this.user = user;
		init();
		stage.setScene(scene);
		stage.setTitle("Available offers");
		stage.show();
	}
	
	
}
