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

import dash.pojo.Form;

/**
 * This is an example implementation of an entity for a simple object (non-user)
 *
 * @author Tyler.swensen@gmail.com
 *
 */
@Entity
@Table(name="forms")
public class FormEntity implements Serializable {

	private static final long serialVersionUID = -8039686696076337053L;

	/** id of the user
	 * Be aware that every object/entity MUST have an id */
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	/** basic_field_sample of the user */
	@Column(name = "name")
	private String name;

	/** insertion date in the database */
	@Column(name = "insertion_date")
	private Date insertion_date;

	public FormEntity(){}


	
	public FormEntity(String name) {
		super();
		this.name = name;
	}



	public FormEntity(Form form) {
		try {
			BeanUtils.copyProperties(this, form);
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



	public Date getInsertion_date() {
		return insertion_date;
	}



	public void setInsertion_date(Date insertion_date) {
		this.insertion_date = insertion_date;
	}

	

}
