package dash.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import dash.pojo.FileUpload;

/**
 * This is an example of a JPA implementation of the DAO layer for a simple object
 * 
 * @author Tyler.swensen@gmail.com
 *
 */
@Component("fileUploadDao")
public class FileUploadDaoJPA2Impl implements FileUploadDao {
	@PersistenceContext(unitName = "dashPersistence")
	private EntityManager entityManager;

	@Override
	public List<FileUpload> getFileUploads(int numberOfFileUploads, Long startIndex) {
		String sqlString = null;

		sqlString = "SELECT u FROM FileUpload u WHERE u.id < ?1 ORDER BY u.upload_timestamp DESC";

		TypedQuery<FileUpload> query = entityManager.createQuery(sqlString,
				FileUpload.class);
		if (startIndex == 0)
			startIndex = Long.MAX_VALUE;
		query.setParameter(1, startIndex);
		query.setMaxResults(numberOfFileUploads);

		return query.getResultList();
	}

	@Override
	public FileUpload getFileUploadById(Long id) {

		try {
			String qlString = "SELECT u FROM FileUpload u WHERE u.id = ?1";
			TypedQuery<FileUpload> query = entityManager.createQuery(qlString,
					FileUpload.class);
			query.setParameter(1, id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void deleteFileUploadById(FileUpload fileUploadPojo) {

		FileUpload fileUpload = entityManager
				.find(FileUpload.class, fileUploadPojo.getId());
		entityManager.remove(fileUpload);

	}

	@Override
	public Long createFileUpload(FileUpload fileUpload) {

		fileUpload.setUpload_timestamp(new Date());
		entityManager.persist(fileUpload);
		entityManager.flush();// force insert to receive the id of the fileUpload

		// Give admin over new fileUpload to the new fileUpload

		return fileUpload.getId();
	}

	@Override
	public void updateFileUpload(FileUpload fileUpload) {
		// TODO think about partial update and full update
		entityManager.merge(fileUpload);
	}

	@Override
	public void deleteFileUploads() {
		Query query = entityManager.createNativeQuery("TRUNCATE TABLE fileUpload");
		query.executeUpdate();
	}

	@Override
	public int getNumberOfFileUploads() {
		try {
			String qlString = "SELECT COUNT(*) FROM fileUpload";
			TypedQuery<FileUpload> query = entityManager.createQuery(qlString,
					FileUpload.class);

			return query.getFirstResult();
		} catch (NoResultException e) {
			return 0;
		}
	}
}
