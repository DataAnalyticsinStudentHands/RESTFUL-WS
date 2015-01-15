package dash.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dash.dao.FormResponseDao;
import dash.dao.FormResponseEntity;
import dash.errorhandling.AppException;
import dash.filters.AppConstants;
import dash.helpers.NullAwareBeanUtilsBean;
import dash.pojo.Entry;
import dash.pojo.Form;
import dash.pojo.FormResponse;
import dash.pojo.Question;
import dash.security.CustomPermission;
import dash.security.GenericAclController;

/**
 * An Example service layer implementation. Here is where all business logic
 * should be implemented. Create a GenericAclController for each type of object
 * you will be managing permissions for in this service.
 * 
 * @author Tyler.swensen@gmail.com
 *
 */
@Component("formResponseService")
public class FormResponseServiceDbAccessImpl extends ApplicationObjectSupport
		implements FormResponseService {

	@Autowired
	FormResponseDao formResponseDao;
	
	@Autowired
	FormService formService;

	@Autowired
	private GenericAclController<FormResponse> aclController;

	/********************* Create related methods implementation ***********************/
	@Override
	@Transactional
	public Long createFormResponse(FormResponse formResponse)
			throws AppException {

		//Generate empty form response entries for each question
		Set<Entry> entries= new HashSet<Entry>();
		Form form = formService.getFormById(formResponse.getForm_id());
		Set<Question> questions= form.getQuestions();
		
		if(questions != null && formResponse.getEntries().isEmpty()){
			for (Question question : questions) {
			    Entry entry= new Entry();
			    entry.setQuestion_id(question.getQuestion_id());
				entries.add(entry);
			}
			formResponse.setEntries(entries);
		}
		
		
		
		long formResponseId = formResponseDao
				.createFormResponse(new FormResponseEntity(formResponse));
		formResponse.setId(formResponseId);
		aclController.createACL(formResponse);
		aclController.createAce(formResponse, CustomPermission.READ);
		aclController.createAce(formResponse, CustomPermission.WRITE);
		aclController.createAce(formResponse, CustomPermission.DELETE);
		return formResponseId;
	}

	@Override
	@Transactional
	public void createFormResponses(List<FormResponse> formResponses)
			throws AppException {
		for (FormResponse formResponse : formResponses) {
			createFormResponse(formResponse);
		}
	}

	// ******************** Read related methods implementation
	// **********************
	@Override
	public List<FormResponse> getFormResponses(int numberOfFormResponses,
			Long startIndex) throws AppException {

		List<FormResponseEntity> formResponses = formResponseDao
				.getFormResponses(numberOfFormResponses, startIndex);
		return getFormResponsesFromEntities(formResponses);
	}
	
	public List<FormResponse> getMyFormResponses(int numberOfFormResponses,
			Long startIndex)throws AppException{
		
		//TODO: Instead of returning the getAll function we should do a lookup
		// in the ACL tables to compile a list of all objects where the user has
		// the required permission and then do a select query to build a collection
		// of only those objects.
		return getFormResponses(numberOfFormResponses, startIndex);
	}

	@Override
	public FormResponse getFormResponseById(Long id) throws AppException {
		FormResponseEntity formResponseById = formResponseDao
				.getFormResponseById(id);
		if (formResponseById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404, "The formResponse you requested with id " + id
							+ " was not found in the database",
					"Verify the existence of the formResponse with the id "
							+ id + " in the database",
					AppConstants.DASH_POST_URL);
		}

		return new FormResponse(formResponseDao.getFormResponseById(id));
	}
	
	@Override
	public List<FormResponse> getFormResponsesByFormId(Long id, int numberOfFormResponses, int page) throws AppException {
		List<FormResponseEntity> formResponsesByFormId = formResponseDao
				.getFormResponsesByFormId(id, numberOfFormResponses, page);
		if (formResponsesByFormId == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404, "The formResponse you requested with id " + id
							+ " was not found in the database",
					"Verify the existence of the formResponse with the id "
							+ id + " in the database",
					AppConstants.DASH_POST_URL);
		}
		
		return getFormResponsesFromEntities(formResponsesByFormId);
	}

	private List<FormResponse> getFormResponsesFromEntities(
			List<FormResponseEntity> formResponseEntities) {
		List<FormResponse> response = new ArrayList<FormResponse>();
		for (FormResponseEntity formResponseEntity : formResponseEntities) {
			response.add(new FormResponse(formResponseEntity));
		}

		return response;
	}

	/**
	 *  save uploaded file to new location
	 */
	public void uploadFile(InputStream uploadedInputStream,
			String uploadedFileLocation)
			throws AppException {

		try {
			File file = new File(uploadedFileLocation);
			file.getParentFile().mkdirs();
			OutputStream out = new FileOutputStream(file);
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			throw new AppException(
					Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), 500,
					"Could not upload file due to IOException", "\n\n"
							+ e.getMessage(), AppConstants.DASH_POST_URL);
		}

	}

	public File getUploadFile(String uploadedFileLocation) throws AppException {
		return new File(uploadedFileLocation);
	}

	// public List<FormResponse> getRecentFormResponses(int
	// numberOfDaysToLookBack) {
	// List<FormResponseEntity> recentFormResponses = formResponseDao
	// .getRecentFormResponses(numberOfDaysToLookBack);
	//
	// return getFormResponsesFromEntities(recentFormResponses);
	// }

	@Override
	public int getNumberOfFormResponses() {
		int totalNumber = formResponseDao.getNumberOfFormResponses();

		return totalNumber;

	}

	/********************* UPDATE-related methods implementation ***********************/

	@Override
	@Transactional
	public void updateFullyFormResponse(FormResponse formResponse)
			throws AppException {

		FormResponse verifyFormResponseExistenceById = verifyFormResponseExistenceById(formResponse
				.getId());
		if (verifyFormResponseExistenceById == null) {
			throw new AppException(
					Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ formResponse.getId(), AppConstants.DASH_POST_URL);
		}
		copyAllProperties(verifyFormResponseExistenceById, formResponse);

		formResponseDao.updateFormResponse(new FormResponseEntity(
				verifyFormResponseExistenceById));

	}

	private void copyAllProperties(
			FormResponse verifyFormResponseExistenceById,
			FormResponse formResponse) {
		// If you would like to allow null values use the following line.
		// Reference PostServiceImpl in the VolunteerManagementApp for more
		// details.
		// BeanUtilsBean withNull=new BeanUtilsBean();

		// Assuming the NullAwareBeanUtilsBean is sufficient this code can be
		// used.
		BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifyFormResponseExistenceById,
					formResponse);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/********************* DELETE-related methods implementation ***********************/

	@Override
	@Transactional
	public void deleteFormResponse(FormResponse formResponse) {

		formResponseDao.deleteFormResponseById(formResponse);
		aclController.deleteACL(formResponse);

	}

	@Override
	@Transactional
	// TODO: This shouldn't exist? If it must, then it needs to accept a list of
	// FormResponses to delete
	public void deleteFormResponses() {
		formResponseDao.deleteFormResponses();
	}

	@Override
	public FormResponse verifyFormResponseExistenceById(Long id) {
		FormResponseEntity formResponseById = formResponseDao
				.getFormResponseById(id);
		if (formResponseById == null) {
			return null;
		} else {
			return new FormResponse(formResponseById);
		}
	}

	@Override
	@Transactional
	public void updatePartiallyFormResponse(FormResponse formResponse)
			throws AppException {
		// do a validation to verify existence of the resource
		FormResponse verifyFormResponseExistenceById = verifyFormResponseExistenceById(formResponse
				.getId());
		if (verifyFormResponseExistenceById == null) {
			throw new AppException(
					Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ formResponse.getId(), AppConstants.DASH_POST_URL);
		}
		copyPartialProperties(verifyFormResponseExistenceById, formResponse);
		formResponseDao.updateFormResponse(new FormResponseEntity(
				verifyFormResponseExistenceById));

	}

	private void copyPartialProperties(
			FormResponse verifyFormResponseExistenceById,
			FormResponse formResponse) {

		BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifyFormResponseExistenceById,
					formResponse);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void deleteUploadFile(String uploadedFileLocation) throws AppException {
		Path path = Paths.get(uploadedFileLocation);
		try {
		    Files.delete(path);
		} catch (NoSuchFileException x) {
			x.printStackTrace();
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"NoSuchFileException thrown, Operation unsuccesful.", "Please ensure the file you are attempting to"
					+ " delete exists at "+path+".", AppConstants.DASH_POST_URL);
			
					
		} catch (DirectoryNotEmptyException x) {
			x.printStackTrace();
			throw new AppException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
					404,
					"DirectoryNotEmptyException thrown, operation unsuccesful.", "This method should not attempt to delete,"
							+ " This should be considered a very serious error. Occured at "+path+".",
					AppConstants.DASH_POST_URL);
		} catch (IOException x) {
			x.printStackTrace();
			throw new AppException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
					500,
					"IOException thrown and the designated file was not deleted.", 
					" Permission problems occured at "+path+".",
					AppConstants.DASH_POST_URL);
		}
		
	}

	@Override
	public List<String> getFileNames(FormResponse formResponse) {
		List<String> results = new ArrayList<String>();
		
		File[] files = new File(AppConstants.APPLICATION_UPLOAD_LOCATION_FOLDER+"/" + formResponse.getDocument_folder()).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		if(files != null){
			for (File file : files) {
			    if (file.isFile()) {
			        results.add(file.getName());
			    }
			}
		}
		return results;
	}

	
}
