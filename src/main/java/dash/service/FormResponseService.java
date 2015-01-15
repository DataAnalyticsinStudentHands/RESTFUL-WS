package dash.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import dash.errorhandling.AppException;
import dash.pojo.FormResponse;
import dash.pojo.SampleObject;

/**
 * Example service interface for a basic object.
 * 
 * This is where you set method/object level permissions using Spring annotations.
 * 
 * @author Tyler.swensen@gmail.com
 *
 */

public interface FormResponseService {
	
	/*
	 * ******************** Create related methods *********************/
	
	/**
	 * Create a new formResponse and set the current user as owner and manager.
	 * @param formResponse
	 * @return
	 * @throws AppException
	 */
	public Long createFormResponse(FormResponse formResponse) throws AppException;

	/*
	 * Create multiple formResponses as ROOT, testing purposes only.
	 */
	@PreAuthorize("hasRole('ROLE_ROOT')")
	public void createFormResponses(List<FormResponse> formResponses) throws AppException;

	/*
	 * ******************* Read related methods ********************
	 */
	/**
	 *
	 * @param orderByInsertionDate
	 *            - if set, it represents the order by criteria (ASC or DESC)
	 *            for displaying formResponses
	 * @param numberDaysToLookBack
	 *            - if set, it represents number of days to look back for formResponses,
	 *            null
	 * @return list with formResponses corresponding to search criteria
	 * @throws AppException
	 */
	//Enable post filter to restrict read access to a collection
	@PostFilter("hasPermission(filterObject, 'READ') or hasRole('ROLE_ADMIN')")
	public List<FormResponse> getFormResponses(int numberOfFormResponses, Long startIndex) throws AppException;
	
	@PostFilter("hasPermission(filterObject, 'WRITE')")
	public List<FormResponse> getMyFormResponses(int numberOfFormResponses, Long startIndex) throws AppException;
	/**
	 * Returns a formResponse given its id
	 *
	 * @param id
	 * @return
	 * @throws AppException
	 */
	
	//Enable the following line of code to restrict read access to a single object.
	@PostAuthorize("hasPermission(#returnObject, 'read') or hasRole('ROLE_ADMIN')")
	public FormResponse getFormResponseById(Long id) throws AppException;
	
	@PostFilter("hasPermission(filterObject, 'read') or hasRole('ROLE_ADMIN')")
	public List<FormResponse> getFormResponsesByFormId(Long id, int numberOfFormResponses, int page) throws AppException;

	/*
	 * ******************** Update related methods **********************
	 */
	@PreAuthorize("hasPermission(#formResponse, 'write') or hasRole('ROLE_ADMIN')")
	public void updateFullyFormResponse(FormResponse formResponse) throws AppException;

	@PreAuthorize("hasPermission(#formResponse, 'write') or hasRole('ROLE_ADMIN')")
	public void updatePartiallyFormResponse(FormResponse formResponse) throws AppException;

	/*
	 * ******************** Delete related methods **********************
	 */


	@PreAuthorize("hasPermission(#formResponse, 'delete') or hasRole('ROLE_ADMIN')")
	public void deleteFormResponse(FormResponse formResponse);
	/** removes all formResponses
	 * DO NOT USE, IMPROPERLY UPDATES ACL_OBJECT table
	 * Functional but does not destroy old acl's which doesnt hurt anything
	 * but they will take up space if this is commonly used */
	@PreAuthorize("hasRole('ROLE_ROOT')")
	public void deleteFormResponses();
	
	
	/*
	 * ******************** Helper methods **********************
	 */
	// TODO: This also should not exist, or it should be changed to
	// private/protected. Redundant
	// Could be made a boolean so it was not a security vulnerability
	public FormResponse verifyFormResponseExistenceById(Long id);

	public int getNumberOfFormResponses();

	public void uploadFile(InputStream uploadedInputStream,
			String uploadedFileLocation) throws AppException;

	public List<String> getFileNames(FormResponse formResponse)throws AppException;

	public Object getUploadFile(String uploadedFileLocation)throws AppException;

	public void deleteUploadFile(String uploadedFileLocation)throws AppException;

}
