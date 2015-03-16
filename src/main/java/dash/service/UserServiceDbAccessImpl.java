 package dash.service;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dash.dao.UserDao;
import dash.dao.UserEntity;
import dash.dao.ValidationTokenEntity;
import dash.errorhandling.AppException;
import dash.filters.AppConstants;
import dash.helpers.NullAwareBeanUtilsBean;
import dash.pojo.User;
import dash.security.UserLoginController;

/**
 * Implementation of the business logic for our User object. Users are also used 
 * by SpringSecurity and the ACL's to determine authorization.
 * 
 * @author Tyler.swensen@gmail.com
 *
 */
@Component("userService")
public class UserServiceDbAccessImpl extends ApplicationObjectSupport implements
UserService {

	@Autowired
	UserDao userDao;
	
	@PersistenceContext(unitName = "dashPersistence")
	private EntityManager entityManager;

	@Autowired
	private MutableAclService mutableAclService;

	@Autowired
	private UserLoginController authoritiesController;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private SimpleMailMessage templateMessage;

	public static final String userRole = "ROLE_USER";
	
	
	
	public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

	/********************* Create related methods implementation ***********************/
	@Override
	@Transactional
	public Long createUser(User user) throws AppException {

		validateInputForCreation(user);

		//verify existence of resource in the db (feed must be unique)
		UserEntity userByName = userDao.getUserByName(user.getUsername());
		if (userByName != null) {
			throw new AppException(
					Response.Status.CONFLICT.getStatusCode(),
					409,
					"User with username already existing in the database with the id "
							+ userByName.getId(),
							"Please verify that the username and password are properly generated",
							AppConstants.DASH_POST_URL);
		}

		long userId = userDao.createUser(new UserEntity(user));
		user.setId(userId);
		authoritiesController.create(user, userRole);
		createUserACL(user, new PrincipalSid(user.getUsername()));
		return userId;
	}

	private void validateInputForCreation(User user) throws AppException {
		if (user.getUsername() == null) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "Provided data not sufficient for insertion",
					"Please verify that the username is properly generated/set",
					AppConstants.DASH_POST_URL);
		}
		if (user.getPassword() == null) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "Provided data not sufficient for insertion",
					"Please verify that the password is properly generated/set",
					AppConstants.DASH_POST_URL);
		} 
		//etc...
	}

	@Override
	@Transactional
	public void createUsers(List<User> users) throws AppException {
		for (User user : users) {
			createUser(user);
		}
	}


	/******************** Read related methods implementation **********************/
	@Override
	public List<User> getUsers(String orderByInsertionDate,
			Integer numberDaysToLookBack) throws AppException {

		//verify optional parameter numberDaysToLookBack first
		if(numberDaysToLookBack!=null){
			List<UserEntity> recentUsers = userDao
					.getRecentUsers(numberDaysToLookBack);
			return getUsersFromEntities(recentUsers);
		}

		if(isOrderByInsertionDateParameterValid(orderByInsertionDate)){
			throw new AppException(
					Response.Status.BAD_REQUEST.getStatusCode(),
					400,
					"Please set either ASC or DESC for the orderByInsertionDate parameter",
					null, AppConstants.DASH_POST_URL);
		}
		List<UserEntity> users = userDao.getUsers(orderByInsertionDate);

		return getUsersFromEntities(users);
	}
	
	@Override
	public List<User> getMyUser() throws AppException {
		return getUsers(null, null);
	}

	private boolean isOrderByInsertionDateParameterValid(
			String orderByInsertionDate) {
		return orderByInsertionDate!=null
				&& !("ASC".equalsIgnoreCase(orderByInsertionDate) || "DESC".equalsIgnoreCase(orderByInsertionDate));
	}

	@Override
	public User getUserById(Long id) throws AppException {
		UserEntity userById = userDao.getUserById(id);
		if (userById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The user you requested with id " + id
					+ " was not found in the database",
					"Verify the existence of the user with the id " + id
					+ " in the database", AppConstants.DASH_POST_URL);
		}

		return new User(userDao.getUserById(id));
	}
	
	@Override
	public User getUserByUsername(String username) throws AppException {
		UserEntity userByName = userDao.getUserByName(username);
		if (userByName == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The user you requested with username " + username
					+ " was not found in the database",
					"Verify the existence of the user with the username " + username
					+ " in the database", AppConstants.DASH_POST_URL);
		}

		return new User(userByName);
	}


	private List<User> getUsersFromEntities(List<UserEntity> userEntities) {
		List<User> response = new ArrayList<User>();
		for (UserEntity userEntity : userEntities) {
			response.add(new User(userEntity));
		}

		return response;
	}

	public List<User> getRecentUsers(int numberOfDaysToLookBack) {
		List<UserEntity> recentUsers = userDao
				.getRecentUsers(numberOfDaysToLookBack);

		return getUsersFromEntities(recentUsers);
	}

	@Override
	public int getNumberOfUsers() {
		int totalNumber = userDao.getNumberOfUsers();

		return totalNumber;

	}

	@Override
	public List<String> getRole(User user) {
		ArrayList<String> tempRole = new ArrayList<String>();
		tempRole.add(userDao.getRoleByName(user.getUsername()));
		return tempRole;
	}
	
	protected String getUsername() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		if (auth.getPrincipal() instanceof UserDetails) {
			return ((UserDetails) auth.getPrincipal()).getUsername();
		} else {
			return auth.getPrincipal().toString();
		}
	}

	/********************* UPDATE-related methods implementation ***********************/
	@Override
	@Transactional
	public void updateFullyUser(User user) throws AppException {
		//do a validation to verify FULL update with PUT
		
		User verifyUserExistenceById = verifyUserExistenceById(user
				.getId());
		if (verifyUserExistenceById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ user.getId(),
							AppConstants.DASH_POST_URL);
		}
		
		copyAllProperties(verifyUserExistenceById, user);
		userDao.updateUser(new UserEntity(verifyUserExistenceById));
	}

	
	
	/**
	 * Allows for merging bean with object does not ignore null properties.
	 * 
	 * 
	 */
	private void copyAllProperties(User verifyUserExistenceById, User user) {

		BeanUtilsBean withNull=new BeanUtilsBean();
		try {
			withNull.copyProperty(verifyUserExistenceById, "firstName", user.getFirstName());
			withNull.copyProperty(verifyUserExistenceById, "lastName", user.getLastName());
			withNull.copyProperty(verifyUserExistenceById, "city", user.getCity());
			withNull.copyProperty(verifyUserExistenceById, "homePhone", user.getHomePhone());
			withNull.copyProperty(verifyUserExistenceById, "cellPhone", user.getCellPhone());
			withNull.copyProperty(verifyUserExistenceById, "email", user.getEmail());
			withNull.copyProperty(verifyUserExistenceById, "picture", user.getPicture());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private User verifyUserExistenceById(Long id) {
		UserEntity userById = userDao.getUserById(id);
		if (userById == null) {
			return null;
		} else {
			return new User(userById);
		}
	}

	@Override
	@Transactional
	public void updatePartiallyUser(User user) throws AppException {
		//do a validation to verify existence of the resource
		User verifyUserExistenceById = verifyUserExistenceById(user.getId());
		if (verifyUserExistenceById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ user.getId(), AppConstants.DASH_POST_URL);
		}
		copyPartialProperties(verifyUserExistenceById, user);
		userDao.updateUser(new UserEntity(verifyUserExistenceById));

	}

	private void copyPartialProperties(User verifyUserExistenceById, User user) {

		BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifyUserExistenceById, user);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Transactional
	public void requestPasswordReset(User user, UriInfo uri) throws AppException{
		UserEntity userEntity=userDao.getUserById(user.getId());
		if(userEntity.isIs_email_verified())
		{
			ValidationTokenEntity tokenEntity= new ValidationTokenEntity(
					ValidationTokenEntity.TOKEN_TYPE.PASSWORD_RESET);
			userEntity.getValidation_tokens().add(tokenEntity);
			//Then email the token
			SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
			msg.setSubject("Password Reset Request");
	        msg.setTo(userEntity.getEmail());
	        msg.setText(
	            "Dear " + userEntity.getFirstName()+ " "
	                + userEntity.getLastName()
	                + ", \n\nWe recieved a request to reset you password for "+AppConstants.APPLICATION_NAME+"."
            		+ "  To reset your password please click the following link.\n\n"
	                + uri.getBaseUri() + "users/"+userEntity.getId()+"/tokenValidation?token="
	                + tokenEntity.getToken()
	                +"\n\n\nIf you did not attempt to reset your password please contact us immidiately.");
	        try{
	            this.mailSender.send(msg);
	        }
	        catch (MailException ex) {
	            // simply log it and go on...
	            throw new AppException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
					500,
					"The mail server has expirienced a critical error, we were unable to email the user",
					ex.getMessage(),
					AppConstants.DASH_POST_URL);
	        }
		}
		
	}
	
	@Override
	@Transactional
	public Response validateToken(Long id, String token) throws AppException{
		User user=this.getUserById(id);
		String debugInfo="token failed debug out:";
		for(ValidationTokenEntity tokenEntity:user.getValidation_tokens()){
			debugInfo="<br>tokenmatchcheck=";
			if(tokenEntity.getToken().equals(token)){debugInfo+="true";}
			else{debugInfo+="false<br>"+tokenEntity.getToken()+"<br>"+token;}
			debugInfo+="  <br>expirationCheck=";
			if(tokenEntity.getExpiration_date().after(new Date())){debugInfo+="true";}
			else{debugInfo+="false";}
			
			if(tokenEntity.getToken().equals(token) && tokenEntity.getExpiration_date().after(new Date())){
				switch(tokenEntity.getToken_type()){
				case PASSWORD_RESET: 
				{
					try{
					return Response.seeOther(new URI("../../PasswordReset.jsp?user_id="
						+user.getId()+"&"+"token="+tokenEntity.getToken()+"&username="+user.getUsername())).build();
					}catch (URISyntaxException e){
						throw new AppException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
						500,
						"Oops, there was an error Redirecting to password reset form",
						e.getMessage(), AppConstants.DASH_POST_URL);
					}
				}
				case EMAIL_ACTIVATION:
				{
					/*TODO: activate email*/
					 return Response.status(200).
							entity("Thank you for activating your account!").build();
				}
						
				default: return Response.status(500).
						entity("Internal Server Error: Token Type Invalid").build();
				}
			}
		}
		
	
		return Response.status(500).
				entity(debugInfo).build();
		
	}
	
	@Override
	@Transactional
	public void resetPassword(User user) throws AppException{
		User verifyUserExistenceById = verifyUserExistenceById(user.getId());
		if (verifyUserExistenceById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ user.getId(), AppConstants.DASH_POST_URL);
		}else
		{
			authoritiesController.passwordReset(user);
		}
		
		
	}
	
	public void tokenPasswordReset(Long id, String token, String password) throws AppException{
		User user=this.getUserById(id);
		boolean tokenIsValid=false;
		for(ValidationTokenEntity tokenEntity:user.getValidation_tokens()){
			if(tokenEntity.getToken().equals(token) && tokenEntity.getExpiration_date().after(new Date())
					&& tokenEntity.getToken_type() == ValidationTokenEntity.TOKEN_TYPE.PASSWORD_RESET){
				user.setPassword(password);
				this.resetPassword(user);
			}
		}
	}

	/****************** Methods for Acl *****************/

	// Creates/Updates the ACL of user
	// Is also an example of how to implement class specific ACL helper methods.
	public void createUserACL(User user, Sid recipient) {
		MutableAcl acl;
		ObjectIdentity oid = new ObjectIdentityImpl(User.class,
				user.getId());

		try {
			acl = (MutableAcl) mutableAclService.readAclById(oid);
		} catch (NotFoundException nfe) {
			acl = mutableAclService.createAcl(oid);
		}
		acl.insertAce(acl.getEntries().size(), BasePermission.READ, recipient,
				true);
		acl.insertAce(acl.getEntries().size(), BasePermission.WRITE, recipient,
				true);
		acl.insertAce(acl.getEntries().size(), BasePermission.DELETE,
				recipient, true);
		mutableAclService.updateAcl(acl);
		acl.setOwner(new PrincipalSid("Root"));
		mutableAclService.updateAcl(acl);

		logger.debug("Added permission " + "Read, Write, Delete" + " for Sid "
				+ recipient
				+ " contact " + user);
	}

	@Override
	@Transactional
	public void setRoleUser(User user) {
		userDao.updateUserRole("ROLE_USER", user.getUsername());
	}

	@Override
	@Transactional
	public void setRoleModerator(User user) {
		userDao.updateUserRole("ROLE_MODERATOR", user.getUsername());
	}

	@Override
	@Transactional
	public void setRoleAdmin(User user) {
		userDao.updateUserRole("ROLE_ADMIN", user.getUsername());		
	}
	
	
	
}
