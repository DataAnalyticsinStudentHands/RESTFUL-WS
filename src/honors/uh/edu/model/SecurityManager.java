package honors.uh.edu.model;

import java.sql.Connection;
import java.util.ArrayList;
 
import honors.uh.edu.pojo.UserVO;
import honors.uh.edu.dao.DbConnection; 
import honors.uh.edu.dao.LoginHandler;
 
public class SecurityManager {

	public ArrayList<UserVO> getAllUsersList()throws Exception {
		ArrayList<UserVO> userList = null;
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
