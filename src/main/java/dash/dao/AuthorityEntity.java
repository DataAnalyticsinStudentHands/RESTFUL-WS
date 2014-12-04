package dash.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * User entity
 * @author tyler.swensen@gmail.com
 *
 * The entity used when making changes to the authorities table.  Does not have its own Dao,
 * instead is used in the UserDao.
 */
@Entity
@Table(name="authorities")
public class AuthorityEntity implements Serializable {

	private static final long serialVersionUID = -8039686696076337053L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	/** username of the user */
	@Column(name = "username")
	private String username;


	/** firstname of the user */
	@Column(name = "authority")
	private String authority;

	

	public AuthorityEntity(){}



	public AuthorityEntity(Long id, String username, String authority) {
		super();
		this.id = id;
		this.username = username;
		this.authority = authority;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getAuthority() {
		return authority;
	}



	public void setAuthority(String authority) {
		this.authority = authority;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	
}
