package dash.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;











import org.apache.commons.beanutils.BeanUtils;

import dash.helpers.DateISO8601Adapter;
import dash.pojo.FormResponse;
import dash.pojo.Entry;

/**
 * This is an example implementation of an entity for a simple object (non-user)
 *
 * @author Tyler.swensen@gmail.com
 *
 */
@Entity
@Table(name="form_responses")
public class FormResponseEntity implements Serializable {

	private static final long serialVersionUID = -8039686696076337053L;

	/** id of the user
	 * Be aware that every object/entity MUST have an id */
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name = "form_id")
	private Long form_id;
	
	@Column(name = "owner_id")
	private Long owner_id;
	
	@Column(name = "insertion_date")
	private Date insertion_date;
	
	@Column(name = "latest_update")
	private Date latest_update;
	
	@Column(name = "is_complete")
	private boolean is_complete;
	
	@Column(name = "document_folder")
	private String document_folder;
	
	@ElementCollection(fetch= FetchType.EAGER)
	@CollectionTable(name = "form_response_entries", joinColumns = {@JoinColumn(name="form_response_id")})
    private Set<Entry> entries = new HashSet<Entry>();

	public FormResponseEntity(){}


	public FormResponseEntity(FormResponse formResponse) {
		try {
			BeanUtils.copyProperties(this, formResponse);
		} catch ( IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FormResponseEntity(Long form_id, Long owner_id, Date insertion_date,
			Date latest_update, boolean is_complete, Set<Entry> entries, String document_folder) {
		super();
		this.form_id = form_id;
		this.owner_id = owner_id;
		this.insertion_date = insertion_date;
		this.latest_update = latest_update;
		this.is_complete = is_complete;
		this.entries = entries;
		this.document_folder=document_folder;
	}


	public String getDocument_folder() {
		return document_folder;
	}


	public void setDocument_folder(String document_folder) {
		this.document_folder = document_folder;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Set<Entry> getEntries() {
		return entries;
	}


	public void setEntries(Set<Entry> entries) {
		this.entries = entries;
	}


}
