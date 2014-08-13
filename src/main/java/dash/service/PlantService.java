package dash.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;

import dash.dao.PlantEntity;
import dash.errorhandling.AppException;
import dash.pojo.Plant;


public interface PlantService
{

	/*
	 * ******************* Read related methods ********************
	 */
	
	public Plant getPlantByCommonName(String commonName) throws AppException;
	
	public List<Plant> getPlantsAlpha(Integer nativeField, Integer colorTimin,
			Integer soilConditions, Integer sunAmount, Integer growthSize)
		throws AppException;
}
