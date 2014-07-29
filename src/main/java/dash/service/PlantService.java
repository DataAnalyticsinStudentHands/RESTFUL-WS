package dash.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import dash.dao.PlantEntity;
import dash.errorhandling.AppException;
import dash.pojo.Plant;


public interface PlantService
{

	/*
	 * ******************* Read related methods ********************
	 */
	public Plant getPlantByID(Long id) throws AppException;
	
	public Plant getPlantByCommonName(String commonName) throws AppException;
	
	public List<Plant> getPlantsByFilter(Integer bloomSeason, Integer category,
			Integer nativeField, Integer waterAmount, Integer sunlightAmount) throws AppException;
}
