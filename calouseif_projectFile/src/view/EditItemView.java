package view;

import java.util.ArrayList;

import controller.ItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Item;
import model.User;

public class EditItemView extends BorderPane{

	
	Stage stage;
	User user;
	Scene scene;
	
	GridPane gp;
	Label nameLbl, categoryLbl, sizeLbl, priceLbl;
	TextField nameField, categoryField, sizeField, priceField;
	Button submitEditBtn;
	
	TableView<Item> itemTable;
	TableColumn<Item, String> itemName;
	TableColumn<Item, String> itemCategory;
	TableColumn<Item, String> itemSize;
	TableColumn<Item, Integer> itemPrice;
	
	int tempId;
	
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
		gp = new GridPane();
		scene = new Scene(this, 700, 600);
		this.setTop(itemTable);
		this.setCenter(gp);
		itemTable.setOnMouseClicked(tableClick());
		
		nameLbl = new Label("Item name");
		categoryLbl = new Label("Item category");
		sizeLbl = new Label("Size");
		priceLbl = new Label("Price");
		
		nameField = new TextField();
		categoryField = new TextField();
		sizeField = new TextField();
		priceField = new TextField();
		
		gp.add(nameLbl, 0, 0);
		gp.add(nameField, 1, 0);
		gp.add(categoryLbl, 0, 1);
		gp.add(categoryField, 1, 1);
		gp.add(sizeLbl, 0, 2);
		gp.add(sizeField, 1, 2);
		gp.add(priceLbl, 0, 3);
		gp.add(priceField, 1, 3);
		
		submitEditBtn = new Button("Submit Edit");
		gp.add(submitEditBtn, 0, 4);
		
		submitEditBtn.setOnAction(e -> {
			String name = nameField.getText();
			String category = categoryField.getText();
			String size = sizeField.getText();
			int price = Integer.parseInt(priceField.getText()); 
			ItemController.EditItem(name, category, size, price, tempId);
			new HomeView(stage, user);
		});
	}
	
	private EventHandler<MouseEvent> tableClick() {
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				TableSelectionModel<Item> tableSelectionModel = itemTable.getSelectionModel();
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
				Item item = tableSelectionModel.getSelectedItem();
				nameField.setText(item.getItem_name());
				categoryField.setText(item.getItem_category());
				sizeField.setText(item.getItem_size());
				priceField.setText(String.valueOf(item.getItem_price()));
				
				tempId = item.getItem_id();
			}
		};
	}
	
	public EditItemView(Stage stage, User user) {
		super();
		this.stage = stage;
		this.user = user;
		init();
		stage.setTitle("Edit Item");
		stage.setScene(scene);
		stage.show();
	}
	
	
}
