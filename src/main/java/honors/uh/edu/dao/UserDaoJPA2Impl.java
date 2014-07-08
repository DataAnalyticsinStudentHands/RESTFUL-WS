package honors.uh.edu.dao;

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

public class UserDaoJPA2Impl implements UserDao {

	@PersistenceContext(unitName = "dashRestPersistence")
	private EntityManager entityManager;

	@Override
	public List<UserEntity> getUsers(String orderByInsertionDate) {
		String sqlString = null;
		if(orderByInsertionDate != null){
			sqlString = "SELECT p FROM Users p" + " ORDER BY p.insertionDate "
					+ orderByInsertionDate;
		} else {
			sqlString = "SELECT p FROM Users p";
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

		String qlString = "SELECT p FROM Users p where p.insertionDate > :dateToLookBackAfter ORDER BY p.insertionDate DESC";
		TypedQuery<UserEntity> query = entityManager.createQuery(qlString,
				UserEntity.class);
		query.setParameter("dateToLookBackAfter", dateToLookBackAfter, TemporalType.DATE);

		return query.getResultList();
	}

	@Override
	public UserEntity getUserById(Long id) {

		try {
			String qlString = "SELECT p FROM Users p WHERE p.id = ?1";
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
			String qlString = "SELECT p FROM Users p WHERE p.username = ?1";
			TypedQuery<UserEntity> query = entityManager.createQuery(qlString,
					UserEntity.class);
			query.setParameter(1, name);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


	@Override
	public void deleteUserById(Long id) {

		UserEntity user = entityManager.find(UserEntity.class, id);
		entityManager.remove(user);

	}

	@Override
	public Long createUser(UserEntity user) {

		user.setInsertionDate(new Date());
		entityManager.merge(user);
		entityManager.flush();// force insert to receive the id of the user

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
