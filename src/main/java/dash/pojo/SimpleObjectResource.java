package dash.pojo;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import dash.errorhandling.AppException;
import dash.service.SimpleObjectService;

/**
 * 
 * Service Class that handles REST requests for Simple Objects
 * 
 * @author plindner
 */
@Component
@Path("/simple")
public class SimpleObjectResource {

	@Autowired
	private SimpleObjectService simpleObjectService;
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response createSimpleObject(SimpleObject simpleObject) throws AppException {
		Long createSimpleObjectId = simpleObjectService.createSimpleObject(simpleObject);
		return Response.status(Response.Status.CREATED)
				// 201
				.entity("A new simple object has been created")
				.header("Location",
						"http://..../simple/"
								+ String.valueOf(createSimpleObjectId)).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<SimpleObject> getSimpleObjects(
			@QueryParam("orderByInsertionDate") String orderByInsertionDate)
					throws IOException,	AppException {
		List<SimpleObject> simpleObjects = simpleObjectService.getSimpleObjects(
				orderByInsertionDate);
		return simpleObjects;
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSimpleObjectById(@PathParam("id") Long id,
			@QueryParam("detailed") boolean detailed)
					throws IOException,	AppException {
		SimpleObject simpleObjectById = simpleObjectService.getSimpleObjectById(id);
		return Response
				.status(200)
				.entity(new GenericEntity<SimpleObject>(simpleObjectById) {
				},
				detailed ? new Annotation[] { SimpleObjectDetailedView.Factory
						.get() } : new Annotation[0])
						.header("Access-Control-Allow-Headers", "X-extra-header")
						.allow("OPTIONS").build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response putSimpleObjectById(@PathParam("id") Long id, SimpleObject simpleObject)
			throws AppException {

		SimpleObject simpleObjectById = simpleObjectService.verifySimpleObjectExistenceById(id);

		if (simpleObjectById == null) {
			// resource not existent yet, and should be created under the
			// specified URI
			Long createSimpleObjectId = simpleObjectService.createSimpleObject(simpleObject);
			return Response
					.status(Response.Status.CREATED)
					// 201
					.entity("A new simpleObject has been created AT THE LOCATION you specified")
					.header("Location",
							"http://.../simple/"
									+ String.valueOf(createSimpleObjectId)).build();
		} else {
			// resource is existent and a full update should occur
			simpleObjectService.updateFullySimpleObject(simpleObject);
			return Response
					.status(Response.Status.OK)
					// 200
					.entity("The simpleObject you specified has been fully updated created AT THE LOCATION you specified")
					.header("Location",
							"http://.../simple"
									+ String.valueOf(id)).build();
		}
	}

	// PARTIAL update
	@POST
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response partialUpdateSimpleObject(@PathParam("id") Long id, SimpleObject simpleObject)
			throws AppException {
		simpleObject.setId(id);
		simpleObjectService.updatePartiallySimpleObject(simpleObject);
		return Response
				.status(Response.Status.OK)
				// 200
				.entity("The simpleObject you specified has been successfully updated")
				.build();
	}

	/*
	 * *********************************** DELETE ***********************************
	 */
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.TEXT_HTML })
	public Response deleteSimpleObject(@PathParam("id") Long id)
			throws AppException {
		SimpleObject simpleObject= new SimpleObject();
		simpleObject.setId(id);
		simpleObjectService.deleteSimpleObject(simpleObject);
		return Response.status(Response.Status.NO_CONTENT)// 204
				.entity("SimpleObject successfully removed from database").build();
	}

	@DELETE
	@Path("admin")
	@Produces({ MediaType.TEXT_HTML })
	public Response deleteSimpleObjects() {
		simpleObjectService.deleteSimpleObjects();
		return Response.status(Response.Status.NO_CONTENT)// 204
				.entity("All simpleObjects have been successfully removed").build();
	}
	
}