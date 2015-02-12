package dash.pojo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dash.errorhandling.AppException;
import dash.filters.AppConstants;
import dash.service.FileUploadService;
import dash.service.FormService;
import dash.service.UserService;

/**
 *
 * Service class that handles REST requests. This is where you define your API
 * and what requests will be accepted.
 *
 * @author tyler.swensen@gmail.com
 *
 */
@Component("fileUploadResource")
@Path("/fileUploads")
public class FileUploadResource {

	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private UserService userService;

	// ************************************* CREATE
	// ************************************

	/**
	 * Adds a new resource (fileUpload) from the given json format (at least
	 * fileUploadname and password elements are required at the DB level)
	 *
	 * @param fileUpload
	 * @return
	 * @throws AppException
	 */
	@POST
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Produces({ MediaType.TEXT_HTML })
	public Response createFileUpload(@QueryParam("user_id") Long user_id,
			@QueryParam("form_id") Long form_id,
			@QueryParam("content_type") String content_type,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@HeaderParam("Content-Length") final long fileSize)
			throws AppException {
		User user= userService.getUserById(user_id);
		
		//Ensure content type is valid
		try{
		MediaType.valueOf(content_type);
		}catch (IllegalArgumentException e){
				return Response.status(Response.Status.BAD_REQUEST)
					.entity("content type must be a valid javax.ws.rs.core.MediaType")
					.build();}
		
		
		String path= AppConstants.APPLICATION_UPLOAD_LOCATION_FOLDER+"/"
				+ user.getId()+"/" + fileDetail.getFileName().replaceAll("%20", "_").toLowerCase();
		
		FileUpload fileUpload = new FileUpload(user_id, form_id, path, fileDetail.getFileName().replaceAll("%20", "_").toLowerCase()
				, content_type);
		Long createFileUploadId = fileUploadService
				.createFileUpload(fileUpload, uploadedInputStream);
		return Response
				.status(Response.Status.CREATED)
				// 201
				.entity("A new fileUpload has been created at index")
				.header("Location", String.valueOf(createFileUploadId))
				.header("ObjectId", String.valueOf(createFileUploadId))
				.build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.TEXT_HTML })
	public Response deleteFileUpload(@PathParam("id") Long id)
			throws AppException {
		FileUpload fileUpload = fileUploadService.verifyFileUploadExistenceById(id);
		Form form = formService.verifyFormExistenceById(fileUpload.getForm_id());
		fileUploadService.deleteFileUpload(fileUpload, form);
		return Response.status(Response.Status.NO_CONTENT)// 204
				.entity("FileUpload successfully removed from database").build();
	}

	/**
	 * A list of resources (here fileUploads) provided in json format will be
	 * added to the database.
	 *
	 * @param fileUploads
	 * @return
	 * @throws AppException
	 */
	/*
	@POST
	@Path("list")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createFileUploads(List<FileUpload> fileUploads)
			throws AppException {
		fileUploadService.createFileUploads(fileUploads);
		return Response.status(Response.Status.CREATED)
				// 201
				.entity("List of fileUploads was successfully created")
				.build();
	}
	*/

	// *************************************
	// READ************************************

	/**
	 * Returns a list of fileUploads via pagination. The order the list is
	 * sorted is set in the DAO implementation. Number of sample objects is the
	 * page size, and start index is the id of the last sample object received.
	 * 
	 * @param numberOfFileUploads
	 * @param startIndex
	 * @return
	 * @throws IOException
	 * @throws AppException
	 */
	/*
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<FileUpload> getFileUploads(
			@QueryParam("numberOfFileUploads") @DefaultValue("25") int numberOfFileUploads,
			@QueryParam("startIndex") @DefaultValue("0") Long startIndex)
			throws IOException, AppException {
		List<FileUpload> fileUploads = fileUploadService
				.getFileUploads(numberOfFileUploads, startIndex);
		return fileUploads;
	}
	*/

	@GET
	@Path("{id}")
	public Response getFileUploadById(@PathParam("id") Long id) throws IOException,
			AppException {
		FileUpload fileUploadById = fileUploadService
				.getFileUploadById(id);
		Form form = formService.verifyFormExistenceById(fileUploadById.getForm_id());
		return Response.ok(fileUploadService.getUploadFile(fileUploadById, form))
				.type(Files.probeContentType(Paths.get(fileUploadById.getPath()))).build(); 
	}

	// ************************************* UPDATE
	// ************************************

	/**
	 * The method offers both Creation and Update resource functionality. If
	 * there is no resource yet at the specified location, then a fileUpload
	 * creation is executed and if there is then the resource will be full
	 * updated.
	 *
	 * @param id
	 * @param fileUpload
	 * @return
	 * @throws AppException
	 */
	/*
	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response putFileUploadById(@PathParam("id") Long id,
			FileUpload fileUpload) throws AppException {

		FileUpload fileUploadById = fileUploadService
				.verifyFileUploadExistenceById(id);

		if (fileUploadById == null) {
			// resource not existent yet, and should be created under the
			// specified URI
			//THIS SHOULD NEVER OCCUR FOR FILE UPLOAD WILL PROBABLY BREAK
			Long createFileUploadId = fileUploadService
					.createFileUpload(fileUpload, null);
			return Response
					.status(Response.Status.CREATED)
					// 201
					.entity("A new fileUpload has been created AT THE LOCATION you specified")
					.header("Location", String.valueOf(createFileUploadId))
					.build();
		} else {
			// resource is existent and a full update should occur
			fileUploadService.updateFullyFileUpload(fileUpload);
			return Response
					.status(Response.Status.OK)
					// 200
					.entity("The fileUpload you specified has been fully updated created AT THE LOCATION you specified")
					.header("Location", String.valueOf(id)).build();
		}
	}
	*/

	/*PARTIAL update
	@POST
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response partialUpdateFileUpload(@PathParam("id") Long id,
			FileUpload fileUpload) throws AppException {
		fileUpload.setId(id);
		fileUploadService.updatePartiallyFileUpload(fileUpload);
		return Response
				.status(Response.Status.OK)
				// 200
				.entity("The fileUpload you specified has been successfully updated")
				.build();
	}*/
	
	// ************************************* FILE UPLOAD
	// ************************************
	
	
}
