package dash.pojo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.beanutils.BeanUtils;

import dash.dao.SampleObjectEntity;
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
@Embeddable
public class Entry implements Serializable{

	
	private static final long serialVersionUID = -3313914953151072441L;

	/**Corresponds to the question.index that this is an answer to*/
	@XmlElement(name = "index")
	@Column(name = "form_index")
	private Long index;
	
	/** Corresponds to the question.label that this is an answer to*/
	@XmlElement(name = "label")
	@Column(name = "label")
	private String label;

	/** Contains the actual data entered by the person completing the form */
	@XmlElement(name = "value")
	@Column(name = "value")
	private String value;


	public Entry() {
	}

	

	public Entry(Long index, String label, String value) {
		super();
		this.index = index;
		this.label = label;
		this.value = value;
	}



	public Long getIndex() {
		return index;
	}



	public void setIndex(Long index) {
		this.index = index;
	}



	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
