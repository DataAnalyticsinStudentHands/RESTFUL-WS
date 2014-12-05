package dash.service;

import java.lang.reflect.InvocationTargetException;
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
 * An Example service layer implementation.  Here is where all business logic should
 * be implemented. Create a GenericAclController for each type of object you will be
 * managing permissions for in this service.
 * 
 * @author Tyler.swensen@gmail.com
 *
 */
public class SampleObjectServiceDbAccessImpl extends ApplicationObjectSupport implements
SampleObjectService {

	@Autowired
	SampleObjectDao sampleObjectDao;

	@Autowired
	private GenericAclController<SampleObject> aclController;

	/********************* Create related methods implementation ***********************/
	@Override
	@Transactional
	public Long createSampleObject(SampleObject sampleObject) throws AppException {

		long sampleObjectId = sampleObjectDao.createSampleObject(new SampleObjectEntity(sampleObject));
		sampleObject.setId(sampleObjectId);
		aclController.createACL(sampleObject);
		aclController.createAce(sampleObject, CustomPermission.READ);
		aclController.createAce(sampleObject, CustomPermission.WRITE);
		aclController.createAce(sampleObject, CustomPermission.DELETE);
		return sampleObjectId;
	}

	@Override
	@Transactional
	public void createSampleObjects(List<SampleObject> sampleObjects) throws AppException {
		for (SampleObject sampleObject : sampleObjects) {
			createSampleObject(sampleObject);
		}
	}


	// ******************** Read related methods implementation **********************
	@Override
	public List<SampleObject> getSampleObjects(int numberOfSampleObjects, Long startIndex) throws AppException{
		
		List<SampleObjectEntity> sampleObjects = sampleObjectDao.getSampleObjects(numberOfSampleObjects, startIndex);
		return getSampleObjectsFromEntities(sampleObjects);
	}
	

	@Override
	public SampleObject getSampleObjectById(Long id) throws AppException {
		SampleObjectEntity sampleObjectById = sampleObjectDao.getSampleObjectById(id);
		if (sampleObjectById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The sampleObject you requested with id " + id
					+ " was not found in the database",
					"Verify the existence of the sampleObject with the id " + id
					+ " in the database", AppConstants.DASH_POST_URL);
		}

		return new SampleObject(sampleObjectDao.getSampleObjectById(id));
	}

	private List<SampleObject> getSampleObjectsFromEntities(List<SampleObjectEntity> sampleObjectEntities) {
		List<SampleObject> response = new ArrayList<SampleObject>();
		for (SampleObjectEntity sampleObjectEntity : sampleObjectEntities) {
			response.add(new SampleObject(sampleObjectEntity));
		}

		return response;
	}
	

	
	

//	public List<SampleObject> getRecentSampleObjects(int numberOfDaysToLookBack) {
//		List<SampleObjectEntity> recentSampleObjects = sampleObjectDao
//				.getRecentSampleObjects(numberOfDaysToLookBack);
//
//		return getSampleObjectsFromEntities(recentSampleObjects);
//	}

	@Override
	public int getNumberOfSampleObjects() {
		int totalNumber = sampleObjectDao.getNumberOfSampleObjects();

		return totalNumber;

	}



	/********************* UPDATE-related methods implementation ***********************/
	
	@Override
	@Transactional
	public void updateFullySampleObject(SampleObject sampleObject) throws AppException {
		
		
		
		SampleObject verifySampleObjectExistenceById = verifySampleObjectExistenceById(sampleObject
				.getId());
		if (verifySampleObjectExistenceById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ sampleObject.getId(),
							AppConstants.DASH_POST_URL);
		}
		copyAllProperties(verifySampleObjectExistenceById, sampleObject);

		sampleObjectDao.updateSampleObject(new SampleObjectEntity(verifySampleObjectExistenceById));

	}

	private void copyAllProperties(SampleObject verifySampleObjectExistenceById, SampleObject sampleObject) {
		//If you would like to allow null values use the following line.
		//Reference PostServiceImpl in the VolunteerManagementApp for more details.
		//BeanUtilsBean withNull=new BeanUtilsBean();
		
		//Assuming the NullAwareBeanUtilsBean is sufficient this code can be used.
		BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifySampleObjectExistenceById, sampleObject);
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
		SampleObjectEntity sampleObjectById = sampleObjectDao.getSampleObjectById(id);
		if (sampleObjectById == null) {
			return null;
		} else {
			return new SampleObject(sampleObjectById);
		}
	}

	@Override
	@Transactional
	public void updatePartiallySampleObject(SampleObject sampleObject) throws AppException {
		//do a validation to verify existence of the resource
		SampleObject verifySampleObjectExistenceById = verifySampleObjectExistenceById(sampleObject.getId());
		if (verifySampleObjectExistenceById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ sampleObject.getId(), AppConstants.DASH_POST_URL);
		}
		copyPartialProperties(verifySampleObjectExistenceById, sampleObject);
		sampleObjectDao.updateSampleObject(new SampleObjectEntity(verifySampleObjectExistenceById));

	}

	private void copyPartialProperties(SampleObject verifySampleObjectExistenceById, SampleObject sampleObject) {

		BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifySampleObjectExistenceById, sampleObject);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
