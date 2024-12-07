package view;

import controller.ItemController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;

public class UploadItemView extends BorderPane{
	
	Stage stage;
	User user;
	Scene scene;
	
	GridPane gp;
	Label nameLbl, categoryLbl, sizeLbl, priceLbl;
	TextField nameField, categoryField, sizeField, priceField;
	Button uploadBtn;
	
	
	private void init() {
		gp = new GridPane();
		scene = new Scene(this, 700, 500);
		
		nameLbl = new Label("Item name");
		categoryLbl = new Label("Item category");
		sizeLbl = new Label("Size");
		priceLbl = new Label("Price");
		
		nameField = new TextField();
		categoryField = new TextField();
		sizeField = new TextField();
		priceField = new TextField();
		
		this.setCenter(gp);
		
		gp.add(nameLbl, 0, 0);
		gp.add(nameField, 1, 0);
		gp.add(categoryLbl, 0, 1);
		gp.add(categoryField, 1, 1);
		gp.add(sizeLbl, 0, 2);
		gp.add(sizeField, 1, 2);
		gp.add(priceLbl, 0, 3);
		gp.add(priceField, 1, 3);
		
		uploadBtn = new Button("Upload Button");
		gp.add(uploadBtn, 0, 4);
		
		uploadBtn.setOnAction(e -> {
			String name = nameField.getText();
			String category = categoryField.getText();
			String size = sizeField.getText();
			int price = Integer.parseInt(priceField.getText());
			ItemController.UploadItem(user.getUserId(), name, category, size, price);
			new HomeView(stage, user);
		});
		
	}
	
	public UploadItemView(Stage stage, User user) {
		super();
		this.stage = stage;
		this.user = user;
		init();
		stage.setScene(scene);
		stage.setTitle("Upload Item");
		stage.show();
	}
	
	
	
}
