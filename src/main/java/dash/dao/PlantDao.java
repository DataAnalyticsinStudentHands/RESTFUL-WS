package dash.dao;

import java.util.List;
import java.util.Map;

/*
 * @Author jVera
 */
public interface PlantDao
{	
	public PlantEntity getPlantByCommonName(String commonName);
	
	public List<PlantEntity> getPlantsAlpha(Integer nativeField, Integer colorTiming,
			Integer soilConditions, Integer sunAmount, Integer growthSize);
}
