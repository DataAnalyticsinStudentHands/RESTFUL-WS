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

import dash.pojo.Group;

public class GroupDaoJPA2Impl implements GroupDao {
	@PersistenceContext(unitName = "dashPersistence")
	private EntityManager entityManager;


	@Override
	public List<GroupEntity> getGroups(String orderByInsertionDate) {
		String sqlString = null;
		if(orderByInsertionDate != null){
			sqlString = "SELECT u FROM GroupEntity u"
					+ " ORDER BY u.creation_timestamp " + orderByInsertionDate;
		} else {
			sqlString = "SELECT u FROM GroupEntity u";
		}
		TypedQuery<GroupEntity> query = entityManager.createQuery(sqlString,
				GroupEntity.class);

		return query.getResultList();
	}

	@Override
	public List<GroupEntity> getRecentGroups(int numberOfDaysToLookBack) {

		Calendar calendar = new GregorianCalendar();
		calendar.setTimeZone(TimeZone.getTimeZone("UTC+6"));
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -numberOfDaysToLookBack);//substract the number of days to look back
		Date dateToLookBackAfter = calendar.getTime();

		String qlString = "SELECT u FROM GroupEntity u where u.creation_timestamp > :dateToLookBackAfter ORDER BY u.creation_timestamp DESC";
		TypedQuery<GroupEntity> query = entityManager.createQuery(qlString,
				GroupEntity.class);
		query.setParameter("dateToLookBackAfter", dateToLookBackAfter, TemporalType.DATE);

		return query.getResultList();
	}

	@Override
	public GroupEntity getGroupById(Long id) {

		try {
			String qlString = "SELECT u FROM GroupEntity u WHERE u.id = ?1";
			TypedQuery<GroupEntity> query = entityManager.createQuery(qlString,
					GroupEntity.class);
			query.setParameter(1, id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public GroupEntity getGroupByName(String name) {

		try {
			String qlString = "SELECT u FROM GroupEntity u WHERE u.name = ?1";
			TypedQuery<GroupEntity> query = entityManager.createQuery(qlString,
					GroupEntity.class);
			query.setParameter(1, name);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


	@Override
	public void deleteGroupById(Group groupPojo) {

		GroupEntity group = entityManager
				.find(GroupEntity.class, groupPojo.getId());
		entityManager.remove(group);

	}

	@Override
	public Long createGroup(GroupEntity group) {

		
		entityManager.persist(group);
		entityManager.flush();// force insert to receive the id of the group

		// Give admin over new group to the new group

		return group.getId();
	}

	@Override
	public void updateGroup(GroupEntity group) {
		//TODO think about partial update and full update
		entityManager.merge(group);
	}

	@Override
	public void deleteGroups() {
		Query query = entityManager.createNativeQuery("TRUNCATE TABLE group");
		query.executeUpdate();
	}

	@Override
	public int getNumberOfGroups() {
		try {
			String qlString = "SELECT COUNT(*) FROM group";
			TypedQuery<GroupEntity> query = entityManager.createQuery(qlString,
					GroupEntity.class);

			return query.getFirstResult();
		} catch (NoResultException e) {
			return 0;
		}
	}
}
