package dash.pojo;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

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
import dash.service.GroupService;
/*
 * 
 * Service Class that handles REST request
 * 
 * @author tswensen
 */
import dash.service.GroupService;

@Component
@Path("/groups")
public class GroupResource {

	@Autowired
	private GroupService groupService;
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response createGroup(Group group) throws AppException {
		Long createGroupId = groupService.createGroup(group);
		return Response.status(Response.Status.CREATED)
				// 201
				.entity("A new group has been created")
				.header("Location",
						"http://localhost:8080/groups/"
								+ String.valueOf(createGroupId)).build();
	}
	
	@POST
	@Path("list")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createGroups(List<Group> groups) throws AppException {
		groupService.createGroups(groups);
		return Response.status(Response.Status.CREATED) // 201
				.entity("List of groups was successfully created").build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Group> getGroups(
			@QueryParam("orderByInsertionDate") String orderByInsertionDate,
			@QueryParam("numberDaysToLookBack") Integer numberDaysToLookBack)
					throws IOException,	AppException {
		List<Group> groups = groupService.getGroups(
				orderByInsertionDate, numberDaysToLookBack);
		return groups;
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getGroupById(@PathParam("id") Long id,
			@QueryParam("detailed") boolean detailed)
					throws IOException,	AppException {
		Group groupById = groupService.getGroupById(id);
		return Response
				.status(200)
				.entity(new GenericEntity<Group>(groupById) {
				},
				detailed ? new Annotation[] { GroupDetailedView.Factory
						.get() } : new Annotation[0])
						.header("Access-Control-Allow-Headers", "X-extra-header")
						.allow("OPTIONS").build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response putGroupById(@PathParam("id") Long id, Group group)
			throws AppException {

		Group groupById = groupService.verifyGroupExistenceById(id);

		if (groupById == null) {
			// resource not existent yet, and should be created under the
			// specified URI
			Long createGroupId = groupService.createGroup(group);
			return Response
					.status(Response.Status.CREATED)
					// 201
					.entity("A new group has been created AT THE LOCATION you specified")
					.header("Location",
							"http://localhost:8080/services/groups/"
									+ String.valueOf(createGroupId)).build();
		} else {
			// resource is existent and a full update should occur
			groupService.updateFullyGroup(group);
			return Response
					.status(Response.Status.OK)
					// 200
					.entity("The group you specified has been fully updated created AT THE LOCATION you specified")
					.header("Location",
							"http://localhost:8888/services/groups/"
									+ String.valueOf(id)).build();
		}
	}

	// PARTIAL update
	@POST
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response partialUpdateGroup(@PathParam("id") Long id, Group group)
			throws AppException {
		group.setId(id);
		groupService.updatePartiallyGroup(group);
		return Response
				.status(Response.Status.OK)
				// 200
				.entity("The group you specified has been successfully updated")
				.build();
	}

	/*
	 * *********************************** DELETE ***********************************
	 */
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.TEXT_HTML })
	public Response deleteGroup(@PathParam("id") Long id)
			throws AppException {
		Group group= new Group();
		group.setId(id);
		groupService.deleteGroup(group);
		return Response.status(Response.Status.NO_CONTENT)// 204
				.entity("Group successfully removed from database").build();
	}

	@DELETE
	@Path("admin")
	@Produces({ MediaType.TEXT_HTML })
	public Response deleteGroups() {
		groupService.deleteGroups();
		return Response.status(Response.Status.NO_CONTENT)// 204
				.entity("All groups have been successfully removed").build();
	}
	
	@POST
	@Path("{id}/MANAGER/{userId}")
	@Produces({MediaType.TEXT_HTML})
	public Response resetManager(@PathParam("userId") Long userId, @PathParam("id") Long id){
		User user=new User();
		user.setId(userId);
		Group group= new Group();
		group.setId(id);
		groupService.resetManager(user, group);
		return Response.status(Response.Status.OK).entity("MANAGER RESET: User "+user.getId()
				+" set as sole MANAGER for group "+group.getId()).build();
	}
	
	@PUT
	@Path("{id}/MANAGER/{userId}")
	@Produces({MediaType.TEXT_HTML})
	public Response addManager(@PathParam("userId") Long userId, @PathParam("id") Long id){
		User user=new User();
		user.setId(userId);
		Group group= new Group();
		group.setId(id);
		groupService.addManager(user, group);
		return Response.status(Response.Status.OK).entity("MANAGER ADDED: User "+user.getId()
				+" added as a MANAGER for group "+group.getId()).build();
	}
	
	@DELETE
	@Path("{id}/MANAGER/{userId}")
	@Produces({MediaType.TEXT_HTML})
	public Response deleteManager(@PathParam("userId") Long userId, @PathParam("id") Long id){
		User user=new User();
		user.setId(userId);
		Group group= new Group();
		group.setId(id);
		groupService.deleteManager(user, group);
		return Response.status(Response.Status.OK).entity("MANAGER DELETED: User "+user.getId()
				+" removed as MANAGER for group "+group.getId()).build();
	}

	@POST
	@Path("{id}/MEMBER/{userId}")
	@Produces({MediaType.TEXT_HTML})
	public Response addMember(@PathParam("userId") Long userId, @PathParam("id") Long id){
		User user=new User();
		user.setId(userId);
		Group group= new Group();
		group.setId(id);
		groupService.addMember(user, group);
		return Response.status(Response.Status.OK).entity("MEMBER ADDED: User "+user.getId()
				+" set as MEMBER for group "+group.getId()).build();
	}
	
	@DELETE
	@Path("{id}/MEMBER/{userId}")
	@Produces({MediaType.TEXT_HTML})
	public Response deleteMember(@PathParam("userId") Long userId, @PathParam("id") Long id){
		User user=new User();
		user.setId(userId);
		Group group= new Group();
		group.setId(id);
		groupService.deleteMember(user, group);
		return Response.status(Response.Status.OK).entity("MEMBER DELETED: User "+user.getId()
				+" removed as MEMBER from group "+group.getId()).build();
	}
	
}
