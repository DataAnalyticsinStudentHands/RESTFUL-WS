package honors.uh.edu.rest;

import honors.uh.edu.pojo.User;
import honors.uh.edu.service.contract.UserService;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

@Path("/users")
public class UserRestService {

	private final UserService userService;

	@Inject
	public UserRestService(final UserService userService) {
		this.userService = userService;
	}

	@GET
	@Path("numberOfUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public int getNumberOfUsers() {
		return userService.getNumberOfUsers();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsersInJSON() {
		return userService.getAllUsers();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("id") final int id) {
		return userService.getById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User create(final User user) {
		return userService.createNewUser(user);
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User update(final User user) {
		return userService.update(user);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") final int id) {
		userService.remove(id);
	}
}
