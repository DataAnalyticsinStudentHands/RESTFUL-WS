package dash.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dash.pojo.SimpleObject;


public class SimpleObjectDaoJPA2Impl implements
SimpleObjectDao {

	@PersistenceContext(unitName = "dashPersistence")
	private EntityManager entityManager;

	@Override
	public List<SimpleObjectEntity> getSimpleObjects(String orderByInsertionDate) {
		String sqlString = null;
		if(orderByInsertionDate != null){
			sqlString = "SELECT o FROM SimpleObjectEntity o"
					+ " ORDER BY o.insertionDate " + orderByInsertionDate;
		} else {
			sqlString = "SELECT o FROM SimpleObjectEntity o";
		}
		TypedQuery<SimpleObjectEntity> query = entityManager.createQuery(sqlString,
				SimpleObjectEntity.class);

		return query.getResultList();
	}

	@Override
	public SimpleObjectEntity getSimpleObjectById(Long id) {

		try {
			String qlString = "SELECT u FROM SimpleObjectEntity u WHERE u.id = ?1";
			TypedQuery<SimpleObjectEntity> query = entityManager.createQuery(qlString,
					SimpleObjectEntity.class);
			query.setParameter(1, id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public SimpleObjectEntity getSimpleObjectByName(String name) {

		try {
			String qlString = "SELECT o FROM SimpleObjectEntity o WHERE o.name = ?1";
			TypedQuery<SimpleObjectEntity> query = entityManager.createQuery(qlString,
					SimpleObjectEntity.class);
			query.setParameter(1, name);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void deleteSimpleObject(SimpleObject simpleObjectPojo) {

		SimpleObjectEntity simpleObject = entityManager
				.find(SimpleObjectEntity.class, simpleObjectPojo.getId());
		entityManager.remove(simpleObject);
	}

	@Override
	public Long createSimpleObject(SimpleObjectEntity simpleObject) {

		simpleObject.setCreation_timestamp(new Date());
		entityManager.persist(simpleObject);
		entityManager.flush();// force insert to receive the id of the user

		// Give admin over new user to the new user
		return simpleObject.getId();
	}

	@Override
	public void updateSimpleObject(SimpleObjectEntity simpleObject) {
		//TODO think about partial update and full update
		entityManager.merge(simpleObject);
	}

	@Override
	public void deleteSimpleObjects() {
		Query query = entityManager.createNativeQuery("TRUNCATE TABLE users");
		query.executeUpdate();
	}

}
