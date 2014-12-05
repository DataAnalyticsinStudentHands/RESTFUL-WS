package dash.pojo;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.beanutils.BeanUtils;

import dash.dao.QuestionEntity;
import dash.filters.AppConstants;
import dash.filters.AppConstants.QuestionType;
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
public class Question implements  IAclObject {

	/** id of the object */
	@XmlElement(name = "id")
	private Long id;

	/** Represents the form this question is a part of*/
	@XmlElement(name = "form_id")
	private Long form_id;

	@XmlElement(name = "type")
	private AppConstants.QuestionType type;
	
	@XmlElement(name= "form_order")
	private int form_order;
	

	public Question(QuestionEntity objectEntity) {
		try {
			BeanUtils.copyProperties(this, objectEntity);
		} catch ( IllegalAccessException e) {

			e.printStackTrace();
		} catch ( InvocationTargetException e) {

			e.printStackTrace();
		}
	}

	public Question() {
	}

	public Question(Long form_id, QuestionType type, int form_order, String text) {
		super();
		this.form_id = form_id;
		this.type = type;
		this.form_order = form_order;
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




}
