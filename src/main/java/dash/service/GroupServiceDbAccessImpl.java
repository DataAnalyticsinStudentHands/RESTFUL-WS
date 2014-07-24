package dash.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.transaction.annotation.Transactional;

import dash.dao.GroupDao;
import dash.dao.GroupEntity;
import dash.errorhandling.AppException;
import dash.filters.AppConstants;
import dash.helpers.NullAwareBeanUtilsBean;
import dash.pojo.Group;
import dash.pojo.User;
import dash.security.CustomPermission;
import dash.security.GenericAclController;


public class GroupServiceDbAccessImpl extends ApplicationObjectSupport implements
GroupService {

	@Autowired
	GroupDao groupDao;

	@Autowired
	private MutableAclService mutableAclService;

	
	private GenericAclController<Group> aclController;

	GroupServiceDbAccessImpl(){
		super();
		aclController= new  GenericAclController<Group>();
	}


	/********************* Create related methods implementation ***********************/
	@Override
	@Transactional
	public Long createGroup(Group group) throws AppException {

		validateInputForCreation(group);

		//verify existence of resource in the db (feed must be unique)
		GroupEntity groupByName = groupDao.getGroupByName(group.getName());
		if (groupByName != null) {
			throw new AppException(
					Response.Status.CONFLICT.getStatusCode(),
					409,
					"Group with groupname already existing in the database with the id "
							+ groupByName.getId(),
							"Please verify that the groupname and password are properly generated",
							AppConstants.DASH_POST_URL);
		}

		long groupId = groupDao.createGroup(new GroupEntity(group));
		group.setId(groupId);
		aclController.createACL(group);
		aclController.createAce(group, CustomPermission.MANAGER);
		return groupId;
	}

	private void validateInputForCreation(Group group) throws AppException {
		if (group.getName() == null) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "Provided data not sufficient for insertion",
					"Please verify that the groupname is properly generated/set",
					AppConstants.DASH_POST_URL);
		}
		
		//etc...
	}

	//Inactive
	@Override
	@Transactional
	public void createGroups(List<Group> groups) throws AppException {
		for (Group group : groups) {
			//createGroup(group);
		}
	}


	// ******************** Read related methods implementation **********************
	@Override
	public List<Group> getGroups(String orderByInsertionDate,
			Integer numberDaysToLookBack) throws AppException {

		//verify optional parameter numberDaysToLookBack first
		if(numberDaysToLookBack!=null){
			List<GroupEntity> recentGroups = groupDao
					.getRecentGroups(numberDaysToLookBack);
			return getGroupsFromEntities(recentGroups);
		}

		if(isOrderByInsertionDateParameterValid(orderByInsertionDate)){
			throw new AppException(
					Response.Status.BAD_REQUEST.getStatusCode(),
					400,
					"Please set either ASC or DESC for the orderByInsertionDate parameter",
					null, AppConstants.DASH_POST_URL);
		}
		List<GroupEntity> groups = groupDao.getGroups(orderByInsertionDate);

		return getGroupsFromEntities(groups);
	}

	private boolean isOrderByInsertionDateParameterValid(
			String orderByInsertionDate) {
		return orderByInsertionDate!=null
				&& !("ASC".equalsIgnoreCase(orderByInsertionDate) || "DESC".equalsIgnoreCase(orderByInsertionDate));
	}

	@Override
	public Group getGroupById(Long id) throws AppException {
		GroupEntity groupById = groupDao.getGroupById(id);
		if (groupById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The group you requested with id " + id
					+ " was not found in the database",
					"Verify the existence of the group with the id " + id
					+ " in the database", AppConstants.DASH_POST_URL);
		}

		return new Group(groupDao.getGroupById(id));
	}

	private List<Group> getGroupsFromEntities(List<GroupEntity> groupEntities) {
		List<Group> response = new ArrayList<Group>();
		for (GroupEntity groupEntity : groupEntities) {
			response.add(new Group(groupEntity));
		}

		return response;
	}

	public List<Group> getRecentGroups(int numberOfDaysToLookBack) {
		List<GroupEntity> recentGroups = groupDao
				.getRecentGroups(numberOfDaysToLookBack);

		return getGroupsFromEntities(recentGroups);
	}

	@Override
	public int getNumberOfGroups() {
		int totalNumber = groupDao.getNumberOfGroups();

		return totalNumber;

	}



	/********************* UPDATE-related methods implementation ***********************/
	@Override
	@Transactional
	public void updateFullyGroup(Group group) throws AppException {
		//do a validation to verify FULL update with PUT
		if (isFullUpdate(group)) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(),
					400,
					"Please specify all properties for Full UPDATE",
					"required properties - name, description",
					AppConstants.DASH_POST_URL);
		}

		Group verifyGroupExistenceById = verifyGroupExistenceById(group
				.getId());
		if (verifyGroupExistenceById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ group.getId(),
							AppConstants.DASH_POST_URL);
		}

		groupDao.updateGroup(new GroupEntity(group));
	}

	/**
	 * Verifies the "completeness" of group resource sent over the wire
	 *
	 * @param Group
	 * @return
	 */
	private boolean isFullUpdate(Group group) {
		return group.getId() == null
				|| group.getName() == null
				|| group.getDescription() == null;
	}

	/********************* DELETE-related methods implementation ***********************/

	@Override
	@Transactional
	public void deleteGroup(Group group) {

		groupDao.deleteGroupById(group);

	}

	@Override
	@Transactional
	// TODO: This shouldn't exist? If it must, then it needs to accept a list of
	// Groups to delete
	public void deleteGroups() {
		groupDao.deleteGroups();
	}

	@Override
	// TODO: This doesnt need to exist. It is the exact same thing as
	// getGroupById(Long)
	public Group verifyGroupExistenceById(Long id) {
		GroupEntity groupById = groupDao.getGroupById(id);
		if (groupById == null) {
			return null;
		} else {
			return new Group(groupById);
		}
	}

	@Override
	@Transactional
	public void updatePartiallyGroup(Group group) throws AppException {
		//do a validation to verify existence of the resource
		Group verifyGroupExistenceById = verifyGroupExistenceById(group.getId());
		if (verifyGroupExistenceById == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					404,
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - "
							+ group.getId(), AppConstants.DASH_POST_URL);
		}
		copyPartialProperties(verifyGroupExistenceById, group);
		groupDao.updateGroup(new GroupEntity(verifyGroupExistenceById));

	}

	private void copyPartialProperties(Group verifyGroupExistenceById, Group group) {

		BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifyGroupExistenceById, group);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ACL related methods
	 */
	// Adds an additional manager to the group
	public void addManager(User user, Group group){
		
		aclController.createAce(group, CustomPermission.MANAGER, new PrincipalSid(user.getUsername()));
			
		
	}
	
	//Removes all managers and sets new manager to user
	public void resetManager(User user, Group group){
		aclController.clearPermission(group, CustomPermission.MANAGER);
		aclController.createAce(group, CustomPermission.MANAGER, new PrincipalSid(user.getUsername()));
	}
	
	//Removes a single manager from a group
	public void deleteManager(User user, Group group){
		aclController.deleteACE(group, CustomPermission.MANAGER, new PrincipalSid(user.getUsername()));
	}
	
	//Adds a member to the group
	public void addMember(User user, Group group){
		aclController.createAce(group, CustomPermission.MEMBER, new PrincipalSid(user.getUsername()));
	}
	
	//Removes single member
	public void deleteMember(User user, Group group){
		aclController.deleteACE(group, CustomPermission.MEMBER, new PrincipalSid(user.getUsername()));
	}

	

}
