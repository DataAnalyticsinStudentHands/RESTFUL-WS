package dash.dao;

import java.util.List;

import dash.pojo.Plant;

/*
 * @Author jVera
 */
public interface PlantDao
{
	public PlantEntity getPlantByID(Long id);
	
	public PlantEntity getPlantByCommonName(String commonName);
	
	public List<PlantEntity> getPlantsByFilter(Integer bloomSeason, Integer category,
			Integer nativeField, Integer waterAmount, Integer sunlightAmount);
}
