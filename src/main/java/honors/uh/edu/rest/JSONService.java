package honors.uh.edu.rest;

import honors.uh.edu.pojo.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/json/lindner")
public class JSONService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserInJSON() {

		final User track = new User();
		track.setFirstName("Peggy");
		track.setLastName("Lindner");

		return track;

	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUserInJSON(final User track) {

		final String result = "User saved : " + track;
		return Response.status(201).entity(result).build();

	}

}