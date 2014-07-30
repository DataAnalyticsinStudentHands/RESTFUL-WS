package dash.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;

import dash.dao.GroupEntity;
import dash.security.IAclObject;


import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Group implements  IAclObject
{
	private static final long serialVersionUID = -1126021260367221880L;


	@XmlElement(name = "id")
	private Long id;
	

	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "description")
	private String description;
	
	@XmlElement(name = "creation_timestamp")
	private Date creation_timestamp;
	
	public Long getId() {
		return id;
	}

	
	
	public Group(Long id, String name, String description,
			Date creation_timestamp) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.creation_timestamp = creation_timestamp;
	}

	
	public Group(GroupEntity groupEntity) {
		try {
			BeanUtils.copyProperties(this, groupEntity);
		} catch ( IllegalAccessException e) {

			e.printStackTrace();
		} catch ( InvocationTargetException e) {

			e.printStackTrace();
		}
	}
	
	public Group(){}

	@Override
	public String toString() {
		return "Group ["
				+ (id != null ? "id=" + id + ", " : "")
				+ (name != null ? "name=" + name + ", " : "")
				+ (description != null ? "description=" + description + ", "
						: "")
				+ (creation_timestamp != null ? "creation_timestamp="
						+ creation_timestamp : "") + "]";
	}



	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreation_timestamp() {
		return creation_timestamp;
	}

	public void setCreation_timestamp(Date creation_timestamp) {
		this.creation_timestamp = creation_timestamp;
	}

	

}
