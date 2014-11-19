package dash.pojo;

import java.io.IOException;
import java.util.List;
import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dash.errorhandling.AppException;
import dash.service.UserService;

/**
 *
 * Service class that handles REST requests
 *
 * @author plindner, tyler.swensen@gmail.com
 *
 */
@Component
@Path("/users")
public class UsersResource {

	@Autowired
	private UserService userService;

	// *********************************** CREATE

	/**
	 * Adds a new resource (user) from the given json format (at least username
	 * and password elements are required at the DB level)
	 *
	 * @param user
	 * @return
	 * @throws AppException
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response createUser(User user) throws AppException {
		Long createUserId = userService.createUser(user);
		return Response.status(Response.Status.CREATED)
				// 201
				.entity("A new user has been created with ")
				.header("ObjectId", String.valueOf(createUserId)).build();
	}

	/**
	 * Adds a new user (resource) from "form" (at least title and feed elements
	 * are required at the DB level)
	 *
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastNameuser
	 * @return
	 * @throws AppException
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.TEXT_HTML })
	@Transactional
	public Response createUserFromApplicationFormURLencoded(
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("fistName") String firstName,
			@FormParam("lastName") String lastName,
			@FormParam("city") String city,
			@FormParam("homePhone") String homePhone,
			@FormParam("cellPhone") String cellPhone,
			@FormParam("email") String email,
			@FormParam("picturePath") String picturePath) throws AppException {

		User user = new User(username, password, firstName, lastName, city,
				homePhone, cellPhone, email, picturePath);

		Long createUserid = userService.createUser(user);

		return Response
				.status(Response.Status.CREATED)
				// 201
				.entity("A new user/resource has been created at /services/users/"
						+ createUserid)
				.header("Location",
						"http://localhost:8888/services/users/"
								+ String.valueOf(createUserid)).build();
	}

	/**
	 * A list of resources (here users) provided in json format will be added to
	 * the database.
	 *
	 * @param users
	 * @return
	 * @throws AppException
	 */
	@POST
	@Path("list")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createUsers(List<User> users) throws AppException {
		userService.createUsers(users);
		return Response.status(Response.Status.CREATED) // 201
				.entity("List of users was successfully created").build();
	}

	// *********************************** READ

	/**
	 * Returns all resources (users) from the database
	 *
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws AppException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<User> getUsers(
			@QueryParam("orderByInsertionDate") String orderByInsertionDate,
			@QueryParam("numberDaysToLookBack") Integer numberDaysToLookBack)
			throws IOException, AppException {
		List<User> users = userService.getUsers(orderByInsertionDate,
				numberDaysToLookBack);
		return users;
	}

	@GET
	@Path("myUser")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<User> getMyUser(
			@QueryParam("orderByInsertionDate") String orderByInsertionDate,
			@QueryParam("numberDaysToLookBack") Integer numberDaysToLookBack)
			throws IOException, AppException {
		List<User> users = userService.getMyUser(orderByInsertionDate,
				numberDaysToLookBack);
		return users;
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getUserById(@PathParam("id") Long id,
			@QueryParam("detailed") boolean detailed) throws IOException,
			AppException {
		User userById = userService.getUserById(id);
		return Response.status(200).entity(new GenericEntity<User>(userById) {
		}).header("Access-Control-Allow-Headers", "X-extra-header")
				.allow("OPTIONS").build();
	}

	@GET
	@Path("myRole")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getMyRole() throws IOException, AppException {

		try {
			List<String> role = userService.getRole(userService.getMyUser(
					"ASC", null).get(0));
			return Response.status(Response.Status.OK).entity(role).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage()).build();
		}
	}

	// ************************************ UPDATE

	/**
	 * The method offers both Creation and Update resource functionality. If
	 * there is no resource yet at the specified location, then a user creation
	 * is executed and if there is then the resource will be full updated.
	 *
	 * @param id
	 * @param user
	 * @return
	 * @throws AppException
	 */
	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response putUserById(@PathParam("id") Long id, User user)
			throws AppException {

		User userById = userService.verifyUserExistenceById(id);

		if (userById == null) {
			// resource not existent yet, and should be created under the
			// specified URI
			Long createUserId = userService.createUser(user);
			return Response.status(Response.Status.CREATED)
					// 201
					.entity("A new user has been created with ")
					.header("ObjectId", String.valueOf(createUserId)).build();
		} else {
			// resource is existent and a full update should occur
			userService.updateFullyUser(user);
			return Response.status(Response.Status.OK)
					// 200
					.entity("The user has been fully updated ")
					.header("ObjectId ", String.valueOf(id)).build();
		}
	}

	// PARTIAL update
	@POST
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response partialUpdateUser(@PathParam("id") Long id, User user)
			throws AppException {
		user.setId(id);
		userService.updatePartiallyUser(user);
		return Response.status(Response.Status.OK)
				// 200
				.entity("The user you specified has been successfully updated")
				.build();
	}

	/**
	 * Changes a users Role (Expects role to = {ROLE_USER, ROLE_MODERATOR,
	 * ROLE_ADMIN})
	 */
	@POST
	@Path("{id}/role")
	public Response updateUserRole(@PathParam("id") Long id,
			@QueryParam("role") String role) throws AppException {

		User user = userService.getUserById(id);
		switch (userService.getRole(user).get(0)) {
		case "ROLE_ROOT":
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Cannot modify root user permissions").build();
		case "ROLE_ADMIN":
			if (userService.getRole(userService.getMyUser("ASC", null).get(0))
					.contains("ROLE_ADMIN")
					|| userService.getRole(
							userService.getMyUser("ASC", null).get(0))
							.contains("ROLE_ROOT")) {
				break;
			} else
				return Response
						.status(401)
						.entity("You do not have required permissions for this"
								+ ".  You must have admin priviliges to modify another admin's role.")
						.build();
		case "ROLE_VISITOR":
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Cannot modify visitor user permissions").build();
		}
		switch (role) {
		case "ROLE_USER":
			userService.setRoleUser(user);
			break;
		case "ROLE_MODERATOR":
			userService.setRoleModerator(user);
			break;
		case "ROLE_ADMIN":
			userService.setRoleAdmin(user);
			break;
		default:
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("The role you specified does not exist").build();
		}
		return Response
				.status(Response.Status.OK)
				.entity("The users role you specified has been successfully updated")
				.build();
	}

	@POST
	@Path("{id}/password")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response passwordReset(@PathParam("id") Long id, User user)
			throws AppException {
		user.setId(id);
		userService.resetPassword(user);
		return Response.status(Response.Status.OK)
				// 200
				.entity("The user you specified has been successfully updated")
				.build();
	}

	// ************************************ DELETE

	@DELETE
	@Path("{id}")
	@Produces({ MediaType.TEXT_HTML })
	public Response deleteUser(@PathParam("id") Long id) throws AppException {
		User user = new User();
		user.setId(id);
		userService.deleteUser(user);
		return Response.status(Response.Status.NO_CONTENT)// 204
				.entity("User successfully removed from database").build();
	}

	/*
	 * @DELETE / @Path("admin") /@Produces({ MediaType.TEXT_HTML }) /public
	 * Response deleteUsers() { / userService.deleteUsers(); /return
	 * Response.status(Response.Status.NO_CONTENT)// 204 /
	 * .entity("All users have been successfully removed").build(); }
	 */
}
