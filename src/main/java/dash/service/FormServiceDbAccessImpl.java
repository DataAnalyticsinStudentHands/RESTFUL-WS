package dash.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.transaction.annotation.Transactional;

import dash.dao.FormDao;
import dash.dao.FormEntity;
import dash.errorhandling.AppException;
import dash.filters.AppConstants;
import dash.helpers.NullAwareBeanUtilsBean;
import dash.pojo.Form;
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
public class FormServiceDbAccessImpl extends ApplicationObjectSupport implements
FormService {

	@Autowired
	FormDao formDao;

	@Autowired
	private GenericAclController<Form> aclController;

	/********************* Create related methods implementation ***********************/
	@Override
	@Transactional
	public Long createForm(Form form) throws AppException {

		long formId = formDao.createForm(new FormEntity(form));
		form.setId(formId);
		aclController.createACL(form);
		aclController.createAce(form, CustomPermission.READ);
		aclController.createAce(form, CustomPermission.WRITE);
		aclController.createAce(form, CustomPermission.DELETE);
		return formId;
	}

	@Override
	@Transactional
	public void createForms(List<Form> forms) throws AppException {
		for (Form form : forms) {
			createForm(form);
		}
	}


	// ******************** Read related methods implementation **********************
	@Override
	public List<Form> getForms(int numberOfForms, Long startIndex) throws AppException{
		
		List<FormEntity> forms = formDao.getForms(numberOfForms, startIndex);
		return getFormsFromEntities(forms);
	}
	

	@Override
	public Form getFormById(Long id) throws AppException {
		FormEntity formById = formDao.getFormById(id);
		if (formById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The form you requested with id " + id
					+ " was not found in the database",
					"Verify the existence of the form with the id " + id
					+ " in the database", AppConstants.DASH_POST_URL);
		}

		return new Form(formDao.getFormById(id));
	}

	private List<Form> getFormsFromEntities(List<FormEntity> formEntities) {
		List<Form> response = new ArrayList<Form>();
		for (FormEntity formEntity : formEntities) {
			response.add(new Form(formEntity));
		}

		return response;
	}
	

	
	

//	public List<Form> getRecentForms(int numberOfDaysToLookBack) {
//		List<FormEntity> recentForms = formDao
//				.getRecentForms(numberOfDaysToLookBack);
//
//		return getFormsFromEntities(recentForms);
//	}

	@Override
	public int getNumberOfForms() {
		int totalNumber = formDao.getNumberOfForms();

		return totalNumber;

	}



	/********************* UPDATE-related methods implementation ***********************/
	
	@Override
	@Transactional
	public void updateFullyForm(Form form) throws AppException {
		
		
		
		Form verifyFormExistenceById = verifyFormExistenceById(form
				.getId());
		if (verifyFormExistenceById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ form.getId(),
							AppConstants.DASH_POST_URL);
		}
		copyAllProperties(verifyFormExistenceById, form);

		formDao.updateForm(new FormEntity(verifyFormExistenceById));

	}

	private void copyAllProperties(Form verifyFormExistenceById, Form form) {
		//If you would like to allow null values use the following line.
		//Reference PostServiceImpl in the VolunteerManagementApp for more details.
		//BeanUtilsBean withNull=new BeanUtilsBean();
		
		//Assuming the NullAwareBeanUtilsBean is sufficient this code can be used.
		BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifyFormExistenceById, form);
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
	public void deleteForm(Form form) {

		formDao.deleteFormById(form);
		aclController.deleteACL(form);

	}

	@Override
	@Transactional
	// TODO: This shouldn't exist? If it must, then it needs to accept a list of
	// Forms to delete
	public void deleteForms() {
		formDao.deleteForms();
	}

	@Override
	
	public Form verifyFormExistenceById(Long id) {
		FormEntity formById = formDao.getFormById(id);
		if (formById == null) {
			return null;
		} else {
			return new Form(formById);
		}
	}

	@Override
	@Transactional
	public void updatePartiallyForm(Form form) throws AppException {
		//do a validation to verify existence of the resource
		Form verifyFormExistenceById = verifyFormExistenceById(form.getId());
		if (verifyFormExistenceById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ form.getId(), AppConstants.DASH_POST_URL);
		}
		copyPartialProperties(verifyFormExistenceById, form);
		formDao.updateForm(new FormEntity(verifyFormExistenceById));

	}

	private void copyPartialProperties(Form verifyFormExistenceById, Form form) {

		BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifyFormExistenceById, form);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
