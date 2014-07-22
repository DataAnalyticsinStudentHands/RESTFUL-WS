package dash.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import dash.pojo.User;


public class UserDaoJPA2Impl implements
UserDao {

	@PersistenceContext(unitName = "dashPersistence")
	private EntityManager entityManager;


	@Override
	public List<UserEntity> getUsers(String orderByInsertionDate) {
		String sqlString = null;
		if(orderByInsertionDate != null){
			sqlString = "SELECT u FROM UserEntity u"
					+ " ORDER BY u.insertionDate " + orderByInsertionDate;
		} else {
			sqlString = "SELECT u FROM UserEntity u";
		}
		TypedQuery<UserEntity> query = entityManager.createQuery(sqlString,
				UserEntity.class);

		return query.getResultList();
	}

	@Override
	public List<UserEntity> getRecentUsers(int numberOfDaysToLookBack) {

		Calendar calendar = new GregorianCalendar();
		calendar.setTimeZone(TimeZone.getTimeZone("UTC+6"));
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -numberOfDaysToLookBack);//substract the number of days to look back
		Date dateToLookBackAfter = calendar.getTime();

		String qlString = "SELECT u FROM UserEntity u where u.insertionDate > :dateToLookBackAfter ORDER BY u.insertionDate DESC";
		TypedQuery<UserEntity> query = entityManager.createQuery(qlString,
				UserEntity.class);
		query.setParameter("dateToLookBackAfter", dateToLookBackAfter, TemporalType.DATE);

		return query.getResultList();
	}

	@Override
	public UserEntity getUserById(Long id) {

		try {
			String qlString = "SELECT u FROM UserEntity u WHERE u.id = ?1";
			TypedQuery<UserEntity> query = entityManager.createQuery(qlString,
					UserEntity.class);
			query.setParameter(1, id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public UserEntity getUserByName(String name) {

		try {
			String qlString = "SELECT u FROM UserEntity u WHERE u.username = ?1";
			TypedQuery<UserEntity> query = entityManager.createQuery(qlString,
					UserEntity.class);
			query.setParameter(1, name);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


	@Override
	public void deleteUserById(User userPojo) {

		UserEntity user = entityManager
				.find(UserEntity.class, userPojo.getId());
		entityManager.remove(user);

	}

	@Override
	public Long createUser(UserEntity user) {

		user.setInsertionDate(new Date());
		entityManager.persist(user);
		entityManager.flush();// force insert to receive the id of the user

		// Give admin over new user to the new user

		return user.getId();
	}

	@Override
	public void updateUser(UserEntity user) {
		//TODO think about partial update and full update
		entityManager.merge(user);
	}

	@Override
	public void deleteUsers() {
		Query query = entityManager.createNativeQuery("TRUNCATE TABLE users");
		query.executeUpdate();
	}

	@Override
	public int getNumberOfUsers() {
		try {
			String qlString = "SELECT COUNT(*) FROM users";
			TypedQuery<UserEntity> query = entityManager.createQuery(qlString,
					UserEntity.class);

			return query.getFirstResult();
		} catch (NoResultException e) {
			return 0;
		}
	}



}
