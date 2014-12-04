package dash.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

import dash.dao.SimpleObjectDao;
import dash.dao.SimpleObjectEntity;
import dash.errorhandling.AppException;
import dash.filters.AppConstants;
import dash.helpers.NullAwareBeanUtilsBean;
import dash.pojo.SimpleObject;
import dash.security.CustomPermission;
import dash.security.GenericAclController;

@Component("simpleObjectService")
public class SimpleObjectServiceDbAccessImpl extends ApplicationObjectSupport implements
SimpleObjectService {

	@Autowired
	SimpleObjectDao simpleObjectDao;

	@Autowired
	private MutableAclService mutableAclService;

	@Autowired
	private GenericAclController<SimpleObject> aclController;


	/********************* Create related methods implementation ***********************/
	@Override
	@Transactional
	public Long createSimpleObject(SimpleObject simpleObject) throws AppException {

		validateInputForCreation(simpleObject);

		//verify existence of resource in the db (feed must be unique)
		SimpleObjectEntity simpleObjectByName = simpleObjectDao.getSimpleObjectByName(simpleObject.getName());
		if (simpleObjectByName != null) {
			throw new AppException(
					Response.Status.CONFLICT.getStatusCode(),
					409,
					"Object with name already existing in the database with the id "
							+ simpleObjectByName.getId(),
							"Please verify that the name are properly generated",
							AppConstants.DASH_POST_URL);
		}

		long simpleObjectId = simpleObjectDao.createSimpleObject(new SimpleObjectEntity(simpleObject));
		simpleObject.setId(simpleObjectId);
		
		aclController.createACL(simpleObject);
		aclController.createAce(simpleObject, CustomPermission.READ);
		aclController.createAce(simpleObject, CustomPermission.WRITE);
		aclController.createAce(simpleObject, CustomPermission.DELETE);
		
		return simpleObjectId;
	}

	private void validateInputForCreation(SimpleObject simpleObject) throws AppException {
		if (simpleObject.getName() == null) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "Provided data not sufficient for insertion",
					"Please verify that the name is properly generated/set",
					AppConstants.DASH_POST_URL);
		}		
		//etc...
	}

		// ******************** Read related methods implementation **********************
	@Override
	public List<SimpleObject> getSimpleObjects(String orderByInsertionDate) throws AppException {

		if(isOrderByInsertionDateParameterValid(orderByInsertionDate)){
			throw new AppException(
					Response.Status.BAD_REQUEST.getStatusCode(),
					400,
					"Please set either ASC or DESC for the orderByInsertionDate parameter",
					null, AppConstants.DASH_POST_URL);
		}
		List<SimpleObjectEntity> simpleObjects = simpleObjectDao.getSimpleObjects(orderByInsertionDate);

		return getSimpleObjectsFromEntities(simpleObjects);
	}

	private boolean isOrderByInsertionDateParameterValid(
			String orderByInsertionDate) {
		return orderByInsertionDate!=null
				&& !("ASC".equalsIgnoreCase(orderByInsertionDate) || "DESC".equalsIgnoreCase(orderByInsertionDate));
	}

	@Override
	public SimpleObject getSimpleObjectById(Long id) throws AppException {
		SimpleObjectEntity simpleObjectById = simpleObjectDao.getSimpleObjectById(id);
		if (simpleObjectById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The object you requested with id " + id
					+ " was not found in the database",
					"Verify the existence of the object with the id " + id
					+ " in the database", AppConstants.DASH_POST_URL);
		}

		return new SimpleObject(simpleObjectDao.getSimpleObjectById(id));
	}

	private List<SimpleObject> getSimpleObjectsFromEntities(List<SimpleObjectEntity> simpleObjectEntities) {
		List<SimpleObject> response = new ArrayList<SimpleObject>();
		for (SimpleObjectEntity simpleObjectEntity : simpleObjectEntities) {
			response.add(new SimpleObject(simpleObjectEntity));
		}

		return response;
	}

	/********************* UPDATE-related methods implementation ***********************/
	@Override
	@Transactional
	public void updateFullySimpleObject(SimpleObject simpleObject) throws AppException {
		//do a validation to verify FULL update with PUT
		if (isFullUpdate(simpleObject)) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(),
					400,
					"Please specify all properties for Full UPDATE",
					"required properties - name",
					AppConstants.DASH_POST_URL);
		}

		SimpleObject verifySimpleObjectExistenceById = verifySimpleObjectExistenceById(simpleObject
				.getId());
		if (verifySimpleObjectExistenceById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ simpleObject.getId(),
							AppConstants.DASH_POST_URL);
		}

		simpleObjectDao.updateSimpleObject(new SimpleObjectEntity(simpleObject));
	}

	/**
	 * Verifies the "completeness" of simple object resource sent over the wire
	 *
	 * @param SimpleObject
	 * @return
	 */
	private boolean isFullUpdate(SimpleObject simpleObject) {
		return simpleObject.getId() == null
				|| simpleObject.getName() == null;
	}

	/********************* DELETE-related methods implementation ***********************/

	@Override
	@Transactional
	public void deleteSimpleObject(SimpleObject simpleObject) {

		simpleObjectDao.deleteSimpleObject(simpleObject);
		aclController.deleteACL(simpleObject);

	}

	@Override
	@Transactional
	// TODO: This shouldn't exist? If it must, then it needs to accept a list of
	// Simple Objects to delete
	public void deleteSimpleObjects() {
		simpleObjectDao.deleteSimpleObjects();
	}

	@Override
	// TODO: This doesn't need to exist. It is the exact same thing as
	// getSimpleObjectById(Long)
	public SimpleObject verifySimpleObjectExistenceById(Long id) {
		SimpleObjectEntity simpleObjectById = simpleObjectDao.getSimpleObjectById(id);
		if (simpleObjectById == null) {
			return null;
		} else {
			return new SimpleObject(simpleObjectById);
		}
	}

	@Override
	@Transactional
	public void updatePartiallySimpleObject(SimpleObject simpleObject) throws AppException {
		//do a validation to verify existence of the resource
		SimpleObject verifySimpleObjectExistenceById = verifySimpleObjectExistenceById(simpleObject.getId());
		if (verifySimpleObjectExistenceById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ simpleObject.getId(), AppConstants.DASH_POST_URL);
		}
		copyPartialProperties(verifySimpleObjectExistenceById, simpleObject);
		simpleObjectDao.updateSimpleObject(new SimpleObjectEntity(verifySimpleObjectExistenceById));

	}

	private void copyPartialProperties(SimpleObject verifySimpleObjectExistenceById, SimpleObject simpleObject) {

		BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifySimpleObjectExistenceById, simpleObject);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	

}
