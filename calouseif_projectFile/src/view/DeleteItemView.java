package view;

import java.util.ArrayList;

import controller.ItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Item;
import model.User;

public class DeleteItemView extends BorderPane{
	Stage stage;
	User user;
	Scene scene;
	GridPane gp;
	
	Label headerLbl;
	Button deleteItem, backBtn;
	
	TableView<Item> itemTable;
	TableColumn<Item, String> itemName;
	TableColumn<Item, String> itemCategory;
	TableColumn<Item, String> itemSize;
	TableColumn<Item, Integer> itemPrice;
	
	
	private void approvedSellerItemTable() {
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
		approvedSellerItemTable();
		scene = new Scene(this, 700, 500);
		gp = new GridPane();
		this.setTop(itemTable);
		this.setCenter(gp);
		
		headerLbl = new Label("Select an item to delete then press delete button");
		deleteItem = new Button("Delete Item");
		
		gp.add(headerLbl, 0, 0);
		gp.add(deleteItem, 0, 1);
		
		deleteItem.setOnAction(e -> {
			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
			if (selectedItem == null) System.out.println("No item selected");
			else ItemController.DeleteItem(selectedItem.getItem_id());
			new HomeView(stage, user);
		});
		
		backBtn = new Button("Back to home");
		gp.add(backBtn, 1, 1);
		backBtn.setOnAction(e -> {
			new HomeView(stage, user);
		});
	}
	
	public DeleteItemView(Stage stage, User user) {
		super();
		this.stage = stage;
		this.user = user;
		init();
		stage.setScene(scene);
		stage.setTitle("Delete Item");
		stage.show();
	}
}
