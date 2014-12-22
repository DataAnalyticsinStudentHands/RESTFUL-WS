package dash.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import dash.pojo.Form;

/**
 * This is an example of a JPA implementation of the DAO layer for a simple object
 * 
 * @author Tyler.swensen@gmail.com
 *
 */
@Component("formDao")
public class FormDaoJPA2Impl implements FormDao {
	@PersistenceContext(unitName = "dashPersistence")
	private EntityManager entityManager;

	@Override
	public List<FormEntity> getForms(int numberOfForms, Long startIndex) {
		String sqlString = null;

		sqlString = "SELECT u FROM FormEntity u WHERE u.id < ?1 ORDER BY u.insertion_date DESC";

		TypedQuery<FormEntity> query = entityManager.createQuery(sqlString,
				FormEntity.class);
		if (startIndex == 0)
			startIndex = Long.MAX_VALUE;
		query.setParameter(1, startIndex);
		query.setMaxResults(numberOfForms);

		return query.getResultList();
	}

	@Override
	public FormEntity getFormById(Long id) {

		try {
			String qlString = "SELECT u FROM FormEntity u WHERE u.id = ?1";
			TypedQuery<FormEntity> query = entityManager.createQuery(qlString,
					FormEntity.class);
			query.setParameter(1, id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void deleteFormById(Form formPojo) {

		FormEntity form = entityManager
				.find(FormEntity.class, formPojo.getId());
		entityManager.remove(form);

	}

	@Override
	public Long createForm(FormEntity form) {

		form.setInsertion_date(new Date());
		entityManager.persist(form);
		entityManager.flush();// force insert to receive the id of the form

		// Give admin over new form to the new form

		return form.getId();
	}

	@Override
	public void updateForm(FormEntity form) {
		// TODO think about partial update and full update
		entityManager.merge(form);
	}

	@Override
	public void deleteForms() {
		Query query = entityManager.createNativeQuery("TRUNCATE TABLE forms");
		query.executeUpdate();
	}

	@Override
	public int getNumberOfForms() {
		try {
			String qlString = "SELECT COUNT(*) FROM form";
			TypedQuery<FormEntity> query = entityManager.createQuery(qlString,
					FormEntity.class);

			return query.getFirstResult();
		} catch (NoResultException e) {
			return 0;
		}
	}
}
