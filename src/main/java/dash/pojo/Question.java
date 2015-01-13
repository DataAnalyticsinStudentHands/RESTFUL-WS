package dash.pojo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.PrePersist;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.beanutils.BeanUtils;









import dash.filters.AppConstants;
import dash.filters.AppConstants.InputValidation;
import dash.filters.AppConstants.QuestionType;
import dash.helpers.DateISO8601Adapter;
import dash.security.IAclObject;

/**
 * An embeddable object resource  for json/xml representation
 *
 * @author tyler.swensen@gmail.com
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Embeddable
public class Question  implements Serializable {
		
	
	private static final long serialVersionUID = -8334607009238618323L;

	/** Defines the type of question, ie. TextInput, MultipleChoice, Checkbox... ect */
	@XmlElement(name = "component")
	@Column(name = "component")
	private  String component;
	
	@GeneratedValue
	@XmlElement(name="question_id")
	@Column(name="question_id")
	private Long question_id;
	
	/** Can this question be changed, possibly useful to prevent accidental removal of a
	 * question which is critical to an organization or is a dependency for an external application */
	@XmlElement(name = "editable")
	@Column (name = "editable")
	private boolean editable;

	/** The order in which this question appears on the form it belongs to. */
	@XmlElement(name = "index")
	@Column (name = "form_index")
	private int index;
	
	/** This is the primary text that identifies the question to both the person filling out the form
	 * and to the user building the form.
	 */
	@XmlElement(name = "label")
	@Column (name = "label")
	private String label;
	
	/** This is displayed as a low profile hint beneath the input area */
	@XmlElement(name = "description")
	@Column (name = "description")
	private String description;
	
	/** A string which appears as ghosted text inside the input area */
	@XmlElement(name = "placeholder")
	@Column (name = "placeholder")
	private String placeholder;
	
	/** An array of strings which hold possible responses to multiple choice and checkbox questions. */
	
	@XmlElement(name = "options")
	@Column(name="options")
	private String options;
	
	/** Must the question have a response in order to submit the form */
	@XmlElement(name = "required")
	@Column(name = "required")
	private boolean required;
	
	/** Are there any constraints on what text may be submitted as input, ie. email, number, url ect.. */
	@XmlElement(name = "validation")
	@Column(name = "validation")
	private AppConstants.InputValidation validation;




	public Question() {
	}




	public Question(String component, boolean editable, int index,
			String label, String description, String placeholder,
			String options, boolean required, InputValidation validation) {
		super();
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




	public String getComponent() {
		return component;
	}




	public void setComponent(String component) {
		this.component = component;
	}




	public Long getQuestion_id() {
		return question_id;
	}




	public void setQuestion_id(Long question_id) {
		this.question_id = question_id;
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




	public String getOptions() {
		return options;
	}




	public void setOptions(String options) {
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




	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
