package honors.uh.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
 
import honors.uh.edu.pojo.UserVO;
 
public class LoginHandler {

	public ArrayList<UserVO> getAllUsers(Connection connection) throws Exception {
		ArrayList<UserVO> userList = new ArrayList<UserVO>();
		try {
			// String uname = request.getParameter("uname");
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM user");
			// ps.setString(1,uname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserVO uservo = new UserVO();
				uservo.setUsername(rs.getString("username"));
				uservo.setPassword(rs.getString("password"));
				userList.add(uservo);
			}
			return userList;
		} catch (Exception e) {
			throw e;
		}
	}

}