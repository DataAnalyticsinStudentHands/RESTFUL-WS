package dash.pojo;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.beanutils.BeanUtils;

import dash.dao.ResponseEntity;
import dash.helpers.DateISO8601Adapter;
import dash.security.IAclObject;

/**
 * object resource placeholder for json/xml representation
 *
 * @author tyler.swensen@gmail.com
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Response implements  IAclObject {

	/** id of the object */
	@XmlElement(name = "id")
	private Long id;
	
	@XmlElement(name = "owner_id")
	private Long owner_id;
	
	@XmlElement(name = "insertion_date")
	@XmlJavaTypeAdapter(DateISO8601Adapter.class)
	private Date insertion_date;
	
	@XmlElement(name = "latest_date")
	@XmlJavaTypeAdapter(DateISO8601Adapter.class)
	private Date latest_update;
	
	@XmlElement(name = "is_complete")
	private boolean is_complete;

	public Response(ResponseEntity objectEntity) {
		try {
			BeanUtils.copyProperties(this, objectEntity);
		} catch ( IllegalAccessException e) {

			e.printStackTrace();
		} catch ( InvocationTargetException e) {

			e.printStackTrace();
		}
	}

	public Response() {
	}

	public Response(Long owner_id, String basic_field_sample,
			Date insertion_date, Date latest_update) {
		super();
		this.owner_id = owner_id;
		this.insertion_date = insertion_date;
		this.latest_update = latest_update;
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
	

}
