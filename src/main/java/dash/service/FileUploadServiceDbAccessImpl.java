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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dash.dao.FileUploadDao;
import dash.errorhandling.AppException;
import dash.filters.AppConstants;
import dash.helpers.NullAwareBeanUtilsBean;
import dash.pojo.FileUpload;
import dash.pojo.Form;
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
@Component("fileUploadService")
public class FileUploadServiceDbAccessImpl extends ApplicationObjectSupport
		implements FileUploadService {

	@Autowired
	FileUploadDao fileUploadDao;

	@Autowired
	private GenericAclController<FileUpload> aclController;

	/********************* Create related methods implementation ***********************/
	@Override
	@Transactional
	public Long createFileUpload(FileUpload fileUpload, InputStream fileInputStream)
			throws AppException {
		
		uploadFile(fileInputStream, fileUpload.getPath());

		long fileUploadId = fileUploadDao
				.createFileUpload(fileUpload);
		fileUpload.setId(fileUploadId);
		aclController.createACL(fileUpload);
		aclController.createAce(fileUpload, CustomPermission.READ);
		aclController.createAce(fileUpload, CustomPermission.WRITE);
		aclController.createAce(fileUpload, CustomPermission.DELETE);
		return fileUploadId;
	}

	@Override
	@Transactional
	//Not functional for this resource
	public void createFileUploads(List<FileUpload> fileUploads)
			throws AppException {
		for (FileUpload fileUpload : fileUploads) {
			//createFileUpload(fileUpload);
		}
	}

	// ******************** Read related methods implementation
	// **********************
	@Override
	public List<FileUpload> getFileUploads(int numberOfFileUploads,
			Long startIndex) throws AppException {

		List<FileUpload> fileUploads = fileUploadDao
				.getFileUploads(numberOfFileUploads, startIndex);
		return fileUploads;
	}

	@Override
	public FileUpload getFileUploadById(Long id) throws AppException {
		FileUpload fileUploadById = fileUploadDao
				.getFileUploadById(id);
		if (fileUploadById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404, "The fileUpload you requested with id " + id
							+ " was not found in the database",
					"Verify the existence of the fileUpload with the id "
							+ id + " in the database",
					AppConstants.DASH_POST_URL);
		}

		return fileUploadDao.getFileUploadById(id);
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

	public File getUploadFile(FileUpload fileUpload, Form form) throws AppException {
		
		return new File(fileUpload.getPath());
	}

	// public List<FileUpload> getRecentFileUploads(int
	// numberOfDaysToLookBack) {
	// List<FileUploadEntity> recentFileUploads = fileUploadDao
	// .getRecentFileUploads(numberOfDaysToLookBack);
	//
	// return getFileUploadsFromEntities(recentFileUploads);
	// }

	@Override
	public int getNumberOfFileUploads() {
		int totalNumber = fileUploadDao.getNumberOfFileUploads();

		return totalNumber;

	}

	/********************* UPDATE-related methods implementation ***********************/

	@Override
	@Transactional
	public void updateFullyFileUpload(FileUpload fileUpload)
			throws AppException {

		FileUpload verifyFileUploadExistenceById = verifyFileUploadExistenceById(fileUpload
				.getId());
		if (verifyFileUploadExistenceById == null) {
			throw new AppException(
					Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ fileUpload.getId(), AppConstants.DASH_POST_URL);
		}
		copyAllProperties(verifyFileUploadExistenceById, fileUpload);

		fileUploadDao.updateFileUpload(fileUpload);

	}

	private void copyAllProperties(
			FileUpload verifyFileUploadExistenceById,
			FileUpload fileUpload) {
		// If you would like to allow null values use the following line.
		// Reference PostServiceImpl in the VolunteerManagementApp for more
		// details.
		// BeanUtilsBean withNull=new BeanUtilsBean();

		// Assuming the NullAwareBeanUtilsBean is sufficient this code can be
		// used.
		BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifyFileUploadExistenceById,
					fileUpload);
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
	public void deleteFileUpload(FileUpload fileUpload, Form form) throws AppException{
		
		//first remove the actual file
		Path path = Paths.get(fileUpload.getPath()+fileUpload.getFile_name());
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
		
		//second delete the file_upload entry in the DB
		fileUploadDao.deleteFileUploadById(fileUpload);
		aclController.deleteACL(fileUpload);

	}

	@Override
	@Transactional
	// TODO: This shouldn't exist? If it must, then it needs to accept a list of
	// FileUploads to delete
	public void deleteFileUploads() {
		fileUploadDao.deleteFileUploads();
	}

	@Override
	public FileUpload verifyFileUploadExistenceById(Long id) {
		FileUpload fileUploadById = fileUploadDao
				.getFileUploadById(id);
		if (fileUploadById == null) {
			return null;
		} else {
			return fileUploadById;
		}
	}

	@Override
	@Transactional
	public void updatePartiallyFileUpload(FileUpload fileUpload)
			throws AppException {
		// do a validation to verify existence of the resource
		FileUpload verifyFileUploadExistenceById = verifyFileUploadExistenceById(fileUpload
				.getId());
		if (verifyFileUploadExistenceById == null) {
			throw new AppException(
					Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ fileUpload.getId(), AppConstants.DASH_POST_URL);
		}
		

	}

	


}
