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

import org.apache.commons.beanutils.BeanUtils;

import dash.pojo.Group;


/**
 * Group entity
 * @author tswensen
 * 
 */
@Entity
@Table(name="group_data")
public class GroupEntity implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1126021260367221880L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@GeneratedValue
	@Column(name="creation_timestamp")
	private Date creation_timestamp;
	
	public GroupEntity(){}

	public GroupEntity(Long id, String name, String description,
			Date creation_timestamp) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.creation_timestamp = creation_timestamp;
	}
	
	public GroupEntity(Group group){
		try {
			BeanUtils.copyProperties(this, group);
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
