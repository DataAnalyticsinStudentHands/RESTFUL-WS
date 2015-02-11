package dash.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import dash.errorhandling.AppException;
import dash.pojo.FileUpload;

/**
 * Example service interface for a basic object.
 * 
 * This is where you set method/object level permissions using Spring annotations.
 * 
 * @author Tyler.swensen@gmail.com
 *
 */

public interface FileUploadService {
	
	/*
	 * ******************** Create related methods *********************/
	
	/**
	 * Create a new fileUpload and set the current user as owner and manager.
	 * @param fileUpload
	 * @return
	 * @throws AppException
	 */
	public Long createFileUpload(FileUpload fileUpload, InputStream fileInputStream) throws AppException;
	
	@PreAuthorize("hasPermission(#fileUpload, 'write') or hasRole('ROLE_ADMIN')")
	public void uploadFile(InputStream uploadedInputStream,
			String uploadedFileLocation) throws AppException;

	/*
	 * Create multiple fileUploads as ROOT, testing purposes only.
	 */
	@PreAuthorize("hasRole('ROLE_ROOT')")
	public void createFileUploads(List<FileUpload> fileUploads) throws AppException;

	/*
	 * ******************* Read related methods ********************
	 */
	/**
	 *
	 * @param orderByInsertionDate
	 *            - if set, it represents the order by criteria (ASC or DESC)
	 *            for displaying fileUploads
	 * @param numberDaysToLookBack
	 *            - if set, it represents number of days to look back for fileUploads,
	 *            null
	 * @return list with fileUploads corresponding to search criteria
	 * @throws AppException
	 */
	//Enable post filter to restrict read access to a collection
	//@PostFilter("hasPermission(filterObject, 'READ')"
	public List<FileUpload> getFileUploads(int numberOfFileUploads, Long startIndex) throws AppException;
	
	/**
	 * Returns a fileUpload given its id
	 *
	 * @param id
	 * @return
	 * @throws AppException
	 */
	
	//Enable the following line of code to restrict read access to a single object.
	//@PostAuthrorize("hasPermission(returnObject, 'read')")
	public FileUpload getFileUploadById(Long id) throws AppException;
	
	@PreAuthorize("hasPermission(#fileUpload, 'read') or hasRole('ROLE_ADMIN')")
	public File getUploadFile(FileUpload fileUpload) throws AppException;

	/*
	 * ******************** Update related methods **********************
	 */
	@PreAuthorize("hasPermission(#fileUpload, 'write') or hasRole('ROLE_ADMIN')")
	public void updateFullyFileUpload(FileUpload fileUpload) throws AppException;

	@PreAuthorize("hasPermission(#fileUpload, 'write') or hasRole('ROLE_ADMIN')")
	public void updatePartiallyFileUpload(FileUpload fileUpload) throws AppException;

	/*
	 * ******************** Delete related methods **********************
	 */

	@PreAuthorize("hasPermission(#fileUpload, 'delete') or hasRole('ROLE_ADMIN')")
	public void deleteFileUpload(FileUpload fileUpload) throws AppException;
	/** removes all fileUploads
	 * DO NOT USE, IMPROPERLY UPDATES ACL_OBJECT table
	 * Functional but does not destroy old acl's which doesnt hurt anything
	 * but they will take up space if this is commonly used */
	@PreAuthorize("hasRole('ROLE_ROOT')")
	public void deleteFileUploads();
	

	/*
	 * ******************** Helper methods **********************
	 */
	// TODO: This also should not exist, or it should be changed to
	// private/protected. Redundant
	// Could be made a boolean so it was not a security vulnerability
	public FileUpload verifyFileUploadExistenceById(Long id);

	public int getNumberOfFileUploads();
		
}
