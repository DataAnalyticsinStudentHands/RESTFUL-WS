package dash.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dash.pojo.Question;

/**
 * This is an example of a JPA implementation of the DAO layer for a simple object
 * 
 * @author Tyler.swensen@gmail.com
 *
 */

public class QuestionDaoJPA2Impl implements QuestionDao {
	@PersistenceContext(unitName = "dashPersistence")
	private EntityManager entityManager;

	@Override
	public List<QuestionEntity> getQuestions(int numberOfQuestions, Long startIndex) {
		String sqlString = null;

		sqlString = "SELECT u FROM QuestionEntity u WHERE u.id < ?1 ORDER BY u.time_stamp_sample DESC";

		TypedQuery<QuestionEntity> query = entityManager.createQuery(sqlString,
				QuestionEntity.class);
		if (startIndex == 0)
			startIndex = Long.MAX_VALUE;
		query.setParameter(1, startIndex);
		query.setMaxResults(numberOfQuestions);

		return query.getResultList();
	}

	@Override
	public QuestionEntity getQuestionById(Long id) {

		try {
			String qlString = "SELECT u FROM QuestionEntity u WHERE u.id = ?1";
			TypedQuery<QuestionEntity> query = entityManager.createQuery(qlString,
					QuestionEntity.class);
			query.setParameter(1, id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void deleteQuestionById(Question questionPojo) {

		QuestionEntity question = entityManager
				.find(QuestionEntity.class, questionPojo.getId());
		entityManager.remove(question);

	}

	@Override
	public Long createQuestion(QuestionEntity question) {

		question.setTime_stamp_sample(new Date());
		entityManager.persist(question);
		entityManager.flush();// force insert to receive the id of the question

		// Give admin over new question to the new question

		return question.getId();
	}

	@Override
	public void updateQuestion(QuestionEntity question) {
		// TODO think about partial update and full update
		entityManager.merge(question);
	}

	@Override
	public void deleteQuestions() {
		Query query = entityManager.createNativeQuery("TRUNCATE TABLE question");
		query.executeUpdate();
	}

	@Override
	public int getNumberOfQuestions() {
		try {
			String qlString = "SELECT COUNT(*) FROM question";
			TypedQuery<QuestionEntity> query = entityManager.createQuery(qlString,
					QuestionEntity.class);

			return query.getFirstResult();
		} catch (NoResultException e) {
			return 0;
		}
	}
}
