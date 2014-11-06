package dash.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dash.pojo.SampleObject;

/**
 * This is an example of a JPA implementation of the DAO layer for a simple object
 * 
 * @author Tyler.swensen@gmail.com
 *
 */

public class SampleObjectDaoJPA2Impl implements SampleObjectDao {
	@PersistenceContext(unitName = "dashPersistence")
	private EntityManager entityManager;

	@Override
	public List<SampleObjectEntity> getSampleObjects(int numberOfSampleObjects, Long startIndex) {
		String sqlString = null;

		sqlString = "SELECT u FROM SampleObjectEntity u WHERE u.id < ?1 ORDER BY u.time_stamp_sample DESC";

		TypedQuery<SampleObjectEntity> query = entityManager.createQuery(sqlString,
				SampleObjectEntity.class);
		if (startIndex == 0)
			startIndex = Long.MAX_VALUE;
		query.setParameter(1, startIndex);
		query.setMaxResults(numberOfSampleObjects);

		return query.getResultList();
	}

	@Override
	public SampleObjectEntity getSampleObjectById(Long id) {

		try {
			String qlString = "SELECT u FROM SampleObjectEntity u WHERE u.id = ?1";
			TypedQuery<SampleObjectEntity> query = entityManager.createQuery(qlString,
					SampleObjectEntity.class);
			query.setParameter(1, id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void deleteSampleObjectById(SampleObject sampleObjectPojo) {

		SampleObjectEntity sampleObject = entityManager
				.find(SampleObjectEntity.class, sampleObjectPojo.getId());
		entityManager.remove(sampleObject);

	}

	@Override
	public Long createSampleObject(SampleObjectEntity sampleObject) {

		sampleObject.setTime_stamp_sample(new Date());
		entityManager.persist(sampleObject);
		entityManager.flush();// force insert to receive the id of the sampleObject

		// Give admin over new sampleObject to the new sampleObject

		return sampleObject.getId();
	}

	@Override
	public void updateSampleObject(SampleObjectEntity sampleObject) {
		// TODO think about partial update and full update
		entityManager.merge(sampleObject);
	}

	@Override
	public void deleteSampleObjects() {
		Query query = entityManager.createNativeQuery("TRUNCATE TABLE sampleObject");
		query.executeUpdate();
	}

	@Override
	public int getNumberOfSampleObjects() {
		try {
			String qlString = "SELECT COUNT(*) FROM sampleObject";
			TypedQuery<SampleObjectEntity> query = entityManager.createQuery(qlString,
					SampleObjectEntity.class);

			return query.getFirstResult();
		} catch (NoResultException e) {
			return 0;
		}
	}
}
