package dash.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;

import dash.errorhandling.AppException;
import dash.filters.AppConstants;

/**
 * This class represents a token which grants access to the password reset for a give user.
 *
 * @author Tyler.swensen@gmail.com
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Embeddable
@Table(name="validation_tokens")
public class ValidationTokenEntity implements Serializable {
	
	// Accounts for the different thing which a token might be used for
	public static enum TOKEN_TYPE{ 
		PASSWORD_RESET, EMAIL_ACTIVATION 
	}
		
	public static final int token_life_minutes=30;

	private static final long serialVersionUID = -8039686696076337053L;
/** id of the user
	 * Be aware that every object/entity MUST have an id *
	
	
	/** path to stored documents for this object */
	
	/*@Column(name = "user_id")
	private Long user_id;*/

	/** basic_field_sample of the user */
	@Column(name = "token")
	private String token;

	/** insertion date in the database */
	@Column(name = "expiration_date")
	private Date expiration_date;
	
	/** basic_field_sample of the user */
	@Enumerated(EnumType.STRING)
	@Column(name = "token_type")
	private TOKEN_TYPE token_type;
	

	public ValidationTokenEntity(){super();}


	public ValidationTokenEntity(TOKEN_TYPE token_type) throws AppException {
		super();
		this.token_type=token_type;
		
		//Set Expiration
		expiration_date=new Date();
		Calendar calendar=new GregorianCalendar(2009, 11, 31, 23, 30, 0);
		
		calendar.setTime(expiration_date);
		calendar.add(Calendar.MINUTE, this.token_life_minutes);
		if(calendar.getTime().after(expiration_date)){
			expiration_date=calendar.getTime();
		}else throw new AppException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
				404,
				"Expiration time generated is invalid.",
				 "  See ValidationTokenEntity.",
				AppConstants.DASH_POST_URL);
		this.token=RandomStringUtils.randomAlphanumeric(64);
	}


	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Date getExpiration_date() {
		return expiration_date;
	}


	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}


	public TOKEN_TYPE getToken_type() {
		return token_type;
	}


	public void setToken_type(TOKEN_TYPE token_type) {
		this.token_type = token_type;
	}


	public static int getTokenLifeMinutes() {
		return token_life_minutes;
	}



	

}
