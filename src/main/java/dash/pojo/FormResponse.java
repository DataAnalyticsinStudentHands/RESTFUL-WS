package dash.pojo;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.beanutils.BeanUtils;

import dash.dao.FormResponseEntity;
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
public class FormResponse implements  IAclObject {

	/** id of the object */
	@XmlElement(name = "id")
	private Long id;
	
	@XmlElement(name = "form_id")
	private Long form_id;
	
	@XmlElement(name = "owner_id")
	private Long owner_id;
	
	@XmlElement(name = "insertion_date")
	@XmlJavaTypeAdapter(DateISO8601Adapter.class)
	private Date insertion_date;
	
	@XmlElement(name = "latest_update")
	@XmlJavaTypeAdapter(DateISO8601Adapter.class)
	private Date latest_update;
	
	@XmlElement(name = "is_complete")
	private boolean is_complete;
	
	@XmlElement(name = "document_folder")
	private String document_folder;
	
	
	@XmlElement(name= "entries")
	private Set<Entry> entries= new HashSet<Entry>();

	public FormResponse(FormResponseEntity objectEntity) {
		try {
			BeanUtils.copyProperties(this, objectEntity);
		} catch ( IllegalAccessException e) {

			e.printStackTrace();
		} catch ( InvocationTargetException e) {

			e.printStackTrace();
		}
	}

	public FormResponse() {
	}

	public FormResponse(Long form_id, Long owner_id, boolean is_complete,
			Set<Entry> entries, String document_folder) {
		super();
		this.form_id = form_id;
		this.owner_id = owner_id;
		this.is_complete = is_complete;
		this.entries = entries;
		this.document_folder= document_folder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getForm_id() {
		return form_id;
	}

	public void setForm_id(Long form_id) {
		this.form_id = form_id;
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

	public Set<Entry> getEntries() {
		return entries;
	}

	public void setEntries(Set<Entry> entries) {
		this.entries = entries;
	}

	public String getDocument_folder() {
		return document_folder;
	}

	public void setDocument_folder(String document_folder) {
		this.document_folder = document_folder;
	}
	
	
	

}
