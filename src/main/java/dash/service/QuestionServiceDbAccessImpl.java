package dash.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.transaction.annotation.Transactional;

import dash.dao.QuestionDao;
import dash.dao.QuestionEntity;
import dash.errorhandling.AppException;
import dash.filters.AppConstants;
import dash.helpers.NullAwareBeanUtilsBean;
import dash.pojo.Question;
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
public class QuestionServiceDbAccessImpl extends ApplicationObjectSupport implements
QuestionService {

	@Autowired
	QuestionDao questionDao;

	@Autowired
	private GenericAclController<Question> aclController;

	/********************* Create related methods implementation ***********************/
	@Override
	@Transactional
	public Long createQuestion(Question question) throws AppException {

		long questionId = questionDao.createQuestion(new QuestionEntity(question));
		question.setId(questionId);
		aclController.createACL(question);
		aclController.createAce(question, CustomPermission.READ);
		aclController.createAce(question, CustomPermission.WRITE);
		aclController.createAce(question, CustomPermission.DELETE);
		return questionId;
	}

	@Override
	@Transactional
	public void createQuestions(List<Question> questions) throws AppException {
		for (Question question : questions) {
			createQuestion(question);
		}
	}


	// ******************** Read related methods implementation **********************
	@Override
	public List<Question> getQuestions(int numberOfQuestions, Long startIndex) throws AppException{
		
		List<QuestionEntity> questions = questionDao.getQuestions(numberOfQuestions, startIndex);
		return getQuestionsFromEntities(questions);
	}
	

	@Override
	public Question getQuestionById(Long id) throws AppException {
		QuestionEntity questionById = questionDao.getQuestionById(id);
		if (questionById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The question you requested with id " + id
					+ " was not found in the database",
					"Verify the existence of the question with the id " + id
					+ " in the database", AppConstants.DASH_POST_URL);
		}

		return new Question(questionDao.getQuestionById(id));
	}
	
	@Override
	public List<Question> getQuestionsByFormId(Long id) throws AppException {
		QuestionEntity questionById = questionDao.getQuestionById(id);
		if (questionById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The question you requested with id " + id
					+ " was not found in the database",
					"Verify the existence of the question with the id " + id
					+ " in the database", AppConstants.DASH_POST_URL);
		}

		return getQuestionsFromEntities(questionDao.getQuestionsByFormId(id));
	}
	

	private List<Question> getQuestionsFromEntities(List<QuestionEntity> questionEntities) {
		List<Question> response = new ArrayList<Question>();
		for (QuestionEntity questionEntity : questionEntities) {
			response.add(new Question(questionEntity));
		}

		return response;
	}
	

	
	

//	public List<Question> getRecentQuestions(int numberOfDaysToLookBack) {
//		List<QuestionEntity> recentQuestions = questionDao
//				.getRecentQuestions(numberOfDaysToLookBack);
//
//		return getQuestionsFromEntities(recentQuestions);
//	}

	@Override
	public int getNumberOfQuestions() {
		int totalNumber = questionDao.getNumberOfQuestions();

		return totalNumber;

	}



	/********************* UPDATE-related methods implementation ***********************/
	
	@Override
	@Transactional
	public void updateFullyQuestion(Question question) throws AppException {
		
		
		
		Question verifyQuestionExistenceById = verifyQuestionExistenceById(question
				.getId());
		if (verifyQuestionExistenceById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ question.getId(),
							AppConstants.DASH_POST_URL);
		}
		copyAllProperties(verifyQuestionExistenceById, question);

		questionDao.updateQuestion(new QuestionEntity(verifyQuestionExistenceById));

	}

	private void copyAllProperties(Question verifyQuestionExistenceById, Question question) {
		//If you would like to allow null values use the following line.
		//Reference PostServiceImpl in the VolunteerManagementApp for more details.
		//BeanUtilsBean withNull=new BeanUtilsBean();
		
		//Assuming the NullAwareBeanUtilsBean is sufficient this code can be used.
		BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifyQuestionExistenceById, question);
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
	public void deleteQuestion(Question question) {

		questionDao.deleteQuestionById(question);
		aclController.deleteACL(question);

	}

	@Override
	@Transactional
	// TODO: This shouldn't exist? If it must, then it needs to accept a list of
	// Questions to delete
	public void deleteQuestions() {
		questionDao.deleteQuestions();
	}

	@Override
	
	public Question verifyQuestionExistenceById(Long id) {
		QuestionEntity questionById = questionDao.getQuestionById(id);
		if (questionById == null) {
			return null;
		} else {
			return new Question(questionById);
		}
	}

	@Override
	@Transactional
	public void updatePartiallyQuestion(Question question) throws AppException {
		//do a validation to verify existence of the resource
		Question verifyQuestionExistenceById = verifyQuestionExistenceById(question.getId());
		if (verifyQuestionExistenceById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ question.getId(), AppConstants.DASH_POST_URL);
		}
		copyPartialProperties(verifyQuestionExistenceById, question);
		questionDao.updateQuestion(new QuestionEntity(verifyQuestionExistenceById));

	}

	private void copyPartialProperties(Question verifyQuestionExistenceById, Question question) {

		BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifyQuestionExistenceById, question);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
