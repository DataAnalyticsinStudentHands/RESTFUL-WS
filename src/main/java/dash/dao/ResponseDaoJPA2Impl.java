package dash.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import dash.pojo.Response;

/**
 * This is an example of a JPA implementation of the DAO layer for a simple object
 * 
 * @author Tyler.swensen@gmail.com
 *
 */

public class ResponseDaoJPA2Impl implements ResponseDao {
	@PersistenceContext(unitName = "dashPersistence")
	private EntityManager entityManager;

	@Override
	public List<ResponseEntity> getResponses(int numberOfResponses, Long startIndex) {
		String sqlString = null;

		sqlString = "SELECT u FROM ResponseEntity u WHERE u.id < ?1 ORDER BY u.insertion_date DESC";

		TypedQuery<ResponseEntity> query = entityManager.createQuery(sqlString,
				ResponseEntity.class);
		if (startIndex == 0)
			startIndex = Long.MAX_VALUE;
		query.setParameter(1, startIndex);
		query.setMaxResults(numberOfResponses);

		return query.getResultList();
	}

	@Override
	public ResponseEntity getResponseById(Long id) {

		try {
			String qlString = "SELECT u FROM ResponseEntity u WHERE u.id = ?1";
			TypedQuery<ResponseEntity> query = entityManager.createQuery(qlString,
					ResponseEntity.class);
			query.setParameter(1, id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void deleteResponseById(Response responsePojo) {

		ResponseEntity response = entityManager
				.find(ResponseEntity.class, responsePojo.getId());
		entityManager.remove(response);

	}

	@Override
	public Long createResponse(ResponseEntity response) {

		response.setInsertion_date(new Date());
		response.setLatest_update(new Date());
		entityManager.persist(response);
		entityManager.flush();// force insert to receive the id of the response

		// Give admin over new response to the new response

		return response.getId();
	}

	@Override
	public void updateResponse(ResponseEntity response) {
		// TODO think about partial update and full update
		response.setLatest_update(new Date());
		entityManager.merge(response);
	}

	@Override
	public void deleteResponses() {
		Query query = entityManager.createNativeQuery("TRUNCATE TABLE responses");
		query.executeUpdate();
	}

	@Override
	public int getNumberOfResponses() {
		try {
			String qlString = "SELECT COUNT(*) FROM response";
			TypedQuery<ResponseEntity> query = entityManager.createQuery(qlString,
					ResponseEntity.class);

			return query.getFirstResult();
		} catch (NoResultException e) {
			return 0;
		}
	}
}
