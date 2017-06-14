package service;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import utils.AppException;

/**
 * User business logic class
 */
public class UserService {

	private UserDao userDao = null;// Define a userDao interface object

	/**
	 * No-arg constructor method is used to initialize userDao instance 
	 */
	public UserService() {
		userDao = new UserDaoImpl();
	}

	/**
	 *User registration
	 * @param user User object
	 * @return Return true if registration is successful, otherwise return false
	 * @throws AppException
	 */
	public boolean register(User user) throws AppException {
		boolean flag = false;// Define flag
		try {
			if (!userDao.isExist(user.getName())) {// Execute save operation when the user does not exist
				flag = userDao.add(user);// Return the operation result back to flag
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanko.service.UserService.register");
		}
		return flag;
	}
}
