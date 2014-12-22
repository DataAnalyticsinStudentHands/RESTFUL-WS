package dash.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;



import org.springframework.stereotype.Component;

import dash.pojo.FormResponse;

/**
 * This is an example of a JPA implementation of the DAO layer for a simple object
 * 
 * @author Tyler.swensen@gmail.com
 *
 */
@Component("formResponseDao")
public class FormResponseDaoJPA2Impl implements FormResponseDao {
	@PersistenceContext(unitName = "dashPersistence")
	private EntityManager entityManager;

	@Override
	public List<FormResponseEntity> getFormResponses(int numberOfFormResponses, Long startIndex) {
		String sqlString = null;

		sqlString = "SELECT u FROM FormResponseEntity u WHERE u.id < ?1 ORDER BY u.insertion_date DESC";

		TypedQuery<FormResponseEntity> query = entityManager.createQuery(sqlString,
				FormResponseEntity.class);
		if (startIndex == 0)
			startIndex = Long.MAX_VALUE;
		query.setParameter(1, startIndex);
		query.setMaxResults(numberOfFormResponses);

		return query.getResultList();
	}

	@Override
	public FormResponseEntity getFormResponseById(Long id) {

		try {
			String qlString = "SELECT u FROM FormResponseEntity u WHERE u.id = ?1";
			TypedQuery<FormResponseEntity> query = entityManager.createQuery(qlString,
					FormResponseEntity.class);
			query.setParameter(1, id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void deleteFormResponseById(FormResponse formResponsePojo) {

		FormResponseEntity formResponse = entityManager
				.find(FormResponseEntity.class, formResponsePojo.getId());
		entityManager.remove(formResponse);

	}

	@Override
	public Long createFormResponse(FormResponseEntity formResponse) {

		formResponse.setInsertion_date(new Date());
		formResponse.setLatest_update(new Date());
		entityManager.persist(formResponse);
		entityManager.flush();// force insert to receive the id of the formResponse

		// Give admin over new formResponse to the new formResponse

		return formResponse.getId();
	}

	@Override
	public void updateFormResponse(FormResponseEntity formResponse) {
		// TODO think about partial update and full update
		formResponse.setLatest_update(new Date());
		entityManager.merge(formResponse);
	}

	@Override
	public void deleteFormResponses() {
		Query query = entityManager.createNativeQuery("TRUNCATE TABLE formResponses");
		query.executeUpdate();
	}

	@Override
	public int getNumberOfFormResponses() {
		try {
			String qlString = "SELECT COUNT(*) FROM formResponse";
			TypedQuery<FormResponseEntity> query = entityManager.createQuery(qlString,
					FormResponseEntity.class);

			return query.getFirstResult();
		} catch (NoResultException e) {
			return 0;
		}
	}
}
