package dash.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.beanutils.BeanUtils;

import dash.pojo.Entry;
import dash.pojo.Form;
import dash.pojo.Form.THEME;
import dash.pojo.Question;

/**
 * This is an example implementation of an entity for a simple object (non-user)
 *
 * @author Tyler.swensen@gmail.com
 *
 */
@Entity
@Table(name="forms")
public class FormEntity implements Serializable {

	private static final long serialVersionUID = -8039686696076337053L;
	
	/** id of the user
	 * Be aware that every object/entity MUST have an id */
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	/** basic_field_sample of the user */
	@Column(name = "name")
	private String name;

	/** insertion date in the database */
	@Column(name = "insertion_date")
	private Date insertion_date;
	
	@ElementCollection (fetch= FetchType.EAGER)
	@CollectionTable(name = "questions", joinColumns = {@JoinColumn(name="form_id")})
	private Set<Question> questions= new HashSet<Question>();

	@Column(name = "public")
	private boolean publi;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "theme")
	private THEME theme;
	
	@Column(name = "redirect_to_url")
	private boolean redirect_to_url;
	
	@Column(name = "alert_for_response")
	private boolean alert_for_response;

	@Column(name = "email_embedded_responses")
	private boolean email_embedded_responses;
	
	@Column(name = "send_confirmation_email")
	private boolean send_confirmation_email; 
	
	@Column(name = "email_message")
	private String email_message;
	
	@Column(name = "completed_message")
	private String completed_message;
	
	@Column(name = "redirect_url")
	private String redirect_url;
	
	@Column(name = "expiration_date")
	private Date expiration_date;
	
	@Column(name = "closed_message")
	private String closed_message;
	
	public FormEntity(){}

	public FormEntity(String name, Set<Question> questions, boolean redirect_to_url,
			boolean enabled, boolean publi, boolean alert_for_response,
			boolean email_embedded_responses, boolean send_confirmation_email, 
			String email_message, String completed_message, String redirect_url, 
			Date expiration_date, String closed_message, THEME theme) {
		super();
		this.name = name;
		this.questions = questions;
		this.redirect_to_url = redirect_to_url;
		this.enabled = enabled;
		this.publi = publi;
		this.alert_for_response = alert_for_response;
		this.email_embedded_responses = email_embedded_responses;
		this.send_confirmation_email = send_confirmation_email;
		this.email_message = email_message;
		this.completed_message = completed_message;
		this.redirect_url = redirect_url;
		this.expiration_date = expiration_date;
		this.closed_message = closed_message;
		this.theme = theme;
	}

	public FormEntity(Form form) {
		try {
			BeanUtils.copyProperties(this, form);
		} catch ( IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Date getInsertion_date() {
		return insertion_date;
	}



	public void setInsertion_date(Date insertion_date) {
		this.insertion_date = insertion_date;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public boolean getPubli(){
		return publi;
	}
	
	public void setPubli(boolean newPubli){
		this.publi = newPubli;
	}
	
	public boolean getEnabled(){
		return enabled;
	}
	
	public void setEnabled(boolean newEnabled){
		this.enabled = newEnabled;
	}
	
	public THEME getTheme(){
		return theme;
	}
	
	public void setTheme(THEME newTheme){
		this.theme = newTheme;
	}
	public boolean getRedirect_to_url(){
		return redirect_to_url;
	}
	
	public void setRedirect_to_url(boolean newRedirect_to_url){
		this.redirect_to_url = newRedirect_to_url;
	}
	
	public boolean getAlert_for_response(){
		return alert_for_response;
	}
	
	public void setAlert_for_response(boolean newAlert_for_response){
		this.alert_for_response = newAlert_for_response;
	}
	
	public boolean getEmail_embedded_responses(){
		return email_embedded_responses;
	}
	
	public void setEmail_embedded_responses(boolean newEmail_embedded_responses){
		this.email_embedded_responses = newEmail_embedded_responses;
	}
	
	public boolean getSend_confirmation_email(){
		return send_confirmation_email;
	}
	
	public void setSend_confirmation_email(boolean newSend_confirmation_email){
		this.send_confirmation_email = newSend_confirmation_email;
	}
	
	public String getEmail_message(){
		return email_message;
	}
	
	public void setEmail_message(String newEmail_message){
		this.email_message = newEmail_message;
	}
	
	public String getCompleted_message(){
		return completed_message;
	}
	
	public void setCompleted_message(String newCompleted_message){
		this.completed_message = newCompleted_message;
	}
	
	public String getRedirect_url(){
		return redirect_url;
	}
	
	public void setRedirect_url(String newRedirect_url){
		this.redirect_url = newRedirect_url;
	}
	
	public Date getExpiration_date(){
		return expiration_date;
	}
	
	public void setExpiration_date(Date newExpiration_date){
		this.expiration_date = newExpiration_date;
	}
	
	public String getClosed_message(){
		return closed_message;
	}
	
	public void setClosed_message(String newClosed_message){
		this.closed_message = newClosed_message;
	}
}
