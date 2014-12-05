package dash.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.beanutils.BeanUtils;

import dash.helpers.DateISO8601Adapter;
import dash.pojo.Response;

/**
 * This is an example implementation of an entity for a simple object (non-user)
 *
 * @author Tyler.swensen@gmail.com
 *
 */
@Entity
@Table(name="sample_object")
public class ResponseEntity implements Serializable {

	private static final long serialVersionUID = -8039686696076337053L;

	/** id of the user
	 * Be aware that every object/entity MUST have an id */
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name = "owner_id")
	private Long owner_id;
	
	@Column(name = "insertion_date")
	private Date insertion_date;
	
	@Column(name = "latest_date")
	private Date latest_update;
	
	@Column(name = "is_complete")
	private boolean is_complete;

	public ResponseEntity(){}


	public ResponseEntity(Response response) {
		try {
			BeanUtils.copyProperties(this, response);
		} catch ( IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public ResponseEntity(Long owner_id, Date insertion_date,
			Date latest_update, boolean is_complete) {
		super();
		this.owner_id = owner_id;
		this.insertion_date = insertion_date;
		this.latest_update = latest_update;
		this.is_complete = is_complete;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getOwner_id() {
		return owner_id;
	}


	public void setOwner_id(Long owner_id) {
		this.owner_id = owner_id;
	}


	public Date getInsertion_date() {
		return insertion_date;
	}


	public void setInsertion_date(Date insertion_date) {
		this.insertion_date = insertion_date;
	}


	public Date getLatest_update() {
		return latest_update;
	}


	public void setLatest_update(Date latest_update) {
		this.latest_update = latest_update;
	}


	public boolean isIs_complete() {
		return is_complete;
	}


	public void setIs_complete(boolean is_complete) {
		this.is_complete = is_complete;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
