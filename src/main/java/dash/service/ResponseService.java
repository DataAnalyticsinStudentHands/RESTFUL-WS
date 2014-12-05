package dash.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import dash.errorhandling.AppException;
import dash.pojo.Response;

/**
 * Example service interface for a basic object.
 * 
 * This is where you set method/object level permissions using Spring annotations.
 * 
 * @author Tyler.swensen@gmail.com
 *
 */

public interface ResponseService {
	
	/*
	 * ******************** Create related methods *********************/
	
	/**
	 * Create a new response and set the current user as owner and manager.
	 * @param response
	 * @return
	 * @throws AppException
	 */
	public Long createResponse(Response response) throws AppException;

	/*
	 * Create multiple responses as ROOT, testing purposes only.
	 */
	@PreAuthorize("hasRole('ROLE_ROOT')")
	public void createResponses(List<Response> responses) throws AppException;

	/*
	 * ******************* Read related methods ********************
	 */
	/**
	 *
	 * @param orderByInsertionDate
	 *            - if set, it represents the order by criteria (ASC or DESC)
	 *            for displaying responses
	 * @param numberDaysToLookBack
	 *            - if set, it represents number of days to look back for responses,
	 *            null
	 * @return list with responses corresponding to search criteria
	 * @throws AppException
	 */
	//Enable post filter to restrict read access to a collection
	//@PostFilter("hasPermission(filterObject, 'READ')"
	public List<Response> getResponses(int numberOfResponses, Long startIndex) throws AppException;
	
	/**
	 * Returns a response given its id
	 *
	 * @param id
	 * @return
	 * @throws AppException
	 */
	
	//Enable the following line of code to restrict read access to a single object.
	//@PostAuthrorize("hasPermission(#returnObject, 'read')")
	public Response getResponseById(Long id) throws AppException;

	/*
	 * ******************** Update related methods **********************
	 */
	@PreAuthorize("hasPermission(#response, 'write') or hasRole('ROLE_ADMIN')")
	public void updateFullyResponse(Response response) throws AppException;

	@PreAuthorize("hasPermission(#response, 'write') or hasRole('ROLE_ADMIN')")
	public void updatePartiallyResponse(Response response) throws AppException;

	/*
	 * ******************** Delete related methods **********************
	 */


	@PreAuthorize("hasPermission(#response, 'delete') or hasRole('ROLE_ADMIN')")
	public void deleteResponse(Response response);
	/** removes all responses
	 * DO NOT USE, IMPROPERLY UPDATES ACL_OBJECT table
	 * Functional but does not destroy old acl's which doesnt hurt anything
	 * but they will take up space if this is commonly used */
	@PreAuthorize("hasRole('ROLE_ROOT')")
	public void deleteResponses();
	
	
	/*
	 * ******************** Helper methods **********************
	 */
	// TODO: This also should not exist, or it should be changed to
	// private/protected. Redundant
	// Could be made a boolean so it was not a security vulnerability
	public Response verifyResponseExistenceById(Long id);

	public int getNumberOfResponses();

}
