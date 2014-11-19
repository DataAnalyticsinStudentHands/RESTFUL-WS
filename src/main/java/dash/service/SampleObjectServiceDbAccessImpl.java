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
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.transaction.annotation.Transactional;

import dash.dao.SampleObjectDao;
import dash.dao.SampleObjectEntity;
import dash.errorhandling.AppException;
import dash.filters.AppConstants;
import dash.helpers.NullAwareBeanUtilsBean;
import dash.pojo.SampleObject;
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
public class SampleObjectServiceDbAccessImpl extends ApplicationObjectSupport
		implements SampleObjectService {

	@Autowired
	SampleObjectDao sampleObjectDao;

	@Autowired
	private GenericAclController<SampleObject> aclController;

	/********************* Create related methods implementation ***********************/
	@Override
	@Transactional
	public Long createSampleObject(SampleObject sampleObject)
			throws AppException {

		long sampleObjectId = sampleObjectDao
				.createSampleObject(new SampleObjectEntity(sampleObject));
		sampleObject.setId(sampleObjectId);
		aclController.createACL(sampleObject);
		aclController.createAce(sampleObject, CustomPermission.READ);
		aclController.createAce(sampleObject, CustomPermission.WRITE);
		aclController.createAce(sampleObject, CustomPermission.DELETE);
		return sampleObjectId;
	}

	@Override
	@Transactional
	public void createSampleObjects(List<SampleObject> sampleObjects)
			throws AppException {
		for (SampleObject sampleObject : sampleObjects) {
			createSampleObject(sampleObject);
		}
	}

	// ******************** Read related methods implementation
	// **********************
	@Override
	public List<SampleObject> getSampleObjects(int numberOfSampleObjects,
			Long startIndex) throws AppException {

		List<SampleObjectEntity> sampleObjects = sampleObjectDao
				.getSampleObjects(numberOfSampleObjects, startIndex);
		return getSampleObjectsFromEntities(sampleObjects);
	}

	@Override
	public SampleObject getSampleObjectById(Long id) throws AppException {
		SampleObjectEntity sampleObjectById = sampleObjectDao
				.getSampleObjectById(id);
		if (sampleObjectById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404, "The sampleObject you requested with id " + id
							+ " was not found in the database",
					"Verify the existence of the sampleObject with the id "
							+ id + " in the database",
					AppConstants.DASH_POST_URL);
		}

		return new SampleObject(sampleObjectDao.getSampleObjectById(id));
	}

	private List<SampleObject> getSampleObjectsFromEntities(
			List<SampleObjectEntity> sampleObjectEntities) {
		List<SampleObject> response = new ArrayList<SampleObject>();
		for (SampleObjectEntity sampleObjectEntity : sampleObjectEntities) {
			response.add(new SampleObject(sampleObjectEntity));
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

	// public List<SampleObject> getRecentSampleObjects(int
	// numberOfDaysToLookBack) {
	// List<SampleObjectEntity> recentSampleObjects = sampleObjectDao
	// .getRecentSampleObjects(numberOfDaysToLookBack);
	//
	// return getSampleObjectsFromEntities(recentSampleObjects);
	// }

	@Override
	public int getNumberOfSampleObjects() {
		int totalNumber = sampleObjectDao.getNumberOfSampleObjects();

		return totalNumber;

	}

	/********************* UPDATE-related methods implementation ***********************/

	@Override
	@Transactional
	public void updateFullySampleObject(SampleObject sampleObject)
			throws AppException {

		SampleObject verifySampleObjectExistenceById = verifySampleObjectExistenceById(sampleObject
				.getId());
		if (verifySampleObjectExistenceById == null) {
			throw new AppException(
					Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ sampleObject.getId(), AppConstants.DASH_POST_URL);
		}
		copyAllProperties(verifySampleObjectExistenceById, sampleObject);

		sampleObjectDao.updateSampleObject(new SampleObjectEntity(
				verifySampleObjectExistenceById));

	}

	private void copyAllProperties(
			SampleObject verifySampleObjectExistenceById,
			SampleObject sampleObject) {
		// If you would like to allow null values use the following line.
		// Reference PostServiceImpl in the VolunteerManagementApp for more
		// details.
		// BeanUtilsBean withNull=new BeanUtilsBean();

		// Assuming the NullAwareBeanUtilsBean is sufficient this code can be
		// used.
		BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifySampleObjectExistenceById,
					sampleObject);
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
	public void deleteSampleObject(SampleObject sampleObject) {

		sampleObjectDao.deleteSampleObjectById(sampleObject);
		aclController.deleteACL(sampleObject);

	}

	@Override
	@Transactional
	// TODO: This shouldn't exist? If it must, then it needs to accept a list of
	// SampleObjects to delete
	public void deleteSampleObjects() {
		sampleObjectDao.deleteSampleObjects();
	}

	@Override
	public SampleObject verifySampleObjectExistenceById(Long id) {
		SampleObjectEntity sampleObjectById = sampleObjectDao
				.getSampleObjectById(id);
		if (sampleObjectById == null) {
			return null;
		} else {
			return new SampleObject(sampleObjectById);
		}
	}

	@Override
	@Transactional
	public void updatePartiallySampleObject(SampleObject sampleObject)
			throws AppException {
		// do a validation to verify existence of the resource
		SampleObject verifySampleObjectExistenceById = verifySampleObjectExistenceById(sampleObject
				.getId());
		if (verifySampleObjectExistenceById == null) {
			throw new AppException(
					Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ sampleObject.getId(), AppConstants.DASH_POST_URL);
		}
		copyPartialProperties(verifySampleObjectExistenceById, sampleObject);
		sampleObjectDao.updateSampleObject(new SampleObjectEntity(
				verifySampleObjectExistenceById));

	}

	private void copyPartialProperties(
			SampleObject verifySampleObjectExistenceById,
			SampleObject sampleObject) {

		BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifySampleObjectExistenceById,
					sampleObject);
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
	public List<String> getFileNames(SampleObject sampleObject) {
		List<String> results = new ArrayList<String>();
		
		File[] files = new File(AppConstants.APPLICATION_UPLOAD_LOCATION_FOLDER+"/" + sampleObject.getDocument_folder()).listFiles();
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
