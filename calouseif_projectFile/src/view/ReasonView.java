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

public class ReasonView extends BorderPane{
	Stage stage;
	Scene scene;
	User user;
	Item item;
	GridPane gp;
	TextField reasonField;
	Button submit, back;
	Label descriptionLabel;
	
	
	private void init() {
		gp = new GridPane();
		scene = new Scene(this, 700, 500);
		this.setCenter(gp);
		
		descriptionLabel = new Label("Why do you want to decline?");
		reasonField = new TextField();
		submit = new Button("Decline item");
		back = new Button("Back to home");
		
		gp.add(descriptionLabel, 0, 0);
		gp.add(reasonField, 1, 0);
		gp.add(submit, 0, 1);
		gp.add(back, 1, 1);
		
		submit.setOnAction(e -> {
			String reason = reasonField.getText();
			if (reason.isEmpty()) {
				System.out.println("Must fill in reason");
			}
			else if(user.getRole().equals("admin")) {
				reason = "Deny request reason: " + reason;
				ItemController.DeclineItem(item);
				ItemController.addReason(item.getItem_id(), reason);
			}
			else if(user.getRole().equals("Seller")) {
				reason = "Deny offer reason: " + reason;
				ItemController.DeclineOffer(item);
				ItemController.addReason(item.getItem_id(), reason);
			}
			new HomeView(stage, user);
		});
		
		back.setOnAction(e -> {
			new HomeView(stage, user);
		});
		
	}
	
	public ReasonView(Stage stage, User user, Item item) {
		super();
		this.stage = stage;
		this.user = user;
		this.item = item;
		init();
		stage.setTitle("Decline Reasoning");
		stage.setScene(scene);
		stage.show();
	}
	
	
}
