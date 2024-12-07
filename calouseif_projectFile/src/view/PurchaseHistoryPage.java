package view;

import java.util.ArrayList;

import controller.ItemController;
import controller.TransactionController;
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
import model.Transaction;
import model.User;

public class PurchaseHistoryPage extends BorderPane{
	Stage stage;
	Scene scene;
	User user;
	Item item;
	Transaction transaction;
	GridPane gp;
	
	TableView<Item> itemTable;
	TableColumn<Item, Integer> itemId;
	TableColumn<Item, String> itemName;
	TableColumn<Item, String> itemCategory;
	TableColumn<Item, String> itemSize;
	TableColumn<Item, Integer> itemPrice;
	
	Button backBtn;
	
	private void HistoryItemTable() {
		itemTable = new TableView<Item>();
		itemId = new TableColumn<>("Item Id");
		itemId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
		itemName = new TableColumn<>("Item Name");
		itemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
		itemCategory = new TableColumn<>("Item Category");
		itemCategory.setCellValueFactory(new PropertyValueFactory<>("item_category"));
		itemSize = new TableColumn<>("Item Size");
		itemSize.setCellValueFactory(new PropertyValueFactory<>("item_size"));
		itemPrice = new TableColumn<>("Item Price");
		itemPrice.setCellValueFactory(new PropertyValueFactory<>("item_price"));
		
		itemTable.getColumns().add(itemId);
		itemTable.getColumns().add(itemName);
		itemTable.getColumns().add(itemCategory);
		itemTable.getColumns().add(itemSize);
		itemTable.getColumns().add(itemPrice);
		viewPurchaseHistory();
	}
	
	private void viewPurchaseHistory() {
		itemTable.refresh();
		ArrayList<Item> items = TransactionController.getPurchaseHistory(user.getUserId());
		ObservableList<Item> itemOL = FXCollections.observableArrayList(items);
		itemTable.setItems(itemOL);
	}
	
	private void init() {
		// TODO Auto-generated method stub
		HistoryItemTable();
		gp = new GridPane();
		scene = new Scene(this, 700, 500);
		this.setTop(itemTable);
		this.setCenter(gp);
		
		backBtn = new Button("Back to home page");
		gp.add(backBtn, 0, 0);
	}
	
	public PurchaseHistoryPage(Stage stage, User user) {
		super();
		this.stage = stage;
		this.user = user;
		init();
		stage.setTitle("Purchase History");
		stage.setScene(scene);
		stage.show();
	}
	
	
}
