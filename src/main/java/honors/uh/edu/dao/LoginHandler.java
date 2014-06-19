package honors.uh.edu.dao;

import honors.uh.edu.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginHandler {

	public ArrayList<User> getAllUsers(final Connection connection) throws Exception {
		final ArrayList<User> userList = new ArrayList<User>();
		try {
			// String uname = request.getParameter("uname");
			final PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM user");
			// ps.setString(1,uname);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				final User user = new User();
				user.setFirstName(rs.getString("username"));
				user.setLastName(rs.getString("password"));
				userList.add(user);
			}
			return userList;
		} catch (final Exception e) {
			throw e;
		}
	}

}