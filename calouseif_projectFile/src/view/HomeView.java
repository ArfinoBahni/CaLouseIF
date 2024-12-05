package view;

import java.util.ArrayList;

import controller.ItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
	GridPane gp;
	Scene scene;
	Label roleLabel;
	TableView<Item> itemTable;
	TableColumn<Item, String> itemName;
	TableColumn<Item, String> itemCategory;
	TableColumn<Item, String> itemSize;
	TableColumn<Item, Integer> itemPrice;
	
	
	
	public void initialize() {
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
		
		gp = new GridPane();
		scene = new Scene(this, 700, 500);
		this.setCenter(gp);
		
		gp.add(roleLabel, 1, 1);
		gp.add(itemTable, 0, 0);
	}
	
	private void viewAllItems() {
		ArrayList<Item> items = ItemController.getAllApprovedItems();
		ObservableList<Item> itemOL = FXCollections.observableArrayList(items);
		itemTable.setItems(itemOL);
	}

	public HomeView(Stage stage, User user) {
		this.stage = stage;
		String role = user.getRole();
		
		if(role.equals("Buyer")) {
			roleLabel = new Label(role);
		}
		if(role.equals("Seller")) {
			
		}
		if(role.equals("admin")) {
			
		}
		
		initialize();
		// init and stuff
		stage.setTitle("Home");
		stage.setScene(scene);
		stage.show();
	}
}
