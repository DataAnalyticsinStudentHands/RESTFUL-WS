package honors.uh.edu.rest;

import honors.uh.edu.infrastructure.SecurityManager;
import honors.uh.edu.pojo.User;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/WebService")
public class LoginService {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String login(@FormParam("username") final String username,
			@FormParam("password") final String id) {

		return getAllUsersList(username, id);

	}

	public String getAllUsersList(final String username,final String id)
	{
		//String userListData = null;
		try
		{
			ArrayList<User> userList = null;
			final SecurityManager securityManager= new SecurityManager();
			userList = securityManager.getAllUsersList();
			for (final User user : userList) {
				if(user.getFirstName().equals(username))
				{
					if(user.getLastName() == id)
					{

						return "Logged in User:"+username;
					}
				}
			}

		} catch (final Exception e)
		{
			System.out.println("error");
		}
		return "You are not a Valid User";
	}
}