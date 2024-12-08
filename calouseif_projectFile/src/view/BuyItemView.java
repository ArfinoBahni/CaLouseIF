package view;

import controller.ItemController;
import controller.TransactionController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Item;
import model.User;

public class BuyItemView extends BorderPane{
	
	Stage stage;
	Scene scene;
	User user;
	Item item;
	GridPane gp;
	
	Label nameLbl, categoryLbl, sizeLbl, priceLbl, confirmationText;
	Button confirmBtn, cancelBtn;
	
	
	private void init() {
		// TODO Auto-generated method stub
		gp = new GridPane();
		scene = new Scene(this, 700, 500);
		this.setCenter(gp);
		
		nameLbl = new Label("Item name: " + item.getItem_name());
		categoryLbl = new Label("Item category: " + item.getItem_category());
		sizeLbl = new Label("Item size: " + item.getItem_size());
		priceLbl = new Label("Item name: " + item.getItem_price());
		confirmationText = new Label("Confirm this item purchase?");
		confirmBtn = new Button("Confirm Purchase");
		cancelBtn = new Button("Cancel Purchase");
		
		gp.add(nameLbl, 0, 0);
		gp.add(categoryLbl, 0, 1);
		gp.add(sizeLbl, 0, 2);
		gp.add(priceLbl, 0, 3);
		gp.add(confirmationText, 0, 4);
		gp.add(confirmBtn, 0, 5);
		gp.add(cancelBtn, 1, 5);
		
		confirmBtn.setOnAction(e -> {
			TransactionController.purchaseItem(user.getUserId(), item);
			new HomeView(stage, user);
		});
		
		cancelBtn.setOnAction(e -> {
			new HomeView(stage, user);
		});
	}
	
	public BuyItemView(Stage stage, User user, Item item) {
		super();
		this.stage = stage;
		this.user = user;
		this.item = item;
		init();
		stage.setTitle("Confirm item purchase");
		stage.setScene(scene);
		stage.show();
	}
	
}
