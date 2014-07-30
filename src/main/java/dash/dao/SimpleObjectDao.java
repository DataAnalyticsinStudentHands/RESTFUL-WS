package dash.dao;

import java.util.List;

import dash.pojo.SimpleObject;

/**
 *
 *
 * @see <a
 *      href="http://www.codingpedia.org/ama/spring-mybatis-integration-example/">http://www.codingpedia.org/ama/spring-mybatis-integration-example/</a>
 */
public interface SimpleObjectDao {

	public List<SimpleObjectEntity> getSimpleObjects(String orderByInsertionDate);

	/**
	 * Returns a simple object given its id
	 *
	 * @param id
	 * @return
	 */
	public SimpleObjectEntity getSimpleObjectById(Long id);
	
	/**
	 * Find object by name
	 *
	 * @param object
	 * @return the object with the name specified or null if not existent
	 */
	public SimpleObjectEntity getSimpleObjectByName(String name);

	public void deleteSimpleObject(SimpleObject simpleObject);

	public Long createSimpleObject(SimpleObjectEntity simpleObject);

	public void updateSimpleObject(SimpleObjectEntity simpleObject);

	/** removes all simple objects */
	public void deleteSimpleObjects();	

}
