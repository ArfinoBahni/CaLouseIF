package controller;

import model.User;

public class UserController {
	
	public static String register(String username, String password, String phoneNumber, String address, String role) {
//		validation belom lengkap 
		if (username.length() < 3 || password.length() < 8 ||
		            !phoneNumber.contains("+62") || phoneNumber.length() != 12 ||
		            address.isEmpty() || role == null) {
		            return "Invalid input";
		        }
		 int result = User.register(username, password, phoneNumber, address, role);
		 if (result == 0) {
			 return "Register Failed";
		 }
		 return "Register Success";
	}
	
	public static User login(String username, String password) {
		return User.login(username, password);
	}

}
