package dash.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.security.acls.model.MutableAclService;

import dash.dao.PlantDao;
import dash.dao.PlantEntity;
import dash.errorhandling.AppException;
import dash.filters.AppConstants;
import dash.pojo.Plant;
import dash.security.GenericAclController;

public class PlantServiceDbAccessImpl extends ApplicationObjectSupport implements PlantService
{
	@Autowired
	PlantDao plantDao;

	@Autowired
	private MutableAclService mutableAclService;

	@Autowired
	private GenericAclController<Plant> aclController;

	@Override
	public Plant getPlantByID(Long id) throws AppException
	{
		PlantEntity plantById = plantDao.getPlantByID(id);
		
		if (plantById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The plant you requested with id " + id
					+ " was not found in the database",
					"Verify the existence of the group with the id " + id
					+ " in the database", AppConstants.DASH_POST_URL);
		}

		return new Plant(plantDao.getPlantByID(id));
	}

	@Override
	public Plant getPlantByCommonName(String commonName) throws AppException
	{
		PlantEntity plantByCommonName = plantDao.getPlantByCommonName(commonName);
		
		if (plantByCommonName == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The plant you requested with commonName " + commonName
					+ " was not found in the database",
					"Verify the existence of the group with the id " + commonName
					+ " in the database", AppConstants.DASH_POST_URL);
		}

		return new Plant(plantDao.getPlantByCommonName(commonName));
	}

	@Override
	public List<Plant> getPlantsByFilter(Integer bloomSeason,
			Integer category, Integer nativeField, Integer waterAmount,
			Integer sunlightAmount) throws AppException
	{
		List<PlantEntity> plants = plantDao.getPlantsByFilter(bloomSeason, category, nativeField,
				waterAmount, sunlightAmount);

		if (plants == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The plants you requested with the search filters"
					+ " were not found in the database",
					"Verify the existence of the plants with the search filters"
					+ " in the database", AppConstants.DASH_POST_URL);
		}
		
		return getPlantsFromEntities(plants);
	}
	
	private List<Plant> getPlantsFromEntities(List<PlantEntity> plantEntities) {
		List<Plant> response = new ArrayList<Plant>();
		for (PlantEntity plantEntity : plantEntities) {
			response.add(new Plant(plantEntity));
		}

		return response;
	}
}
