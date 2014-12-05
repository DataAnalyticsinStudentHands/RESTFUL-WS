package dash.dao;

import java.util.List;


import dash.pojo.Question;


/**
 * An Example DAO interface for a simple object.
 * @Author tyler.swensen@gmail.com
 */
public interface QuestionDao {
	
	public List<QuestionEntity> getQuestions(int numberOfQuestions, Long startIndex);
	
	public int getNumberOfQuestions();

	/**
	 * Returns a question given its id
	 *
	 * @param id
	 * @return
	 */
	public QuestionEntity getQuestionById(Long id);
	
	public void deleteQuestionById(Question question);

	public Long createQuestion(QuestionEntity question);

	public void updateQuestion(QuestionEntity question);

	/** removes all questions */
	public void deleteQuestions();

}
