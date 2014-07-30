package dash.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import dash.errorhandling.AppException;
import dash.pojo.SimpleObject;

public interface SimpleObjectService {
	/*
	 * ******************** Create related methods **********************
	 *
	 *Create a new image and set the current user as owner and manager.
	 */
	public Long createSimpleObject(SimpleObject image) throws AppException;	

	/*
	 * ******************* Read related methods ********************
	 */
	/**
	 *
	 * @param orderByInsertionDate
	 *            - if set, it represents the order by criteria (ASC or DESC)
	 *            for displaying objects
	 * @return list with objects corresponding to search criteria
	 * @throws AppException
	 */
	
	public List<SimpleObject> getSimpleObjects(String orderByInsertionDate) throws AppException;

	/**
	 * Returns a simple object given its id
	 *
	 * @param id
	 * @return
	 * @throws AppException
	 */
	
	public SimpleObject getSimpleObjectById(Long id) throws AppException;
	
	/*
	 * ******************** Update related methods **********************
	 */
	@PreAuthorize("hasPermission(#user, 'WRITE') or hasRole('ROLE_ADMIN')")
	public void updateFullySimpleObject(SimpleObject simpleObject) throws AppException;

	@PreAuthorize("hasPermission(#user, 'WRITE') or hasRole('ROLE_ADMIN')")
	public void updatePartiallySimpleObject(SimpleObject simpleObject) throws AppException;
	
	/*
	 * ******************** Delete related methods **********************
	 */
	@PreAuthorize("hasPermission(#user, 'DELETE') or hasRole('ROLE_ADMIN')")
	public void deleteSimpleObject(SimpleObject simpleObject);
	
	/** removes all simpleobjects
	 * DO NOT USE, IMPROPERLY UPDATES ACL_OBJECT table
	 * Functional but does not destroy old acl's which doesnt hurt anything
	 * but they will take up space if this is commonly used */
	@PreAuthorize("hasRole('ROLE_ROOT')")
	public void deleteSimpleObjects();	

	/*
	 * ******************** Helper methods **********************
	 */
	// TODO: This also should not exist, or it should be changed to
	// private/protected. Redundant
	// Could be made a boolean so it was not a security vulnerability
	public SimpleObject verifySimpleObjectExistenceById(Long id);	

}
