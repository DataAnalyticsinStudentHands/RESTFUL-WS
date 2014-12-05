package dash.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;

import dash.filters.AppConstants;
import dash.filters.AppConstants.InputValidation;
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
	/** id of the object */
	@Column(name = "id")
	private Long id;
	
	/** Represents the form this question is a part of*/
	@Column(name = "form_id")
	private Long form_id;
	
	/** Defines the type of question, ie. TextInput, MultipleChoice, Checkbox... ect */
	@Column(name = "component")
	private  String component;
	
	/** Can this question be changed, possibly useful to prevent accidental removal of a
	 * question which is critical to an orginiztion or is a dependency for an external application */
	@Column(name = "editable")
	private boolean editable;

	/** The order in which this question appears on the form it belongs to. */
	@Column(name = "index")
	private int index;
	
	/** This is the primary text that identifies the question to both the person filling out the form
	 * and to the user building the form.
	 */
	@Column(name = "label")
	private String label;
	
	/** This is displayed as a low profile hint beneath the input area */
	@Column(name = "description")
	private String description;
	
	/** A string which appears as ghosted text inside the input area */
	@Column(name = "placeholder")
	private String placeholder;
	
	/** An array of strings which hold possible responses to multiple choice and checkbox questions. */
	@Column(name = "options")
	private String options[];
	
	/** Must the question have a response in order to submit the form */
	@Column(name = "required")
	private boolean required;
	
	/** Are there any constraints on what text may be submitted as input, ie. email, number, url ect.. */
	@Column(name = "validation")
	private AppConstants.InputValidation validation;

	/** insertion date in the database */
	@Column(name = "insertion_date")
	private Date insertion_date;
	
	/** timestamp of latest update */
	@Column(name = "latest_update")
	private Date latest_update;

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

	

	public QuestionEntity(Long form_id, String component, boolean editable,
			int index, String label, String description, String placeholder,
			String[] options, boolean required, InputValidation validation,
			Date insertion_date, Date latest_update) {
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
		this.insertion_date = insertion_date;
		this.latest_update= latest_update;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Date getLatest_update() {
		return insertion_date;
	}

	public void setLatest_update(Date latest_update) {
		this.latest_update = latest_update;
	}

}
