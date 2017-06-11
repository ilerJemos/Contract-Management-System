package service;

import model.User;
import utils.AppException;

/**
 * User business logic class
 */
public class UserService {
	
	/**
	 * User registration
	 * @param user User object
	 * @return Return true if registration is successful, otherwise return false
	 * @throws AppException
	 */
	public boolean register(User user) throws AppException {
		boolean flag = false;// Define flag 
		
		// Hard-coding set jack as the existing user
		String existName = "jack";
		
		// 1.Verification of user for the same name does not exist
		if(!user.getName().equals(existName)){ 
			// 2.Save user information
			System.out.println("Business logic processing: Registration Successful!");
			flag = true;// Registration Successful
		} 
		
		return flag;
	}
}
