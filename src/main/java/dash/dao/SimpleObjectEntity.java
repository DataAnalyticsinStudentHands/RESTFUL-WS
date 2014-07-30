package dash.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;

import dash.pojo.SimpleObject;

/**
 * SimpleObject entity
 * @author plindner
 *
 */
@Entity
@Table(name="simple_object")
public class SimpleObjectEntity implements Serializable {

	/** id of the object */
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	/** path to stored images for this object */
	@Column(name = "image_folder")
	private String image_folder;

	/** description of the object */
	@Column(name = "name")
	private String name;

	/** insertion date in the database */
	@Column(name = "creation_timestamp")
	private Date creation_timestamp;

	public SimpleObjectEntity(){}

	public SimpleObjectEntity( String image_folder, String name) {

		this.image_folder = image_folder;
		this.name = name;
	}

	public SimpleObjectEntity(SimpleObject simpleObject) {
		try {
			BeanUtils.copyProperties(this, simpleObject);
		} catch ( IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId( Long id) {
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
