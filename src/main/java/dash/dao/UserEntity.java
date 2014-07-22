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

import dash.pojo.User;

/**
 * User entity
 * @author plindner
 *
 */
@Entity
@Table(name="user_data")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -8039686696076337053L;

	/** id of the user */
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	/** username of the user */
	@Column(name = "username")
	private String username;


	/** firstname of the user */
	@Column(name = "firstName")
	private String firstName;

	/** lastname of the user */
	@Column(name = "lastName")
	private String lastName;

	/** city of the user */
	@Column(name = "city")
	private String city;

	/** home phone number of the user */
	@Column(name = "homePhone")
	private String homePhone;

	/** cellPhone number of the user */
	@Column(name = "cellPhone")
	private String cellPhone;

	/** email address of the user */
	@Column(name = "email")
	private String email;

	/** path to stored picture of the user */
	@Column(name = "picture")
	private String picturePath;

	/** insertion date in the database */
	@Column(name = "insertion_date")
	private Date insertionDate;

	public UserEntity(){}

	public UserEntity( String username, 
			String firstName,  String lastName,  String city,
			String homePhone,  String cellPhone,  String email,
			String picturePath) {

		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.homePhone = homePhone;
		this.cellPhone = cellPhone;
		this.email = email;
		this.picturePath = picturePath;

	}

	public UserEntity(User user) {
		try {
			BeanUtils.copyProperties(this, user);
		} catch ( IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picturePath;
	}

	public void setPicture(String picturePath) {
		this.picturePath = picturePath;
	}

	public Long getId() {
		return id;
	}

	public void setId( Long id) {
		this.id = id;
	}

	public Date getInsertionDate() {
		return insertionDate;
	}

	public void setInsertionDate(Date insertionDate) {
		this.insertionDate = insertionDate;
	}

}
