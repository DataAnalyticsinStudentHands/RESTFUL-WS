package dash.service;


import java.util.List;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import dash.errorhandling.AppException;
import dash.pojo.User;

/**
 *
 * @author plindner, tyler.swensen@gmail.com
 * @see <a
 *      href="http://www.codingpedia.org/ama/spring-mybatis-integration-example/">http://www.codingpedia.org/ama/spring-mybatis-integration-example/</a>
 */
public interface UserService {

	/*
	 * ******************** Create related methods **********************
	 */
	public Long createUser(User user) throws AppException;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void createUsers(List<User> users) throws AppException;

	/*
	 * ******************* Read related methods ********************
	 */
	/**
	 *
	 * @param orderByInsertionDate
	 *            - if set, it represents the order by criteria (ASC or DESC)
	 *            for displaying users
	 * @param numberDaysToLookBack
	 *            - if set, it represents number of days to look back for users,
	 *            null
	 * @return list with users corresponding to search criteria
	 * @throws AppException
	 */
	
	public List<User> getUsers(String orderByInsertionDate,
			Integer numberDaysToLookBack) throws AppException;
	
	@PostFilter("hasPermission(filterObject, 'READ')")
	public List<User> getMyUser(String orderByInsertionDate,
			Integer numberDaysToLookBack) throws AppException;
	/**
	 * Returns a user given its id
	 *
	 * @param id
	 * @return
	 * @throws AppException
	 */
	
	public User getUserById(Long id) throws AppException;
	
	public List<String> getRole(User user);

	/*
	 * ******************** Update related methods **********************
	 */
	@PreAuthorize("hasPermission(#user, 'WRITE') or hasRole('ROLE_ADMIN')")
	public void updateFullyUser(User user) throws AppException;

	@PreAuthorize("hasPermission(#user, 'WRITE') or hasRole('ROLE_ADMIN')")
	public void updatePartiallyUser(User user) throws AppException;
	
	@PreAuthorize("hasPermission(#user, 'WRITE') or hasRole('ROLE_ADMIN')")
	public void resetPassword(User user) throws AppException;
	
	@PreAuthorize("hasPermission(#user, 'WRITE') and hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public void setRoleUser(User user);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void setRoleModerator(User user);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void setRoleAdmin(User user);
	
	/*
	 * ******************** Delete related methods **********************
	 */
	@PreAuthorize("hasPermission(#user, 'DELETE') or hasRole('ROLE_ADMIN')")
	public void deleteUser(User user);
	/** removes all users */
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public void deleteUsers();

	/*
	 * ******************** Helper methods **********************
	 */
	// TODO: This also should not exist, or it should be changed to
	// private/protected. Redundant
	// Could be made a boolean so it was not a security vulnerability
	public User verifyUserExistenceById(Long id);

	public int getNumberOfUsers();


	

	

}

