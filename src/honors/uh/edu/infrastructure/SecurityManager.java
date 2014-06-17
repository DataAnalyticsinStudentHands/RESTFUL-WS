package honors.uh.edu.infrastructure;

import java.sql.Connection;
import java.util.ArrayList;
 
import honors.uh.edu.pojo.User;
import honors.uh.edu.dao.DbConnection; 
import honors.uh.edu.dao.LoginHandler;
 
public class SecurityManager {

	public ArrayList<User> getAllUsersList()throws Exception {
		ArrayList<User> userList = null;
		try {
			DbConnection database= new DbConnection();
			Connection connection = database.getConnection();
			LoginHandler loginHandler= new LoginHandler();
			userList= loginHandler.getAllUsers(connection);

		} catch (Exception e) {
			throw e;
		}
		return userList;
	}

}
