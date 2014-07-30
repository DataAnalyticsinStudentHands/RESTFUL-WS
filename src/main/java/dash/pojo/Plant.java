package dash.pojo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.beanutils.BeanUtils;

import dash.dao.PlantEntity;
import dash.helpers.DateISO8601Adapter;
import dash.security.IAclObject;

/**
 * Plant resource placeholder for json/xml representation
 *
 * @author jVera
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Plant implements IAclObject
{
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "id")
	private Long id;

	@XmlElement(name = "scientificName")
	private String scientificName;
	
	@XmlElement(name = "houstonCommonName")
	private String houstonCommonName;
	
	@XmlElement(name = "commonName")
	private String commonName;
	
	@XmlElement(name = "class")
	private Integer classField;		// java reserves 'class'
	
	@XmlElement(name = "height_ft")
	private Integer height;			// in feet
	
	@XmlElement(name = "width_ft")
	private Integer width;			// in feet
	
	@XmlElement(name = "sun")
	private Integer sun;
	
	@XmlElement(name = "native")
	private Integer nativeField;		// java reserves 'native'
	
	@XmlElement(name = "soilConditions")
	private Integer soilConditions;
	
	@XmlElement(name = "map")
	private String map;
	
	@XmlElement(name = "plantTime")
	private Date plantTime;
	
	@XmlElement(name = "fruitTiming")
	private Integer fruitTiming;
	
	@XmlElement(name = "colorTiming")
	private Integer colorTiming;
	
	@XmlElement(name = "distanceBetweenPlants")
	private Integer distanceBetweenPlants;			// in feet
	
	@XmlElement(name = "annual")
	private Integer annual;
	
	@XmlElement(name = "color")
	private String color;
	
	@XmlElement(name = "hostPlantFor")
	private String hostPlantFor;
	
	@XmlElement(name = "diseaseTolerance")
	private Integer diseaseTolerance;
	
	@XmlElement(name = "pestTolerance")
	private Integer pestTolerance;
	
	@XmlElement(name = "food")
	private String food;
	
	@XmlElement(name = "purpose")
	private Integer purpose;
	
	@XmlElement(name = "growthRate")
	private Integer growthRate;

	@XmlElement(name = "edibleParts")
	private String edibleParts;
	
	@XmlElement(name = "companionPlants")
	private String companionPlants;
	
	@XmlElement(name = "toxicParts")
	private String toxicParts;
	
	@XmlElement(name = "allergencity")
	private Integer allergencity;
	
	@XmlElement(name = "deteringAnimals")
	private Integer deteringAnimals;
	
	@XmlElement(name = "growthSuggestions")
	private String growthSuggestions;
	
	@XmlElement(name = "cultivar")
	private String cultivar;

	@XmlElement(name = "endorsedBy")
	private Integer endorsedBy;
	
	@XmlElement(name = "availableAt")
	private Integer availableAt;
	
	@XmlElement(name = "hardyScale")
	private Integer hardyScale;
	
	@XmlElement(name = "touristsSpots")
	private String touristsSpots;
	
	@XmlElement(name = "easeOfPlanting")
	private Integer easeOfPlanting;
	
	@XmlElement(name = "interestingFacts")
	private String interestingFacts;
	
	@XmlElement(name = "timeToProduce")
	private Date timeToProduce;
	
	@XmlElement(name = "rootDepth")
	private Integer rootDepth;
	
	@XmlElement(name = "duration")
	private Date duration;
	
	@XmlElement(name = "pH")
	private Integer pH;
	
	@XmlElement(name = "price")
	private Integer price;
	
	@XmlElement(name = "D4")
	private Integer d4;
	
	@XmlElement(name = "deciduousCover")
	private Integer deciduousCover;
	
	@XmlElement(name = "larvalButterflyHost")
	private Integer Larval_Butterfly_Host;
	
	@XmlElement(name = "sap")
	private Integer sap;
	
	@XmlElement(name = "seedTiming")
	private Date seedTiming;
	
	@XmlElement(name = "seedType")
	private Integer seedType;
	
	@XmlElement(name = "irrigated")
	private Integer irrigated;
	
	@XmlElement(name = "cultivarNames")
	private String cultivarNames;

	@XmlElement(name = "airPurifying")
	private Integer airPurifying;
	
	@XmlElement(name = "soilPurifying")
	private Integer soilPurifying;
	
	@XmlElement(name = "dataSource")
	private String dataSource;
	
	@XmlElement(name = "camWeShareTheSource")
	private Integer canWeShareTheSource;
	
	@XmlElement(name = "tasteOfFruit")
	private String tasteOfFruit;
	
	@XmlElement(name = "website")
	private String website;
	
	@XmlElement(name = "seedsAndNuts")
	private Integer seedsAndNuts;
	
	@XmlElement(name = "hoaApproved")
	private Integer hoaApproved;
	
	@XmlElement(name = "coldTolerance")
	private Integer coldTolerance;
	
	@XmlElement(name = "droughtTolerance")
	private Integer droughtTolerance;
	
	@XmlElement(name = "moistureTolerance")
	private Integer moistureTolerance;
	
	@XmlElement(name = "fall")
	private String fall;
	
	@XmlElement(name = "spring")
	private String spring;
	
	@XmlElement(name = "summer")
	private String summer;
	
	@XmlElement(name = "winter")
	private String winter;

	@XmlElement(name = "fileStructure")
	private String fileStructure;
	
	public Plant(){}
	
	public Plant(PlantEntity plantEntity)
	{
		try
		{
			BeanUtils.copyProperties(this, plantEntity);
		}
		catch ( IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch ( InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}

	public Plant(Long id, String scientificName, String houstonCommonName,
			String commonName, Integer classField, Integer height,
			Integer width, Integer sun, Integer nativeField, Integer soilConditions,
			String map, Date plantTime, Integer fruitTiming, Integer colorTiming,
			Integer distanceBetweenPlants, Integer annual, String color,
			String hostPlantFor, Integer diseaseTolerance, Integer pestTolerance,
			String food, Integer purpose, Integer growthRate,
			String edibleParts, String companionPlants, String toxicParts,
			Integer allergencity, Integer deteringAnimals,
			String growthSuggestions, String cultivar, Integer endorsedBy,
			Integer availableAt, Integer hardyScale, String touristsSpots,
			Integer easeOfPlanting, String interestingFacts,
			Date timeToProduce, Integer rootDepth, Date duration, Integer pH,
			Integer price, Integer d4, Integer deciduousCover,
			Integer larval_Butterfly_Host, Integer sap, Date seedTiming,
			Integer seedType, Integer irrigated, String cultivarNames,
			Integer airPurifying, Integer soilPurifying, String dataSource,
			Integer canWeShareTheSource, String tasteOfFruit, String website,
			Integer seedsAndNuts, Integer hoaApproved, Integer coldTolerance,
			Integer droughtTolerance, Integer moistureTolerance, String fall,
			String spring, String summer, String winter, String fileStructure) {
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
		this.toxicParts = toxicParts;
		this.allergencity = allergencity;
		this.deteringAnimals = deteringAnimals;
		this.growthSuggestions = growthSuggestions;
		this.cultivar = cultivar;
		this.endorsedBy = endorsedBy;
		this.availableAt = availableAt;
		this.hardyScale = hardyScale;
		this.touristsSpots = touristsSpots;
		this.easeOfPlanting = easeOfPlanting;
		this.interestingFacts = interestingFacts;
		this.timeToProduce = timeToProduce;
		this.rootDepth = rootDepth;
		this.duration = duration;
		this.pH = pH;
		this.price = price;
		this.d4 = d4;
		this.deciduousCover = deciduousCover;
		Larval_Butterfly_Host = larval_Butterfly_Host;
		this.sap = sap;
		this.seedTiming = seedTiming;
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
		this.fileStructure = fileStructure;
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

	public String getToxicParts() {
		return toxicParts;
	}

	public void setToxicParts(String toxicParts) {
		this.toxicParts = toxicParts;
	}

	public Integer getAllergencity() {
		return allergencity;
	}

	public void setAllergencity(Integer allergencity) {
		this.allergencity = allergencity;
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

	public String getTouristsSpots() {
		return touristsSpots;
	}

	public void setTouristsSpots(String touristsSpots) {
		this.touristsSpots = touristsSpots;
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

	public Date getDuration() {
		return duration;
	}

	public void setDuration(Date duration) {
		this.duration = duration;
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
		return Larval_Butterfly_Host;
	}

	public void setLarval_Butterfly_Host(Integer larval_Butterfly_Host) {
		Larval_Butterfly_Host = larval_Butterfly_Host;
	}

	public Integer getSap() {
		return sap;
	}

	public void setSap(Integer sap) {
		this.sap = sap;
	}

	public Date getSeedTiming() {
		return seedTiming;
	}

	public void setSeedTiming(Date seedTiming) {
		this.seedTiming = seedTiming;
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

	public void setHqaApproved(Integer hoaApproved) {
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

	public String getFileStructure() {
		return fileStructure;
	}

	public void setFileStructure(String fileStructure) {
		this.fileStructure = fileStructure;
	}

	@Override
	public String toString() {
		return "Plant [id=" + id + ", scientificName=" + scientificName
				+ ", houstonCommonName=" + houstonCommonName + ", commonName="
				+ commonName + "]";
	}
}
