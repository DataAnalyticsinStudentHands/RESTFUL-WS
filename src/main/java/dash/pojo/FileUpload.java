package dash.pojo;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.beanutils.BeanUtils;








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
@Entity
@Table(name="file_uploads")
public class FileUpload implements  IAclObject {

	/** id of the object */
	@Id
	@GeneratedValue
	@XmlElement(name = "id")
	@Column(name = "id")
	private Long id;
	
	/** The user this file belongs to */
	@XmlElement(name = "user_id")
	@Column(name = "user_id")
	private Long user_id;
	
	@XmlElement(name = "form_id")
	@Column(name = "form_id")
	private Long form_id;
	
	/**The path of the file relative to the applications root uploads folder.*/
	@XmlElement(name = "path")
	@Column(name = "path")
	private String path;

	/** basic_field_sample of the object */
	@XmlElement(name = "file_name")
	@Column(name = "file_name")
	private String file_name;

	/** basic_field_sample of the object */
	@XmlElement(name = "content_type")
	@Column(name = "content_type")
	private String content_type;
	
	/** insertion date in the database */
	@XmlElement(name = "upload_timestamp")
	@Column(name = "upload_timestamp")
	@XmlJavaTypeAdapter(DateISO8601Adapter.class)
	private Date upload_timestamp;

	public FileUpload(){
		super();
	}
	
	public FileUpload(Long user_id, Long form_id, String path, String file_name, String content_type) {
		super();
		this.user_id = user_id;
		this.form_id = form_id;
		this.path = path;
		this.file_name = file_name;
		this.content_type= content_type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}
	

	public Long getForm_id() {
		return form_id;
	}

	public void setForm_id(Long form_id) {
		this.form_id = form_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Date getUpload_timestamp() {
		return upload_timestamp;
	}

	public void setUpload_timestamp(Date upload_timestamp) {
		this.upload_timestamp = upload_timestamp;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}




}
