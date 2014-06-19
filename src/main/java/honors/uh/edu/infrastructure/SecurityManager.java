package honors.uh.edu.infrastructure;

import honors.uh.edu.dao.DbConnection;
import honors.uh.edu.dao.LoginHandler;
import honors.uh.edu.pojo.User;

import java.sql.Connection;
import java.util.ArrayList;

public class SecurityManager {

	public ArrayList<User> getAllUsersList()throws Exception {
		ArrayList<User> userList = null;
		try {
			final DbConnection database= new DbConnection();
			final Connection connection = database.getConnection();
			final LoginHandler loginHandler= new LoginHandler();
			userList= loginHandler.getAllUsers(connection);

		} catch (final Exception e) {
			throw e;
		}
		return userList;
	}

}
