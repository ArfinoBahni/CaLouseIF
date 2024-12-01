package view;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.Connect;

public class RegisterView {
	
	Scene scene;
	BorderPane borderContainer;
	HBox horizontalContainer;
	GridPane gridContainer;
	FlowPane flowContainer;
	
	Text titleText;
	Button registerButton, loginRedirectBtn;
	Label usernameLabel, passwordLabel, phoneNumberLabel, addressLabel, roleLabel;
	TextField usernameField, phoneNumberField, addressField, roleField;
	PasswordField passwordField;
	ToggleGroup roleGroup;
	RadioButton buyerRadioButton, sellerRadioButton;
	
	//styling
	private void setStyles() {
		borderContainer.setAlignment(titleText, Pos.CENTER);
		borderContainer.setAlignment(registerButton, Pos.CENTER);
		borderContainer.setPadding(new Insets(16));
		
		gridContainer.setAlignment(Pos.CENTER);
		gridContainer.setVgap(20);
		gridContainer.setHgap(20);
		horizontalContainer.setSpacing(10);
		flowContainer.setHgap(20);
		
		usernameField.setPromptText("username");
		passwordField.setPromptText("Password");
		
		titleText.setFont(new Font("calibri", 16));
//		registerButton.setStyle("-fx-background-color: yellow");
		
		borderContainer.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, null, null)));

	}
	
	//intialize content of scene
	private void initialize() {
		borderContainer = new BorderPane();
		horizontalContainer = new HBox();
		gridContainer = new GridPane();
		flowContainer = new FlowPane();
		
		// Parameter scene => (Node, width, height)
		scene = new Scene(borderContainer, 700, 500);
		
		//init components
		titleText = new Text("Register now");
		registerButton = new Button("Register");
		loginRedirectBtn = new Button("Login");
		
		usernameLabel = new Label("Username");
		passwordLabel = new Label("Password");
		phoneNumberLabel = new Label("Phone Number");
		addressLabel = new Label("Address");
		roleLabel = new Label("Role");
		
		usernameField = new TextField();
		passwordField = new PasswordField();
		phoneNumberField = new TextField();
		addressField = new TextField();
		
		roleGroup = new ToggleGroup();
		buyerRadioButton = new RadioButton("Buyer");
		buyerRadioButton.setToggleGroup(roleGroup);
		sellerRadioButton = new RadioButton("Seller");
		sellerRadioButton.setToggleGroup(roleGroup);
		
		
		//set component to scene
		borderContainer.setTop(titleText);
		borderContainer.setBottom(registerButton);
		borderContainer.setCenter(gridContainer);
		
		//index i,j (i column index, j row index)
		//set labels
		gridContainer.add(usernameLabel, 0, 0);
		gridContainer.add(passwordLabel, 0, 1);
		gridContainer.add(phoneNumberLabel, 0, 2);
		gridContainer.add(addressLabel, 0, 3);
		gridContainer.add(roleLabel, 0, 4);
		gridContainer.add(loginRedirectBtn, 0, 5);
		
		//set fields
		gridContainer.add(usernameField, 1, 0);
		gridContainer.add(passwordField, 1, 1);
		gridContainer.add(phoneNumberField, 1, 2);
		gridContainer.add(addressField, 1, 3);		
		
		//set radio button
		flowContainer.getChildren().addAll(buyerRadioButton, sellerRadioButton);
		gridContainer.add(flowContainer, 1, 4);
	}
	
	public RegisterView (Stage stage) {
		initialize();
		setStyles();
		setActions(stage);
		stage.setTitle("Register");
		stage.setScene(scene);
		stage.show();
	}
	
	public void setActions(Stage stage) {
		registerButton.setOnAction((EventHandler<ActionEvent>) e -> {
			handleRegister();
			if(handleRegister()) new LoginView(stage);
		});
		loginRedirectBtn.setOnAction(e -> {
			new LoginView(stage);
		});
	}
	
	
	 private boolean handleRegister() {
	        RadioButton selectedRadioButton = (RadioButton) roleGroup.getSelectedToggle();
	        String toggleGroupValue = selectedRadioButton != null ? selectedRadioButton.getText() : null;
	        String username = usernameField.getText();
	        String password = passwordField.getText();
	        String phoneNumber = phoneNumberField.getText();
	        String address = addressField.getText();
//	        System.out.println(username + password + phoneNumber + address + toggleGroupValue);
	        String message = UserController.register(username, password, phoneNumber, address, toggleGroupValue);
	        System.out.println(message);
	        
	        if (message.equals("Invalid input") || message.equals("Register Failed")) return false;
	        return true;
	    }

	
//	@Override
//	public void handle(ActionEvent event) {
//		// TODO Auto-generated method stub
//		if(event.getSource() == registerButton) {
//			handleRegister();
//		}
//	}
	
}
