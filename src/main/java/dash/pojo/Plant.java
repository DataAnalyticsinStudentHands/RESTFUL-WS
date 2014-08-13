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
	
	@XmlElement(name = "plantTimeFrom")
	private Date plantTimeFrom;
	
	@XmlElement(name = "plantTimeTo")
	private Date plantTimeTo;
	
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
	
	@XmlElement(name = "timeToProduceFrom")
	private Date timeToProduceFrom;
	
	@XmlElement(name = "timeToProduceTo")
	private Date timeToProduceTo;
	
	@XmlElement(name = "rootDepth")
	private Integer rootDepth;
	
	@XmlElement(name = "durationFrom")
	private Date durationFrom;
	
	@XmlElement(name = "durationTo")
	private Date durationTo;
	
	@XmlElement(name = "pH")
	private Integer pH;
	
	@XmlElement(name = "price")
	private Integer price;
	
	@XmlElement(name = "D4")
	private Integer d4;
	
	@XmlElement(name = "deciduousCover")
	private Integer deciduousCover;
	
	@XmlElement(name = "larvalButterflyHost")
	private Integer larval_Butterfly_Host;
	
	@XmlElement(name = "sap")
	private Integer sap;
	
	@XmlElement(name = "seedTimingFrom")
	private Date seedTimingFrom;
	
	@XmlElement(name = "seedTimingTo")
	private Date seedTimingTo;
	
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

	@XmlElement(name = "imagePath")
	private String imagePath;
	
	@XmlElement(name = "maintenance")
	private Integer maintenance;
	
	@XmlElement(name = "wildlife")
	private Integer wildlife;
	
	@XmlElement(name = "map2")
	private Integer map2;
	
	@XmlElement(name = "growthSize")
	private Integer growthSize;
	
	@XmlElement(name = "whenToPlant")
	private Integer whenToPlant;
	
	@XmlElement(name = "purpose2")
	private Integer purpose2;
	
	@XmlElement(name = "companionPlants2")
	private Integer companionPlants2;
	
	@XmlElement(name = "cultivar2")
	private Integer cultivar2;
	
	@XmlElement(name = "duration")
	private Integer duration2;
	
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
			Integer width, Integer sun, Integer nativeField,
			Integer soilConditions, String map, Date plantTimeFrom,
			Date plantTimeTo, Integer fruitTiming, Integer colorTiming,
			Integer distanceBetweenPlants, Integer annual, String color,
			String hostPlantFor, Integer diseaseTolerance,
			Integer pestTolerance, String food, Integer purpose,
			Integer growthRate, String edibleParts, String companionPlants,
			String toxicParts, Integer allergencity, Integer deteringAnimals,
			String growthSuggestions, String cultivar, Integer endorsedBy,
			Integer availableAt, Integer hardyScale, String touristsSpots,
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
		return "Plant [id=" + id + ", scientificName=" + scientificName
				+ ", houstonCommonName=" + houstonCommonName + ", commonName="
				+ commonName + "]";
	}
}
