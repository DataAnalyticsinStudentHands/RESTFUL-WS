package dash.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import dash.errorhandling.AppException;
import dash.pojo.Question;

/**
 * Example service interface for a basic object.
 * 
 * This is where you set method/object level permissions using Spring annotations.
 * 
 * @author Tyler.swensen@gmail.com
 *
 */

public interface QuestionService {
	
	/*
	 * ******************** Create related methods *********************/
	
	/**
	 * Create a new question and set the current user as owner and manager.
	 * @param question
	 * @return
	 * @throws AppException
	 */
	public Long createQuestion(Question question) throws AppException;

	/*
	 * Create multiple questions as ROOT, testing purposes only.
	 */
	@PreAuthorize("hasRole('ROLE_ROOT')")
	public void createQuestions(List<Question> questions) throws AppException;

	/*
	 * ******************* Read related methods ********************
	 */
	/**
	 *
	 * @param orderByInsertionDate
	 *            - if set, it represents the order by criteria (ASC or DESC)
	 *            for displaying questions
	 * @param numberDaysToLookBack
	 *            - if set, it represents number of days to look back for questions,
	 *            null
	 * @return list with questions corresponding to search criteria
	 * @throws AppException
	 */
	//Enable post filter to restrict read access to a collection
	//@PostFilter("hasPermission(filterObject, 'READ')"
	public List<Question> getQuestions(int numberOfQuestions, Long startIndex) throws AppException;
	
	/**
	 * Returns a question given its id
	 *
	 * @param id
	 * @return
	 * @throws AppException
	 */
	
	//Enable the following line of code to restrict read access to a single object.
	//@PostAuthrorize("hasPermission(#returnObject, 'read')")
	public Question getQuestionById(Long id) throws AppException;

	/*
	 * ******************** Update related methods **********************
	 */
	@PreAuthorize("hasPermission(#question, 'write') or hasRole('ROLE_ADMIN')")
	public void updateFullyQuestion(Question question) throws AppException;

	@PreAuthorize("hasPermission(#question, 'write') or hasRole('ROLE_ADMIN')")
	public void updatePartiallyQuestion(Question question) throws AppException;

	/*
	 * ******************** Delete related methods **********************
	 */


	@PreAuthorize("hasPermission(#question, 'delete') or hasRole('ROLE_ADMIN')")
	public void deleteQuestion(Question question);
	/** removes all questions
	 * DO NOT USE, IMPROPERLY UPDATES ACL_OBJECT table
	 * Functional but does not destroy old acl's which doesnt hurt anything
	 * but they will take up space if this is commonly used */
	@PreAuthorize("hasRole('ROLE_ROOT')")
	public void deleteQuestions();
	
	
	/*
	 * ******************** Helper methods **********************
	 */
	// TODO: This also should not exist, or it should be changed to
	// private/protected. Redundant
	// Could be made a boolean so it was not a security vulnerability
	public Question verifyQuestionExistenceById(Long id);

	public int getNumberOfQuestions();

}
