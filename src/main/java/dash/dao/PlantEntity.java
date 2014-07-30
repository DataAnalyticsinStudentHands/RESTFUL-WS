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
	/** id of the plant */
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	/** scientific name of the plant */
	@Column(name = "Scientific_Name")
	private String scientificName;
	
	/** houston common name of the plant */
	@Column(name = "houstonCommonName")
	private String houstonCommonName;
	
	/** common name of the plant */
	@Column(name = "Common_Name")
	private String commonName;
	
	/** class of the plant */
	@Column(name = "Class")
	private Integer classField;		// java reserves 'class'
	
	/** height of the plant */
	@Column(name = "Height_ft")
	private Integer height;			// in feet
	
	/** width of the plant */
	@Column(name = "Width_ft")
	private Integer width;			// in feet
	
	/** amount of sun needed */
	@Column(name = "Sun")
	private Integer sun;
	
	/** nativity of plant */
	@Column(name = "Native")
	private Integer nativeField;		// java reserves 'native'
	
	/** soil conditions of plant (amound of water)  */
	@Column(name = "Soil_Conditions")
	private Integer soilConditions;
	
	/** common name of the plant */
	@Column(name = "Map")
	private String map;
	
	/** time to plant */
	@Column(name = "When_to_plant")
	private Date plantTime;
	
	/** timing of fruit */
	@Column(name = "Fruit_Timing")
	private Integer fruitTiming;
	
	/** timing of color */
	@Column(name = "Color_Timing")
	private Integer colorTiming;
	
	/** distance between plants */
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
	
	@Column(name = "Time_to_Produce")
	private Date timeToProduce;
	
	@Column(name = "Root_Depth")
	private Integer rootDepth;
	
//	@Column(name = "Duration")
//	private Date duration;
//	
//	@Column(name = "pH")
//	private Integer pH;
//	
//	@Column(name = "Price")
//	private Integer price;
//	
//	@Column(name = "D4")
//	private Integer d4;
//	
//	@Column(name = "Deciduous_Cover")
//	private Integer deciduousCover;
//	
//	@Column(name = "Larval_Butterfly_Host")
//	private Integer Larval_Butterfly_Host;
//	
//	@Column(name = "Sap")
//	private Integer sap;
//	
//	@Column(name = "Seed_Timing")
//	private Date seedTiming;
//	
//	@Column(name = "Seed_Type")
//	private Integer seedType;
//	
//	@Column(name = "Irrigated")
//	private Integer irrigated;
//	
//	@Column(name = "Cultivar_Names")
//	private String cultivarNames;
//
//	@Column(name = "Air_Purifying")
//	private Integer airPurifying;
//	
//	@Column(name = "Soil_Purifying")
//	private Integer soilPurifying;
//	
//	@Column(name = "Data_Source")
//	private String dataSource;
//	
//	@Column(name = "Cam_we_share_the_source")
//	private Integer canWeShareTheSource;
//	
//	@Column(name = "Taste_of_Fruit")
//	private String tasteOfFruit;
//	
//	@Column(name = "Website")
//	private String website;
//	
//	@Column(name = "Seeds_and_Nuts")
//	private Integer seedsAndNuts;
//	
//	@Column(name = "HOA_approved")
//	private Integer hoaApproved;
//	
//	@Column(name = "Cold_Tolerance")
//	private Integer coldTolerance;
//	
//	@Column(name = "Drought_Tolerance")
//	private Integer droughtTolerance;
//	
//	@Column(name = "Moisture_Tolerance")
//	private Integer moistureTolerance;
//	
//	@Column(name = "Fall")
//	private String fall;
//	
//	@Column(name = "Spring")
//	private String spring;
//	
//	@Column(name = "Summer")
//	private String summer;
//	
//	@Column(name = "Winter")
//	private String winter;
//
//	@Column(name = "file_structure")
//	private String fileStructure;
	
	public PlantEntity(){}
	


	public PlantEntity(Long id, String scientificName, String houstonCommonName,
		String commonName, Integer classField, Integer height, Integer width,
		Integer sun, Integer nativeField, Integer soilConditions, String map,
		Date plantTime, Integer fruitTiming, Integer colorTiming,
		Integer distanceBetweenPlants, Integer annual, String color,
		String hostPlantFor, Integer diseaseTolerance, Integer pestTolerance,
		String food, Integer purpose, Integer growthRate, String edibleParts,
		String companionPlants, String toxicPart, Integer allergenicity,
		Integer deteringAnimals, String growthSuggestions, String cultivar,
		Integer endorsedBy, Integer availableAt, Integer hardyScale,
		String touristSpots, Integer easeOfPlanting, String interestingFacts,
		Date timeToProduce, Integer rootDepth) {
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
	this.plantTime = plantTime;
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
	this.timeToProduce = timeToProduce;
	this.rootDepth = rootDepth;
//	this.duration = duration;
}



	public PlantEntity(Long id)
	{
		super();
		this.id = id;
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

	public Date getPlantTime() {
		return plantTime;
	}

	public void setPlantTime(Date plantTime) {
		this.plantTime = plantTime;
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


	public Date getTimeToProduce() {
		return timeToProduce;
	}


	public void setTimeToProduce(Date timeToProduce) {
		this.timeToProduce = timeToProduce;
	}



	public Integer getRootDepth() {
		return rootDepth;
	}



	public void setRootDepth(Integer rootDepth) {
		this.rootDepth = rootDepth;
	}




	
	
}