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

import org.apache.commons.beanutils.BeanUtils;

import dash.filters.AppConstants;
import dash.filters.AppConstants.QuestionType;
import dash.pojo.Question;

/**
 * This is an example implementation of an entity for a simple object (non-user)
 *
 * @author Tyler.swensen@gmail.com
 *
 */
@Entity
@Table(name="sample_object")
public class QuestionEntity implements Serializable {

	private static final long serialVersionUID = -8039686696076337053L;

	/** id of the user
	 * Be aware that every object/entity MUST have an id */
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name = "type")
	private AppConstants.QuestionType type;
	
	@Column(name= "form_order")
	private int form_order;
	


	public QuestionEntity(){}

	public QuestionEntity(Question question) {
		try {
			BeanUtils.copyProperties(this, question);
		} catch ( IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public QuestionEntity(QuestionType type, int form_order, String text) {
		super();
		this.type = type;
		this.form_order = form_order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppConstants.QuestionType getType() {
		return type;
	}

	public void setType(AppConstants.QuestionType type) {
		this.type = type;
	}

	public int getForm_order() {
		return form_order;
	}

	public void setForm_order(int form_order) {
		this.form_order = form_order;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
