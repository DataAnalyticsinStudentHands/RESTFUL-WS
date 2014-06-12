package honors.uh.edu.rest;

import java.util.ArrayList;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
 
import honors.uh.edu.model.SecurityManager;
import honors.uh.edu.pojo.User;
 
@Path("/WebService")
public class LoginService {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String login(@FormParam("username") String username,
			@FormParam("password") int id) {

		return getAllUsersList(username, id);

	}

	public String getAllUsersList(String username,int id)
	{
		//String userListData = null;
		try
		{
			ArrayList<User> userList = null;
			SecurityManager securityManager= new SecurityManager();
			userList = securityManager.getAllUsersList();
			for (User user : userList) {
				if(user.getFirstName().equals(username))
				{
					if(user.getId() == id)
					{
						return "Logged in User:"+username;
					}
				}
			}

		} catch (Exception e)
		{
			System.out.println("error");
		}
		return "You are not a Valid User";
	}
}