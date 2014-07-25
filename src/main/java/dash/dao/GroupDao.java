package dash.dao;

import java.util.List;

import dash.pojo.Group;


/*
 * @Author tswensen
 */
public interface GroupDao {
	public List<GroupEntity> getGroups(String orderByInsertionDate);

	public List<GroupEntity> getRecentGroups(int numberOfDaysToLookBack);

	public int getNumberOfGroups();

	/**
	 * Returns a group given its id
	 *
	 * @param id
	 * @return
	 */
	public GroupEntity getGroupById(Long id);

	/**
	 * Find group by name
	 *
	 * @param group
	 * @return the group with the name specified or null if not existent
	 */
	public GroupEntity getGroupByName(String name);


	public void deleteGroupById(Group group);

	public Long createGroup(GroupEntity group);

	public void updateGroup(GroupEntity group);

	/** removes all groups */
	public void deleteGroups();

}
