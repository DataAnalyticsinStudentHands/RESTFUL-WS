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

import dash.pojo.Plant;

public class PlantDaoJPA2Impl implements PlantDao
{
	@PersistenceContext(unitName = "dashPersistence")
	private EntityManager entityManager;

	@Override
	public PlantEntity getPlantByID(Long id)
	{
		try
		{
			String qlString = "SELECT u FROM PlantEntity u WHERE u.id = ?1";
			TypedQuery<PlantEntity> query = entityManager.createQuery(qlString,
					PlantEntity.class);
			query.setParameter(1, id);

			return query.getSingleResult();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}

	@Override
	public PlantEntity getPlantByCommonName(String commonName)
	{
		try
		{
			String qlString = "SELECT u FROM PlantEntity u WHERE u.commonName = ?1";
			TypedQuery<PlantEntity> query = entityManager.createQuery(qlString,
					PlantEntity.class);
			query.setParameter(1, commonName);

			return query.getSingleResult();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}

	@Override
	public List<PlantEntity> getPlantsByFilter(Integer bloomSeason, Integer category,
			Integer nativeField, Integer waterAmount, Integer sunlightAmount)
	{
		try
		{
			String sqlString = "SELECT u FROM PlantEntity u " + 
							   "WHERE u.colorTiming = ?1 " + 
							   "AND u.classField = ?2 " +
							   "AND u.nativeField = ?3" +
							   "AND u.soilConditions = ?4" +
							   "AND u.sun = ?5";
			
			TypedQuery<PlantEntity> query = entityManager.createQuery(sqlString,
					PlantEntity.class);
	
			query.setParameter(1, bloomSeason);
			query.setParameter(2, category);
			query.setParameter(3, nativeField);
			query.setParameter(4, waterAmount);
			query.setParameter(5, sunlightAmount);
			
			return query.getResultList();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}
}
