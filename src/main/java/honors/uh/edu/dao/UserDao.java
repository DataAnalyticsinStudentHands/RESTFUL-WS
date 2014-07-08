package honors.uh.edu.dao;

import java.util.List;

/**
 *
 *
 * @see <a
 *      href="http://www.codingpedia.org/ama/spring-mybatis-integration-example/">http://www.codingpedia.org/ama/spring-mybatis-integration-example/</a>
 */
public interface UserDao {

	public List<UserEntity> getUsers(String orderByInsertionDate);

	public List<UserEntity> getRecentUsers(int numberOfDaysToLookBack);

	public int getNumberOfUsers();

	/**
	 * Returns a user given its id
	 *
	 * @param id
	 * @return
	 */
	public UserEntity getUserById(Long id);

	/**
	 * Find user by name
	 *
	 * @param user
	 * @return the user with the name specified or null if not existent
	 */
	public UserEntity getUserByName(String name);

	public void deleteUserById(Long id);

	public Long createUser(UserEntity user);

	public void updateUser(UserEntity user);

	/** removes all users */
	public void deleteUsers();

}
