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
import model.User;
import util.Connect;

public class LoginView implements EventHandler<ActionEvent> {
	
	Scene scene;
	BorderPane borderContainer;
	HBox horizontalContainer;
	GridPane gridContainer;
	FlowPane flowContainer;
	
	Text titleText;
	Button loginButton, registerRedirectBtn;
	Label usernameLabel, passwordLabel;
	TextField usernameField;
	PasswordField passwordField;
	
	//styling
	private void setStyles() {
		borderContainer.setAlignment(titleText, Pos.CENTER);
		borderContainer.setAlignment(loginButton, Pos.CENTER);
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
		titleText = new Text("Login");
		loginButton = new Button("Login");
		registerRedirectBtn = new Button("Register Here!");
		
		usernameLabel = new Label("Username");
		passwordLabel = new Label("Password");
		
		usernameField = new TextField();
		passwordField = new PasswordField();
		
		//set component to scene
		borderContainer.setTop(titleText);
		borderContainer.setBottom(loginButton);
		borderContainer.setCenter(gridContainer);
		
		//index i,j (i column index, j row index)
		//set labels
		gridContainer.add(usernameLabel, 0, 0);
		gridContainer.add(passwordLabel, 0, 1);
		gridContainer.add(registerRedirectBtn, 0, 3);
		
		//set fields
		gridContainer.add(usernameField, 1, 0);
		gridContainer.add(passwordField, 1, 1);	
		
		//set radio button
	}
	
	public LoginView (Stage stage) {
		initialize();
		setStyles();
		setActions(stage);
		stage.setTitle("Register");
		stage.setScene(scene);
		stage.show();
	}
	
	public void setActions(Stage stage) {
		loginButton.setOnAction(this);
		registerRedirectBtn.setOnAction(e -> {
			new RegisterView(stage);
		});
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == loginButton) {
			String username = usernameField.getText();
			String password = passwordField.getText();
			User user = UserController.login(username, password);
			if (user != null) System.out.println("User found");
			else {
				System.out.println("user not found");
			}
		}
	}
}
