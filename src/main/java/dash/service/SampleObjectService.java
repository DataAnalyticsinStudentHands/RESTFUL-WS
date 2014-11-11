package dash.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import dash.errorhandling.AppException;
import dash.pojo.SampleObject;

/**
 * Example service interface for a basic object.
 * 
 * This is where you set method/object level permissions using Spring annotations.
 * 
 * @author Tyler.swensen@gmail.com
 *
 */

public interface SampleObjectService {
	
	/*
	 * ******************** Create related methods *********************/
	
	/**
	 * Create a new sampleObject and set the current user as owner and manager.
	 * @param sampleObject
	 * @return
	 * @throws AppException
	 */
	public Long createSampleObject(SampleObject sampleObject) throws AppException;

	/*
	 * Create multiple sampleObjects as ROOT, testing purposes only.
	 */
	@PreAuthorize("hasRole('ROLE_ROOT')")
	public void createSampleObjects(List<SampleObject> sampleObjects) throws AppException;

	/*
	 * ******************* Read related methods ********************
	 */
	/**
	 *
	 * @param orderByInsertionDate
	 *            - if set, it represents the order by criteria (ASC or DESC)
	 *            for displaying sampleObjects
	 * @param numberDaysToLookBack
	 *            - if set, it represents number of days to look back for sampleObjects,
	 *            null
	 * @return list with sampleObjects corresponding to search criteria
	 * @throws AppException
	 */
	//Enable post filter to restrict read access to a collection
	//@PostFilter("hasPermission(filterObject, 'READ')"
	public List<SampleObject> getSampleObjects(int numberOfSampleObjects, Long startIndex) throws AppException;
	
	/**
	 * Returns a sampleObject given its id
	 *
	 * @param id
	 * @return
	 * @throws AppException
	 */
	
	//Enable the following line of code to restrict read access to a single object.
	//@PostAuthrorize("hasPermission(#returnObject, 'read')")
	public SampleObject getSampleObjectById(Long id) throws AppException;

	/*
	 * ******************** Update related methods **********************
	 */
	@PreAuthorize("hasPermission(#sampleObject, 'write') or hasRole('ROLE_ADMIN')")
	public void updateFullySampleObject(SampleObject sampleObject) throws AppException;

	@PreAuthorize("hasPermission(#sampleObject, 'write') or hasRole('ROLE_ADMIN')")
	public void updatePartiallySampleObject(SampleObject sampleObject) throws AppException;

	/*
	 * ******************** Delete related methods **********************
	 */


	@PreAuthorize("hasPermission(#sampleObject, 'delete') or hasRole('ROLE_ADMIN')")
	public void deleteSampleObject(SampleObject sampleObject);
	/** removes all sampleObjects
	 * DO NOT USE, IMPROPERLY UPDATES ACL_OBJECT table
	 * Functional but does not destroy old acl's which doesnt hurt anything
	 * but they will take up space if this is commonly used */
	@PreAuthorize("hasRole('ROLE_ROOT')")
	public void deleteSampleObjects();
	
	
	/*
	 * ******************** Helper methods **********************
	 */
	// TODO: This also should not exist, or it should be changed to
	// private/protected. Redundant
	// Could be made a boolean so it was not a security vulnerability
	public SampleObject verifySampleObjectExistenceById(Long id);

	public int getNumberOfSampleObjects();

}
