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

public class ItemRequestView extends BorderPane {
	
	Stage stage;
	Scene scene;
	User user;
	GridPane gp;
	
	TableView<Item> itemTable;
	TableColumn<Item, String> itemName;
	TableColumn<Item, String> itemCategory;
	TableColumn<Item, String> itemSize;
	TableColumn<Item, Integer> itemPrice;
	
	Button approve, decline;
	
	private void pendingItemTable() {
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
		viewPendingItems();
	}
	
	private void viewPendingItems() {
		itemTable.refresh();
		ArrayList<Item> items = ItemController.getAllPendingItems();
		ObservableList<Item> itemOL = FXCollections.observableArrayList(items);
		itemTable.setItems(itemOL);
	}
	
	private void init() {
		// TODO Auto-generated method stub
		pendingItemTable();
		gp = new GridPane();
		scene = new Scene(this, 700, 500);
		this.setTop(itemTable);
		this.setCenter(gp);
		
		approve = new Button("Approve Item");
		decline = new Button("Decline Item");
		gp.add(approve, 0, 0);
		gp.add(decline, 1, 0);
		
		approve.setOnAction(e -> {
			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
			ItemController.ApproveItem(selectedItem);
			new HomeView(stage, user);
		});
		
		decline.setOnAction(e -> {
			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
			ItemController.DeclineItem(selectedItem);
			new HomeView(stage, user);
		});
	}
	
	public ItemRequestView(Stage stage, User user) {
		super();
		this.stage = stage;
		this.user = user;
		init();
		stage.setScene(scene);
		stage.setTitle("Item Requests");
		stage.show();
	}
	
	
}
