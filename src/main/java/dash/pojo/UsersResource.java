package dash.pojo;

import java.io.IOException;
import java.lang.annotation.Annotation;
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
 * @author plindner
 *
 */
@Component
@Path("/users")
public class UsersResource {

	@Autowired
	private UserService userService;



	/*
	 * *********************************** CREATE ***********************************
	 */

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
				.entity("A new user has been created")
				.header("Location",
						"http://localhost:8080/services/users/"
								+ String.valueOf(createUserId)).build();
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

	/*
	 * *********************************** READ ***********************************
	 */
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
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<User> getUsers(
			@QueryParam("orderByInsertionDate") String orderByInsertionDate,
			@QueryParam("numberDaysToLookBack") Integer numberDaysToLookBack)
					throws IOException,	AppException {
		List<User> users = userService.getUsers(
				orderByInsertionDate, numberDaysToLookBack);
		return users;
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUserById(@PathParam("id") Long id,
			@QueryParam("detailed") boolean detailed)
					throws IOException,	AppException {
		User userById = userService.getUserById(id);
		return Response
				.status(200)
				.entity(new GenericEntity<User>(userById) {
				},
				detailed ? new Annotation[] { UserDetailedView.Factory
						.get() } : new Annotation[0])
						.header("Access-Control-Allow-Headers", "X-extra-header")
						.allow("OPTIONS").build();
	}

	/*
	 * *********************************** UPDATE ***********************************
	 */

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
			return Response
					.status(Response.Status.CREATED)
					// 201
					.entity("A new user has been created AT THE LOCATION you specified")
					.header("Location",
							"http://localhost:8080/services/users/"
									+ String.valueOf(createUserId)).build();
		} else {
			// resource is existent and a full update should occur
			userService.updateFullyUser(user);
			return Response
					.status(Response.Status.OK)
					// 200
					.entity("The user you specified has been fully updated created AT THE LOCATION you specified")
					.header("Location",
							"http://localhost:8888/services/users/"
									+ String.valueOf(id)).build();
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
		return Response
				.status(Response.Status.OK)
				// 200
				.entity("The user you specified has been successfully updated")
				.build();
	}

	/*
	 * *********************************** DELETE ***********************************
	 */
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.TEXT_HTML })
	public Response deleteUser(@PathParam("id") Long id)
			throws AppException {
		User user= new User();
		user.setId(id);
		userService.deleteUser(user);
		return Response.status(Response.Status.NO_CONTENT)// 204
				.entity("User successfully removed from database").build();
	}

	@DELETE
	@Path("admin")
	@Produces({ MediaType.TEXT_HTML })
	public Response deleteUsers() {
		userService.deleteUsers();
		return Response.status(Response.Status.NO_CONTENT)// 204
				.entity("All users have been successfully removed").build();
	}

	/*
	 * *********************************** FILES ***********************************
	 */
	private static final String IMAGE_PATH = "/srv/imgages/testApp/users/Admin.png";  
    
	 @GET  
	 @Path("{username}")  
	 @Produces("image/png")  
	 public Response getUserPicture() {  
	   
	  File file = new File(IMAGE_PATH); 	   
	  
	  return Response.ok((Object) file) 
			  		.header("Content-Disposition",  
			   "attachment; filename=\"employee_image_photo.png\"").build();  
	   
	 }  
	
	
	public void setuserService(UserService userService) {
		this.userService = userService;
	}

}
