package view;

import controller.ItemController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Item;
import model.User;

public class MakeOfferView extends BorderPane{
	Stage stage;
	Scene scene;
	User user;
	Item item;
	
	GridPane gp;
	
	Label itemNameLbl, currentPriceLbl, currentOfferLbl, yourOffer, descriptionText;
	TextField offerField;
	Button makeOfferBtn;
	
	private void init() {
		// TODO Auto-generated method stub
		scene = new Scene(this, 700, 500);
		gp = new GridPane();
		this.setCenter(gp);
		
		itemNameLbl = new Label("Item name: " + item.getItem_name());
		currentPriceLbl = new Label("Current item price: " + item.getItem_price());
		currentOfferLbl = new Label("Current Existing offer: " + item.getOffer_price());
		yourOffer = new Label("Your offer: ");
		descriptionText = new Label("Offer must be higher than current offer");
		offerField = new TextField();
		makeOfferBtn = new Button("Make Offer");
		
		gp.add(itemNameLbl, 0, 0);
		gp.add(currentPriceLbl, 0, 1);
		gp.add(currentOfferLbl, 0, 2);
		gp.add(descriptionText, 0, 3);
		gp.add(yourOffer, 0, 4);
		gp.add(offerField, 1, 4);
		gp.add(makeOfferBtn, 0, 5);
		
		makeOfferBtn.setOnAction(e -> {
			int offerPrice = Integer.parseInt(offerField.getText());
			ItemController.makeOffer(item, offerPrice);
			new HomeView(stage, user);
		});
	}

	public MakeOfferView(Stage stage, User user, Item item) {
		super();
		this.stage = stage;
		this.user = user;
		this.item = item;
		init();
		stage.setScene(scene);
		stage.setTitle("Make Offer");
		stage.show();
	}
	
}


