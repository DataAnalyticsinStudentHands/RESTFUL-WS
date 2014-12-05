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
import dash.filters.AppConstants.InputValidation;
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
	
	/** Defines the type of question, ie. TextInput, MultipleChoice, Checkbox... ect */
	@XmlElement(name = "component")
	private  String component;
	
	/** Can this question be changed, possibly usefull to prevent accidental removal of a
	 * question which is critical to an orginiztion or is a dependency for an external application */
	@XmlElement(name = "editable")
	private boolean editable;

	/** The order in which this question appears on the form it belongs to. */
	@XmlElement(name = "index")
	private int index;
	
	/** This is the primary text that identifies the question to both the person filling out the form
	 * and to the user building the form.
	 */
	@XmlElement(name = "label")
	private String label;
	
	/** This is displayed as a low profile hint beneath the input area */
	@XmlElement(name = "description")
	private String description;
	
	/** A string which appears as ghosted text inside the input area */
	@XmlElement(name = "placeholder")
	private String placeholder;
	
	/** An array of strings which hold possible responses to multiple choice and checkbox questions. */
	@XmlElement(name = "options")
	private String options[];
	
	/** Must the question have a response in order to submit the form */
	@XmlElement(name = "required")
	private boolean required;
	
	/** Are there any constraints on what text may be submitted as input, ie. email, number, url ect.. */
	@XmlElement(name = "validation")
	private AppConstants.InputValidation validation;

	/** insertion date in the database */
	@XmlElement(name = "insertion_date")
	@XmlJavaTypeAdapter(DateISO8601Adapter.class)
	private Date insertion_date;
	
	/** insertion date in the database */
	@XmlElement(name = "latest_update")
	@XmlJavaTypeAdapter(DateISO8601Adapter.class)
	private Date latest_update;

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

	public Question(Long form_id, String component, boolean editable,
			int index, String label, String description, String placeholder,
			String[] options, boolean required, InputValidation validation) {
		super();
		this.form_id = form_id;
		this.component = component;
		this.editable = editable;
		this.index = index;
		this.label = label;
		this.description = description;
		this.placeholder = placeholder;
		this.options = options;
		this.required = required;
		this.validation = validation;
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

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public AppConstants.InputValidation getValidation() {
		return validation;
	}

	public void setValidation(AppConstants.InputValidation validation) {
		this.validation = validation;
	}

	public Date getInsertion_date() {
		return insertion_date;
	}

	public void setInsertion_date(Date insertion_date) {
		this.insertion_date = insertion_date;
	}
	
	public Date getLatest_update() {
		return insertion_date;
	}

	public void setLatest_update(Date latest_update) {
		this.latest_update = latest_update;
	}

	
}
