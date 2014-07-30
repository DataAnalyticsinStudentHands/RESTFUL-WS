package dash.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;

import dash.dao.SimpleObjectEntity;
import dash.security.IAclObject;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleObject implements  Serializable, IAclObject{

	@XmlElement(name = "id")
	private Long id;	

	@XmlElement(name = "image_folder")
	private String image_folder;
	
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "creation_timestamp")
	private Date creation_timestamp;
	
	public Long getId() {
		return id;
	}	
	
	public SimpleObject(){}
	
	public SimpleObject(Long id, String image_folder, String name,
			Date creation_timestamp) {
		super();
		this.id = id;
		this.image_folder = image_folder;
		this.name = name;
		this.creation_timestamp = creation_timestamp;
	}
	
	public SimpleObject(SimpleObjectEntity simpleObjectEntity) {
		try {
			BeanUtils.copyProperties(this, simpleObjectEntity);
		} catch ( IllegalAccessException e) {

			e.printStackTrace();
		} catch ( InvocationTargetException e) {

			e.printStackTrace();
		}
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage_folder() {
		return image_folder;
	}

	public void setImage_folder(String image_folder) {
		this.image_folder = image_folder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreation_timestamp() {
		return creation_timestamp;
	}

	public void setCreation_timestamp(Date creation_timestamp) {
		this.creation_timestamp = creation_timestamp;
	}

	

}
