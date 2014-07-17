package honors.uh.edu.service;


import honors.uh.edu.errorhandling.AppException;
import honors.uh.edu.pojo.User;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 * @author plindner
 * @see <a
 *      href="http://www.codingpedia.org/ama/spring-mybatis-integration-example/">http://www.codingpedia.org/ama/spring-mybatis-integration-example/</a>
 */
public interface UserService {

	/*
	 * ******************** Create related methods **********************
	 */
	public Long createUser(User user) throws AppException;

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
	@PostFilter("hasPermission(filterObject, 'READ')")
	public List<User> getUsers(String orderByInsertionDate,
			Integer numberDaysToLookBack) throws AppException;

	/**
	 * Returns a user given its id
	 *
	 * @param id
	 * @return
	 * @throws AppException
	 */
	@PostAuthorize("hasPermission(returnObject, 'READ')")
	public User getUserById(Long id) throws AppException;

	/*
	 * ******************** Update related methods **********************
	 */
	@PostAuthorize("hasPermission(returnObject, 'WRITE')")
	public void updateFullyUser(User user) throws AppException;

	@PostAuthorize("hasPermission(returnObject, 'WRITE')")
	public void updatePartiallyUser(User user) throws AppException;

	/*
	 * ******************** Delete related methods **********************
	 */
	// Requires delete permissions on implementation level
	// TODO: Change params to (User user) and add a PreAuthorize annotation
	public void deleteUserById(Long id);

	/** removes all users */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteUsers();

	/*
	 * ******************** Helper methods **********************
	 */
	// TODO: This also should not exist, or it should be changed to
	// private/protected. Redundant
	// Could be made a boolean so it was not a security vulnerability
	public User verifyUserExistenceById(Long id);

	public int getNumberOfUsers();

}

