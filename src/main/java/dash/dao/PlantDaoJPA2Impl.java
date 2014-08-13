package dash.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
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
	public List<PlantEntity> getPlantsAlpha(Integer nativeField, Integer colorTiming,
			Integer soilConditions, Integer sunAmount, Integer growthSize)
	{
		try
		{
			String sqlString = "SELECT u FROM PlantEntity u" + 
							   " WHERE u.nativeField = ?1" + 
							   " AND u.colorTiming = ?2" +
							   " AND u.soilConditions = ?3" +
							   " AND u.sun = ?4" +
							   " AND u.growthSize = ?5";
			
			TypedQuery<PlantEntity> query = entityManager.createQuery(sqlString,
					PlantEntity.class);
	
			query.setParameter(1, nativeField);
			query.setParameter(2, colorTiming);
			query.setParameter(3, soilConditions);
			query.setParameter(4, sunAmount);
			query.setParameter(5, growthSize);
			
			return query.getResultList();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}
}
