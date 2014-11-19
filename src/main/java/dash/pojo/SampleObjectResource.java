package dash.pojo;

import java.io.IOException;
import java.io.InputStream;
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
import dash.service.SampleObjectService;

/**
 *
 * Service class that handles REST requests. This is where you define your API
 * and what requests will be accepted.
 *
 * @author tyler.swensen@gmail.com
 *
 */
@Component
@Path("/sampleObjects")
public class SampleObjectResource {

	@Autowired
	private SampleObjectService sampleObjectService;

	// ************************************* CREATE
	// ************************************

	/**
	 * Adds a new resource (sampleObject) from the given json format (at least
	 * sampleObjectname and password elements are required at the DB level)
	 *
	 * @param sampleObject
	 * @return
	 * @throws AppException
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response createSampleObject(SampleObject sampleObject)
			throws AppException {
		Long createSampleObjectId = sampleObjectService
				.createSampleObject(sampleObject);
		return Response
				.status(Response.Status.CREATED)
				// 201
				.entity("A new sampleObject has been created at index")
				.header("Location", String.valueOf(createSampleObjectId))
				.header("ObjectId", String.valueOf(createSampleObjectId))
				.build();
	}

	/**
	 * A list of resources (here sampleObjects) provided in json format will be
	 * added to the database.
	 *
	 * @param sampleObjects
	 * @return
	 * @throws AppException
	 */
	@POST
	@Path("list")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createSampleObjects(List<SampleObject> sampleObjects)
			throws AppException {
		sampleObjectService.createSampleObjects(sampleObjects);
		return Response.status(Response.Status.CREATED)
				// 201
				.entity("List of sampleObjects was successfully created")
				.build();
	}

	// *************************************
	// READ************************************

	/**
	 * Returns a list of sampleObjects via pagination. The order the list is
	 * sorted is set in the DAO implementation. Number of sample objects is the
	 * page size, and start index is the id of the last sample object received.
	 * 
	 * @param numberOfSampleObjects
	 * @param startIndex
	 * @return
	 * @throws IOException
	 * @throws AppException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<SampleObject> getSampleObjects(
			@QueryParam("numberOfSampleObjects") @DefaultValue("25") int numberOfSampleObjects,
			@QueryParam("startIndex") @DefaultValue("0") Long startIndex)
			throws IOException, AppException {
		List<SampleObject> sampleObjects = sampleObjectService
				.getSampleObjects(numberOfSampleObjects, startIndex);
		return sampleObjects;
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSampleObjectById(@PathParam("id") Long id,
			@QueryParam("detailed") boolean detailed) throws IOException,
			AppException {
		SampleObject sampleObjectById = sampleObjectService
				.getSampleObjectById(id);
		return Response.status(200)
				.entity(new GenericEntity<SampleObject>(sampleObjectById) {
				}).header("Access-Control-Allow-Headers", "X-extra-header")
				.allow("OPTIONS").build();
	}

	// ************************************* UPDATE
	// ************************************

	/**
	 * The method offers both Creation and Update resource functionality. If
	 * there is no resource yet at the specified location, then a sampleObject
	 * creation is executed and if there is then the resource will be full
	 * updated.
	 *
	 * @param id
	 * @param sampleObject
	 * @return
	 * @throws AppException
	 */
	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response putSampleObjectById(@PathParam("id") Long id,
			SampleObject sampleObject) throws AppException {

		SampleObject sampleObjectById = sampleObjectService
				.verifySampleObjectExistenceById(id);

		if (sampleObjectById == null) {
			// resource not existent yet, and should be created under the
			// specified URI
			Long createSampleObjectId = sampleObjectService
					.createSampleObject(sampleObject);
			return Response
					.status(Response.Status.CREATED)
					// 201
					.entity("A new sampleObject has been created AT THE LOCATION you specified")
					.header("Location", String.valueOf(createSampleObjectId))
					.build();
		} else {
			// resource is existent and a full update should occur
			sampleObjectService.updateFullySampleObject(sampleObject);
			return Response
					.status(Response.Status.OK)
					// 200
					.entity("The sampleObject you specified has been fully updated created AT THE LOCATION you specified")
					.header("Location", String.valueOf(id)).build();
		}
	}

	//PARTIAL update
	@POST
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response partialUpdateSampleObject(@PathParam("id") Long id,
			SampleObject sampleObject) throws AppException {
		sampleObject.setId(id);
		sampleObjectService.updatePartiallySampleObject(sampleObject);
		return Response
				.status(Response.Status.OK)
				// 200
				.entity("The sampleObject you specified has been successfully updated")
				.build();
	}
	
	// ************************************* FILE UPLOAD
	// ************************************
	
	@POST
	@Path("/upload")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response uploadFile(
			@QueryParam("id") Long id,
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail,
		@HeaderParam("Content-Length") final long fileSize) throws AppException {
		
		SampleObject sampleObject= sampleObjectService.getSampleObjectById(id);
		
		//TODO: Generate directory if not set
		//if(application.getDocument_folder()==null)	
		String uploadedFileLocation = AppConstants.APPLICATION_UPLOAD_LOCATION_FOLDER+"/"
				+ sampleObject.getDocument_folder()+"/" + fileDetail.getFileName().replaceAll("%20", "_").toLowerCase();;
		// save it
				sampleObjectService.uploadFile(uploadedInputStream, uploadedFileLocation);
 
		String output = "File uploaded to : " + uploadedFileLocation;
 
		return Response.status(200).entity(output).build();
 
	}
	
	@GET
	@Path("/upload")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getFileNames(@QueryParam("applicationId") Long id) throws AppException{
		
		SampleObject sampleObject= sampleObjectService.getSampleObjectById(id);
		JaxbList<String> fileNames=new JaxbList<String>(sampleObjectService.getFileNames(sampleObject));
		return Response.status(200).entity(fileNames).build();
	}
	
	//Gets a specific file and allows the user to download the pdf
	@GET
	@Path("/upload")
	public Response getFile(@QueryParam("applicationId") Long id,
			@QueryParam("fileName") String fileName) throws AppException {
		
		SampleObject sampleObject= sampleObjectService.getSampleObjectById(id);
		
		if(sampleObject == null){
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Invalid applicationId, unable to locate application with id: "+id).build();
		}
		
		String uploadedFileLocation = AppConstants.APPLICATION_UPLOAD_LOCATION_FOLDER + sampleObject.getDocument_folder()+"/" + fileName;
		
		
		return Response.ok(sampleObjectService.getUploadFile(uploadedFileLocation))
				.type("application/pdf").build(); 
	}
	
	@DELETE
	@Path("/upload")
	public Response deleteUpload(
			@QueryParam("applicationId") Long id,
			@QueryParam("fileName") String fileName) throws AppException{
		
		SampleObject sampleObject= sampleObjectService.getSampleObjectById(id);
		
		String uploadedFileLocation = AppConstants.APPLICATION_UPLOAD_LOCATION_FOLDER + sampleObject.getDocument_folder()+"/" + fileName;
		// save it
		sampleObjectService.deleteUploadFile(uploadedFileLocation);
 
		String output = "File removed from: " + uploadedFileLocation;
		
		return Response.status(200).entity(output).build();
	}
	
	@XmlRootElement(name="fileNames")
	public static class JaxbList<T>{
	    protected List<T> list;

	    public JaxbList(){}

	    public JaxbList(List<T> list){
	    	this.list=list;
	    }

	    @XmlElement(name="fileName")
	    public List<T> getList(){
	    	return list;
	    }
	}
	
}
