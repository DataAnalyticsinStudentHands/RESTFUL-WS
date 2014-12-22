package dash.pojo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dash.errorhandling.AppException;
import dash.service.FormResponseService;

/**
 *
 * Service class that handles REST requests. This is where you define your API
 * and what requests will be accepted.
 *
 * @author tyler.swensen@gmail.com
 *
 */
@Component("formResponseResource")
@Path("/formResponses")
public class FormResponseResource {

	@Autowired
	private FormResponseService formResponseService;
	
	

	// ************************************* CREATE
	// ************************************

	/**
	 * Adds a new resource (formResponse) from the given json formResponseat (at least
	 * formResponsename and password elements are required at the DB level)
	 *
	 * @param formResponse
	 * @return
	 * @throws AppException
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response createFormResponse(FormResponse formResponse)
			throws AppException, JsonParseException, JsonMappingException, IOException
	{
		
		Long createFormResponseId = formResponseService.createFormResponse(formResponse);
		
		
		return Response
				.status(Response.Status.CREATED)
				// 201
				.entity("A new formResponse has been created at index")
				.header("Location", String.valueOf(createFormResponseId))
				.header("ObjectId", String.valueOf(createFormResponseId))
				.build();
	}

	/**
	 * A list of resources (here formResponses) provided in json formResponseat will be
	 * added to the database.
	 *
	 * @param formResponses
	 * @return
	 * @throws AppException
	 */
	
	/*This service is disabled because it does not appear to be a use case.
	 * 
	 * Before enabling be sure to implement the creation of questions for each formResponse in a secure way.
	 * 
	 * @POST
	@Path("list")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createFormResponses(List<FormResponse> formResponses)
			throws AppException {
		formResponseService.createFormResponses(formResponses);
		return Response.status(Response.Status.CREATED)
				// 201
				.entity("List of formResponses was successfully created")
				.build();
	}*/

	// *************************************
	// READ************************************

	/**
	 * Returns a list of formResponses via pagination. The order the list is
	 * sorted is set in the DAO implementation. Number of sample objects is the
	 * page size, and start index is the id of the last sample object received.
	 * 
	 * @param numberOfFormResponses
	 * @param startIndex
	 * @return
	 * @throws IOException
	 * @throws AppException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<FormResponse> getFormResponses(
			@QueryParam("numberOfFormResponses") @DefaultValue("25") int numberOfFormResponses,
			@QueryParam("startIndex") @DefaultValue("0") Long startIndex)
			throws IOException, AppException {
		List<FormResponse> formResponses = formResponseService
				.getFormResponses(numberOfFormResponses, startIndex);
		return formResponses;
	}
	
	@GET
	@Path("/myFormResponses")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<FormResponse> getMyFormResponses(
			@QueryParam("numberOfFormResponses") @DefaultValue("25") int numberOfFormResponses,
			@QueryParam("startIndex") @DefaultValue("0") Long startIndex)
			throws IOException, AppException {
		List<FormResponse> formResponses = formResponseService
				.getMyFormResponses(numberOfFormResponses, startIndex);
		return formResponses;
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getFormResponseById(@PathParam("id") Long id,
			@QueryParam("detailed") boolean detailed) throws IOException,
			AppException {
		FormResponse formResponseById = formResponseService
				.getFormResponseById(id);
		return Response.status(200)
				.entity(new GenericEntity<FormResponse>(formResponseById) {
				}).header("Access-Control-Allow-Headers", "X-extra-header")
				.allow("OPTIONS").build();
	}
	
	
	// ************************************* UPDATE
	// ************************************

	/**
	 * The method offers both Creation and Update resource functionality. If
	 * there is no resource yet at the specified location, then a formResponse
	 * creation is executed and if there is then the resource will be full
	 * updated.
	 *
	 * @param id
	 * @param formResponse
	 * @return
	 * @throws AppException
	 */
	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response putFormResponseById(@PathParam("id") Long id,
			FormResponse formResponse) throws AppException {

		FormResponse formResponseById = formResponseService
				.verifyFormResponseExistenceById(id);

		if (formResponseById == null) {
			// resource not existent yet, and should be created under the
			// specified URI
			Long createFormResponseId = formResponseService
					.createFormResponse(formResponse);
			return Response
					.status(Response.Status.CREATED)
					// 201
					.entity("A new formResponse has been created AT THE LOCATION you specified")
					.header("Location", String.valueOf(createFormResponseId))
					.build();
		} else {
			// resource is existent and a full update should occur
			formResponseService.updateFullyFormResponse(formResponse);
			return Response
					.status(Response.Status.OK)
					// 200
					.entity("The formResponse you specified has been fully updated created AT THE LOCATION you specified")
					.header("Location", String.valueOf(id)).build();
		}
	}

	// PARTIAL update
	@POST
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response partialUpdateFormResponse(@PathParam("id") Long id,
			FormResponse formResponse) throws AppException {
		formResponse.setId(id);
		formResponseService.updatePartiallyFormResponse(formResponse);
		return Response
				.status(Response.Status.OK)
				// 200
				.entity("The formResponse you specified has been successfully updated")
				.build();
	}
}
