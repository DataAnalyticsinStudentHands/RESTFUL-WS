package dash.pojo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import dash.service.FormService;

/**
 *
 * Service class that handles REST requests. This is where you define your API
 * and what requests will be accepted.
 *
 * @author tyler.swensen@gmail.com
 *
 */
@Component("formResource")
@Path("/forms")
public class FormResource {

	@Autowired
	private FormService formService;
	
	

	// ************************************* CREATE
	// ************************************

	/**
	 * Adds a new resource (form) from the given json format (at least
	 * formname and password elements are required at the DB level)
	 *
	 * @param form
	 * @return
	 * @throws AppException
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response createForm(Form form)
			throws AppException, JsonParseException, JsonMappingException, IOException
	{
		
		Long createFormId = formService.createForm(form);
		
		
		return Response
				.status(Response.Status.CREATED)
				// 201
				.entity("A new form has been created at index")
				.header("Location", String.valueOf(createFormId))
				.header("ObjectId", String.valueOf(createFormId))
				.build();
	}

	/**
	 * A list of resources (here forms) provided in json format will be
	 * added to the database.
	 *
	 * @param forms
	 * @return
	 * @throws AppException
	 */
	
	/*This service is disabled because it does not appear to be a use case.
	 * 
	 * Before enabling be sure to implement the creation of questions for each form in a secure way.
	 * 
	 * @POST
	@Path("list")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createForms(List<Form> forms)
			throws AppException {
		formService.createForms(forms);
		return Response.status(Response.Status.CREATED)
				// 201
				.entity("List of forms was successfully created")
				.build();
	}*/

	// *************************************
	// READ************************************

	/**
	 * Returns a list of forms via pagination. The order the list is
	 * sorted is set in the DAO implementation. Number of sample objects is the
	 * page size, and start index is the id of the last sample object received.
	 * 
	 * @param numberOfForms
	 * @param startIndex
	 * @return
	 * @throws IOException
	 * @throws AppException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Form> getForms(
			@QueryParam("numberOfForms") @DefaultValue("25") int numberOfForms,
			@QueryParam("startIndex") @DefaultValue("0") Long startIndex)
			throws IOException, AppException {
		List<Form> forms = formService
				.getForms(numberOfForms, startIndex);
		return forms;
	}
	
	@GET
	@Path("/myForms")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Form> getMyForms(
			@QueryParam("numberOfForms") @DefaultValue("25") int numberOfForms,
			@QueryParam("startIndex") @DefaultValue("0") Long startIndex)
			throws IOException, AppException {
		List<Form> forms = formService
				.getMyForms(numberOfForms, startIndex);
		return forms;
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getFormById(@PathParam("id") Long id,
			@QueryParam("detailed") boolean detailed) throws IOException,
			AppException {
		Form formById = formService
				.getFormById(id);
		return Response.status(200)
				.entity(new GenericEntity<Form>(formById) {
				}).header("Access-Control-Allow-Headers", "X-extra-header")
				.allow("OPTIONS").build();
	}
	
	
	// ************************************* UPDATE
	// ************************************

	/**
	 * The method offers both Creation and Update resource functionality. If
	 * there is no resource yet at the specified location, then a form
	 * creation is executed and if there is then the resource will be full
	 * updated.
	 *
	 * @param id
	 * @param form
	 * @return
	 * @throws AppException
	 */
	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response putFormById(@PathParam("id") Long id,
			Form form) throws AppException {

		Form formById = formService
				.verifyFormExistenceById(id);

		if (formById == null) {
			// resource not existent yet, and should be created under the
			// specified URI
			Long createFormId = formService
					.createForm(form);
			return Response
					.status(Response.Status.CREATED)
					// 201
					.entity("A new form has been created AT THE LOCATION you specified")
					.header("Location", String.valueOf(createFormId))
					.build();
		} else {
			// resource is existent and a full update should occur
			formService.updateFullyForm(form);
			return Response
					.status(Response.Status.OK)
					// 200
					.entity("The form you specified has been fully updated created AT THE LOCATION you specified")
					.header("Location", String.valueOf(id)).build();
		}
	}

	// PARTIAL update
	@POST
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response partialUpdateForm(@PathParam("id") Long id,
			Form form) throws AppException {
		form.setId(id);
		formService.updatePartiallyForm(form);
		return Response
				.status(Response.Status.OK)
				// 200
				.entity("The form you specified has been successfully updated")
				.build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.TEXT_HTML })
	public Response deletePost(@PathParam("id") Long id)
			throws AppException {
		Form form = formService.verifyFormExistenceById(id);
		
		
		formService.deleteForm(form);
		return Response.status(Response.Status.NO_CONTENT)// 204
				.entity("Form successfully removed from database").build();
	}
}
