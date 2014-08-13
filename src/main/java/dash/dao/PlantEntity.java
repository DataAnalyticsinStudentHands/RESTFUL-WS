package dash.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.beanutils.BeanUtils;

import dash.helpers.DateISO8601Adapter;
import dash.pojo.Plant;
import dash.pojo.UserDetailedView;

/**
 * Plant Entity
 * @author jVera
 * 
 */
@Entity
@Table(name="db2012")
public class PlantEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name = "scientific_name")
	private String scientificName;

	@Column(name = "houston_common_name")
	private String houstonCommonName;

	@Column(name = "common_name")
	private String commonName;

	@Column(name = "Class")
	private Integer classField;		// java reserves 'class'
	
	@Column(name = "Height_ft")
	private Integer height;			// in feet
	
	@Column(name = "Width_ft")
	private Integer width;			// in feet

	@Column(name = "Sun")
	private Integer sun;
	
	@Column(name = "Native")
	private Integer nativeField;		// java reserves 'native'
	
	@Column(name = "Soil_Conditions")
	private Integer soilConditions;
	
	@Column(name = "Map")
	private String map;

	@Column(name = "when_to_plant_from")
	private Date plantTimeFrom;
	
	@Column(name = "when_to_plant_to")
	private Date plantTimeTo;

	@Column(name = "Fruit_Timing")
	private Integer fruitTiming;
	
	@Column(name = "Color_Timing")
	private Integer colorTiming;
	
	@Column(name = "Distance_between_Plants")
	private Integer distanceBetweenPlants;			// in feet
	
	@Column(name = "Annual")
	private Integer annual;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "Host_Plant_For")
	private String hostPlantFor;
	
	@Column(name = "Disease_Tolerance")
	private Integer diseaseTolerance;
	
	@Column(name = "Pest_Tolerance")
	private Integer pestTolerance;
	
	@Column(name = "Food", columnDefinition = "enum('1', '2', '3', '')")
	private String food;
	
	@Column(name = "Purpose")
	private Integer purpose;
	
	@Column(name = "Growth_Rate")
	private Integer growthRate;

	@Column(name = "Edible_Parts")
	private String edibleParts;
	
	@Column(name = "Companion_Plants")
	private String companionPlants;
	
	@Column(name = "Toxic_Part")
	private String toxicPart;
	
	@Column(name = "Allergenicity")
	private Integer allergenicity;
	
	@Column(name = "Detering_Animals")
	private Integer deteringAnimals;
	
	@Column(name = "Suggestions_on_How_to_Grow")
	private String growthSuggestions;
	
	@Column(name = "cultivar")
	private String cultivar;

	@Column(name = "Endorsed_By")
	private Integer endorsedBy;
	
	@Column(name = "Available_at")
	private Integer availableAt;
	
	@Column(name = "Hardy_Scale")
	private Integer hardyScale;
	
	@Column(name = "Tourist_Spots")
	private String touristSpots;
	
	@Column(name = "Ease_of_Planting")
	private Integer easeOfPlanting;
	
	@Column(name = "Interesting_Facts")
	private String interestingFacts;
	
	@Column(name = "time_to_produce_from")
	private Date timeToProduceFrom;
	
	@Column(name = "time_to_produce_to")
	private Date timeToProduceTo;
	
	@Column(name = "Root_Depth")
	private Integer rootDepth;
	
	@Column(name = "duration_from")
	private Date durationFrom;
	
	@Column(name = "duration_to")
	private Date durationTo;
	
	@Column(name = "pH")
	private Integer pH;
	
	@Column(name = "Price")
	private Integer price;
	
	@Column(name = "D4")
	private Integer d4;
	
	@Column(name = "Deciduous_Cover")
	private Integer deciduousCover;
	
	@Column(name = "Larval_Butterfly_Host")
	private Integer larval_Butterfly_Host;
	
	@Column(name = "Sap")
	private Integer sap;
	
	@Column(name = "seed_timing_from")
	private Date seedTimingFrom;
	
	@Column(name = "seed_timing_to")
	private Date seedTimingTo;
	
	@Column(name = "Seed_Type")
	private Integer seedType;
	
	@Column(name = "Irrigated")
	private Integer irrigated;
	
	@Column(name = "Cultivar_Names")
	private String cultivarNames;

	@Column(name = "Air_Purifying")
	private Integer airPurifying;
	
	@Column(name = "Soil_Purifying")
	private Integer soilPurifying;
	
	@Column(name = "Data_Source")
	private String dataSource;
	
	@Column(name = "Can_we_share_the_source")
	private Integer canWeShareTheSource;
	
	@Column(name = "Taste_of_Fruit")
	private String tasteOfFruit;
	
	@Column(name = "Website")
	private String website;
	
	@Column(name = "Seeds_and_Nuts")
	private Integer seedsAndNuts;
	
	@Column(name = "HOA_approved")
	private Integer hoaApproved;
	
	@Column(name = "Cold_Tolerance")
	private Integer coldTolerance;
	
	@Column(name = "Drought_Tolerance")
	private Integer droughtTolerance;
	
	@Column(name = "Moisture_Tolerance")
	private Integer moistureTolerance;
	
	@Column(name = "Fall")
	private String fall;
	
	@Column(name = "Spring")
	private String spring;
	
	@Column(name = "Summer")
	private String summer;
	
	@Column(name = "Winter")
	private String winter;

	@Column(name = "image_path")
	private String imagePath;
	
	@Column(name = "maintenance")
	private Integer maintenance;
	
	@Column(name = "wildlife")
	private Integer wildlife;
	
	@Column(name = "map2")
	private Integer map2;
	
	@Column(name = "growthSize")
	private Integer growthSize;
	
	@Column(name = "whenToPlant")
	private Integer whenToPlant;
	
	@Column(name = "purpose2")
	private Integer purpose2;
	
	@Column(name = "companionPlants2")
	private Integer companionPlants2;
	
	@Column(name = "cultivar2")
	private Integer cultivar2;
	
	@Column(name = "duration")
	private Integer duration2;
	
	public PlantEntity(){}

	public PlantEntity(Long id, String scientificName,
			String houstonCommonName, String commonName, Integer classField,
			Integer height, Integer width, Integer sun, Integer nativeField,
			Integer soilConditions, String map, Date plantTimeFrom,
			Date plantTimeTo, Integer fruitTiming, Integer colorTiming,
			Integer distanceBetweenPlants, Integer annual, String color,
			String hostPlantFor, Integer diseaseTolerance,
			Integer pestTolerance, String food, Integer purpose,
			Integer growthRate, String edibleParts, String companionPlants,
			String toxicPart, Integer allergenicity, Integer deteringAnimals,
			String growthSuggestions, String cultivar, Integer endorsedBy,
			Integer availableAt, Integer hardyScale, String touristSpots,
			Integer easeOfPlanting, String interestingFacts,
			Date timeToProduceFrom, Date timeToProduceTo, Integer rootDepth,
			Date durationFrom, Date durationTo, Integer pH, Integer price,
			Integer d4, Integer deciduousCover, Integer larval_Butterfly_Host,
			Integer sap, Date seedTimingFrom, Date seedTimingTo,
			Integer seedType, Integer irrigated, String cultivarNames,
			Integer airPurifying, Integer soilPurifying, String dataSource,
			Integer canWeShareTheSource, String tasteOfFruit, String website,
			Integer seedsAndNuts, Integer hoaApproved, Integer coldTolerance,
			Integer droughtTolerance, Integer moistureTolerance, String fall,
			String spring, String summer, String winter, String imagePath,
			Integer maintenance, Integer wildlife, Integer map2,
			Integer growthSize, Integer whenToPlant, Integer purpose2,
			Integer companionPlants2, Integer cultivar2, Integer duration2) {
		super();
		this.id = id;
		this.scientificName = scientificName;
		this.houstonCommonName = houstonCommonName;
		this.commonName = commonName;
		this.classField = classField;
		this.height = height;
		this.width = width;
		this.sun = sun;
		this.nativeField = nativeField;
		this.soilConditions = soilConditions;
		this.map = map;
		this.plantTimeFrom = plantTimeFrom;
		this.plantTimeTo = plantTimeTo;
		this.fruitTiming = fruitTiming;
		this.colorTiming = colorTiming;
		this.distanceBetweenPlants = distanceBetweenPlants;
		this.annual = annual;
		this.color = color;
		this.hostPlantFor = hostPlantFor;
		this.diseaseTolerance = diseaseTolerance;
		this.pestTolerance = pestTolerance;
		this.food = food;
		this.purpose = purpose;
		this.growthRate = growthRate;
		this.edibleParts = edibleParts;
		this.companionPlants = companionPlants;
		this.toxicPart = toxicPart;
		this.allergenicity = allergenicity;
		this.deteringAnimals = deteringAnimals;
		this.growthSuggestions = growthSuggestions;
		this.cultivar = cultivar;
		this.endorsedBy = endorsedBy;
		this.availableAt = availableAt;
		this.hardyScale = hardyScale;
		this.touristSpots = touristSpots;
		this.easeOfPlanting = easeOfPlanting;
		this.interestingFacts = interestingFacts;
		this.timeToProduceFrom = timeToProduceFrom;
		this.timeToProduceTo = timeToProduceTo;
		this.rootDepth = rootDepth;
		this.durationFrom = durationFrom;
		this.durationTo = durationTo;
		this.pH = pH;
		this.price = price;
		this.d4 = d4;
		this.deciduousCover = deciduousCover;
		this.larval_Butterfly_Host = larval_Butterfly_Host;
		this.sap = sap;
		this.seedTimingFrom = seedTimingFrom;
		this.seedTimingTo = seedTimingTo;
		this.seedType = seedType;
		this.irrigated = irrigated;
		this.cultivarNames = cultivarNames;
		this.airPurifying = airPurifying;
		this.soilPurifying = soilPurifying;
		this.dataSource = dataSource;
		this.canWeShareTheSource = canWeShareTheSource;
		this.tasteOfFruit = tasteOfFruit;
		this.website = website;
		this.seedsAndNuts = seedsAndNuts;
		this.hoaApproved = hoaApproved;
		this.coldTolerance = coldTolerance;
		this.droughtTolerance = droughtTolerance;
		this.moistureTolerance = moistureTolerance;
		this.fall = fall;
		this.spring = spring;
		this.summer = summer;
		this.winter = winter;
		this.imagePath = imagePath;
		this.maintenance = maintenance;
		this.wildlife = wildlife;
		this.map2 = map2;
		this.growthSize = growthSize;
		this.whenToPlant = whenToPlant;
		this.purpose2 = purpose2;
		this.companionPlants2 = companionPlants2;
		this.cultivar2 = cultivar2;
		this.duration2 = duration2;
	}

	public PlantEntity(Plant plant)
	{
		try
		{
			BeanUtils.copyProperties(this, plant);
		}
		catch ( IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch ( InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getHoustonCommonName() {
		return houstonCommonName;
	}

	public void setHoustonCommonName(String houstonCommonName) {
		this.houstonCommonName = houstonCommonName;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public Integer getClassField() {
		return classField;
	}

	public void setClassField(Integer classField) {
		this.classField = classField;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getSun() {
		return sun;
	}

	public void setSun(Integer sun) {
		this.sun = sun;
	}

	public Integer getNativeField() {
		return nativeField;
	}

	public void setNativeField(Integer nativeField) {
		this.nativeField = nativeField;
	}

	public Integer getSoilConditions() {
		return soilConditions;
	}

	public void setSoilConditions(Integer soilConditions) {
		this.soilConditions = soilConditions;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public Date getPlantTimeFrom() {
		return plantTimeFrom;
	}

	public void setPlantTimeFrom(Date plantTimeFrom) {
		this.plantTimeFrom = plantTimeFrom;
	}

	public Date getPlantTimeTo() {
		return plantTimeTo;
	}

	public void setPlantTimeTo(Date plantTimeTo) {
		this.plantTimeTo = plantTimeTo;
	}

	public Integer getFruitTiming() {
		return fruitTiming;
	}

	public void setFruitTiming(Integer fruitTiming) {
		this.fruitTiming = fruitTiming;
	}

	public Integer getColorTiming() {
		return colorTiming;
	}

	public void setColorTiming(Integer colorTiming) {
		this.colorTiming = colorTiming;
	}

	public Integer getDistanceBetweenPlants() {
		return distanceBetweenPlants;
	}

	public void setDistanceBetweenPlants(Integer distanceBetweenPlants) {
		this.distanceBetweenPlants = distanceBetweenPlants;
	}

	public Integer getAnnual() {
		return annual;
	}

	public void setAnnual(Integer annual) {
		this.annual = annual;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getHostPlantFor() {
		return hostPlantFor;
	}

	public void setHostPlantFor(String hostPlantFor) {
		this.hostPlantFor = hostPlantFor;
	}

	public Integer getDiseaseTolerance() {
		return diseaseTolerance;
	}

	public void setDiseaseTolerance(Integer diseaseTolerance) {
		this.diseaseTolerance = diseaseTolerance;
	}

	public Integer getPestTolerance() {
		return pestTolerance;
	}

	public void setPestTolerance(Integer pestTolerance) {
		this.pestTolerance = pestTolerance;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public Integer getPurpose() {
		return purpose;
	}

	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}

	public Integer getGrowthRate() {
		return growthRate;
	}

	public void setGrowthRate(Integer growthRate) {
		this.growthRate = growthRate;
	}

	public String getEdibleParts() {
		return edibleParts;
	}

	public void setEdibleParts(String edibleParts) {
		this.edibleParts = edibleParts;
	}

	public String getCompanionPlants() {
		return companionPlants;
	}

	public void setCompanionPlants(String companionPlants) {
		this.companionPlants = companionPlants;
	}

	public String getToxicPart() {
		return toxicPart;
	}

	public void setToxicPart(String toxicPart) {
		this.toxicPart = toxicPart;
	}

	public Integer getAllergenicity() {
		return allergenicity;
	}

	public void setAllergenicity(Integer allergenicity) {
		this.allergenicity = allergenicity;
	}

	public Integer getDeteringAnimals() {
		return deteringAnimals;
	}

	public void setDeteringAnimals(Integer deteringAnimals) {
		this.deteringAnimals = deteringAnimals;
	}

	public String getGrowthSuggestions() {
		return growthSuggestions;
	}

	public void setGrowthSuggestions(String growthSuggestions) {
		this.growthSuggestions = growthSuggestions;
	}

	public String getCultivar() {
		return cultivar;
	}

	public void setCultivar(String cultivar) {
		this.cultivar = cultivar;
	}

	public Integer getEndorsedBy() {
		return endorsedBy;
	}

	public void setEndorsedBy(Integer endorsedBy) {
		this.endorsedBy = endorsedBy;
	}

	public Integer getAvailableAt() {
		return availableAt;
	}

	public void setAvailableAt(Integer availableAt) {
		this.availableAt = availableAt;
	}

	public Integer getHardyScale() {
		return hardyScale;
	}

	public void setHardyScale(Integer hardyScale) {
		this.hardyScale = hardyScale;
	}

	public String getTouristSpots() {
		return touristSpots;
	}

	public void setTouristSpots(String touristSpots) {
		this.touristSpots = touristSpots;
	}

	public Integer getEaseOfPlanting() {
		return easeOfPlanting;
	}

	public void setEaseOfPlanting(Integer easeOfPlanting) {
		this.easeOfPlanting = easeOfPlanting;
	}

	public String getInterestingFacts() {
		return interestingFacts;
	}

	public void setInterestingFacts(String interestingFacts) {
		this.interestingFacts = interestingFacts;
	}

	public Date getTimeToProduceFrom() {
		return timeToProduceFrom;
	}

	public void setTimeToProduceFrom(Date timeToProduceFrom) {
		this.timeToProduceFrom = timeToProduceFrom;
	}

	public Date getTimeToProduceTo() {
		return timeToProduceTo;
	}

	public void setTimeToProduceTo(Date timeToProduceTo) {
		this.timeToProduceTo = timeToProduceTo;
	}

	public Integer getRootDepth() {
		return rootDepth;
	}

	public void setRootDepth(Integer rootDepth) {
		this.rootDepth = rootDepth;
	}

	public Date getDurationFrom() {
		return durationFrom;
	}

	public void setDurationFrom(Date durationFrom) {
		this.durationFrom = durationFrom;
	}

	public Date getDurationTo() {
		return durationTo;
	}

	public void setDurationTo(Date durationTo) {
		this.durationTo = durationTo;
	}

	public Integer getpH() {
		return pH;
	}

	public void setpH(Integer pH) {
		this.pH = pH;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getD4() {
		return d4;
	}

	public void setD4(Integer d4) {
		this.d4 = d4;
	}

	public Integer getDeciduousCover() {
		return deciduousCover;
	}

	public void setDeciduousCover(Integer deciduousCover) {
		this.deciduousCover = deciduousCover;
	}

	public Integer getLarval_Butterfly_Host() {
		return larval_Butterfly_Host;
	}

	public void setLarval_Butterfly_Host(Integer larval_Butterfly_Host) {
		this.larval_Butterfly_Host = larval_Butterfly_Host;
	}

	public Integer getSap() {
		return sap;
	}

	public void setSap(Integer sap) {
		this.sap = sap;
	}

	public Date getSeedTimingFrom() {
		return seedTimingFrom;
	}

	public void setSeedTimingFrom(Date seedTimingFrom) {
		this.seedTimingFrom = seedTimingFrom;
	}

	public Date getSeedTimingTo() {
		return seedTimingTo;
	}

	public void setSeedTimingTo(Date seedTimingTo) {
		this.seedTimingTo = seedTimingTo;
	}

	public Integer getSeedType() {
		return seedType;
	}

	public void setSeedType(Integer seedType) {
		this.seedType = seedType;
	}

	public Integer getIrrigated() {
		return irrigated;
	}

	public void setIrrigated(Integer irrigated) {
		this.irrigated = irrigated;
	}

	public String getCultivarNames() {
		return cultivarNames;
	}

	public void setCultivarNames(String cultivarNames) {
		this.cultivarNames = cultivarNames;
	}

	public Integer getAirPurifying() {
		return airPurifying;
	}

	public void setAirPurifying(Integer airPurifying) {
		this.airPurifying = airPurifying;
	}

	public Integer getSoilPurifying() {
		return soilPurifying;
	}

	public void setSoilPurifying(Integer soilPurifying) {
		this.soilPurifying = soilPurifying;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Integer getCanWeShareTheSource() {
		return canWeShareTheSource;
	}

	public void setCanWeShareTheSource(Integer canWeShareTheSource) {
		this.canWeShareTheSource = canWeShareTheSource;
	}

	public String getTasteOfFruit() {
		return tasteOfFruit;
	}

	public void setTasteOfFruit(String tasteOfFruit) {
		this.tasteOfFruit = tasteOfFruit;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Integer getSeedsAndNuts() {
		return seedsAndNuts;
	}

	public void setSeedsAndNuts(Integer seedsAndNuts) {
		this.seedsAndNuts = seedsAndNuts;
	}

	public Integer getHoaApproved() {
		return hoaApproved;
	}

	public void setHoaApproved(Integer hoaApproved) {
		this.hoaApproved = hoaApproved;
	}

	public Integer getColdTolerance() {
		return coldTolerance;
	}

	public void setColdTolerance(Integer coldTolerance) {
		this.coldTolerance = coldTolerance;
	}

	public Integer getDroughtTolerance() {
		return droughtTolerance;
	}

	public void setDroughtTolerance(Integer droughtTolerance) {
		this.droughtTolerance = droughtTolerance;
	}

	public Integer getMoistureTolerance() {
		return moistureTolerance;
	}

	public void setMoistureTolerance(Integer moistureTolerance) {
		this.moistureTolerance = moistureTolerance;
	}

	public String getFall() {
		return fall;
	}

	public void setFall(String fall) {
		this.fall = fall;
	}

	public String getSpring() {
		return spring;
	}

	public void setSpring(String spring) {
		this.spring = spring;
	}

	public String getSummer() {
		return summer;
	}

	public void setSummer(String summer) {
		this.summer = summer;
	}

	public String getWinter() {
		return winter;
	}

	public void setWinter(String winter) {
		this.winter = winter;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Integer maintenance) {
		this.maintenance = maintenance;
	}

	public Integer getWildlife() {
		return wildlife;
	}

	public void setWildlife(Integer wildlife) {
		this.wildlife = wildlife;
	}

	public Integer getMap2() {
		return map2;
	}

	public void setMap2(Integer map2) {
		this.map2 = map2;
	}

	public Integer getGrowthSize() {
		return growthSize;
	}

	public void setGrowthSize(Integer growthSize) {
		this.growthSize = growthSize;
	}

	public Integer getWhenToPlant() {
		return whenToPlant;
	}

	public void setWhenToPlant(Integer whenToPlant) {
		this.whenToPlant = whenToPlant;
	}

	public Integer getPurpose2() {
		return purpose2;
	}

	public void setPurpose2(Integer purpose2) {
		this.purpose2 = purpose2;
	}

	public Integer getCompanionPlants2() {
		return companionPlants2;
	}

	public void setCompanionPlants2(Integer companionPlants2) {
		this.companionPlants2 = companionPlants2;
	}

	public Integer getCultivar2() {
		return cultivar2;
	}

	public void setCultivar2(Integer cultivar2) {
		this.cultivar2 = cultivar2;
	}

	public Integer getDuration2() {
		return duration2;
	}

	public void setDuration2(Integer duration2) {
		this.duration2 = duration2;
	}

	@Override
	public String toString() {
		return "PlantEntity [id=" + id + ", scientificName=" + scientificName
				+ ", houstonCommonName=" + houstonCommonName + ", commonName="
				+ commonName + "]";
	}
}