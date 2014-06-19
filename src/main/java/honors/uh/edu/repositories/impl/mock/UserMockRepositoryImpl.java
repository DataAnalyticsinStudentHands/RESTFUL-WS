package honors.uh.edu.repositories.impl.mock;

import honors.uh.edu.pojo.NullUser;
import honors.uh.edu.pojo.User;
import honors.uh.edu.repositories.contract.UserRepository;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.google.inject.Singleton;

@Singleton
public class UserMockRepositoryImpl extends GenericMockRepository<User> implements UserRepository {

	private List<User> users = new ArrayList<>();

	public UserMockRepositoryImpl() {
		users = createUsers();
	}

	@Override
	public User getById(final int id) {
		for (final User u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return new NullUser();
	}

	@Override
	public List<User> getAll() {
		return users;
	}

	@Override
	public User create(final User user) {
		user.setId(getCurrentMaxId() + 1);
		users.add(user);
		return user;
	}

	@Override
	public User update(final User user) {
		final User byId = getById(user.getId());
		byId.setFirstName(user.getFirstName());
		byId.setLastName(user.getLastName());
		return byId;
	}

	@Override
	public void remove(final int id) {
		final User byId = getById(id);
		users.remove(byId);
	}

	@Override
	public int getNumberOfUsers() {
		return users.size();
	}

	private List<User> createUsers() {
		final int numberOfUsers = 10;
		for (int i = 0; i < numberOfUsers; i++) {
			final User user = new User();
			user.setId(i + 1);
			user.setFirstName("Foo" + (i + 1));
			user.setLastName("Bar" + (i + 1));
			users.add(user);
		}
		return users;
	}

	private int getCurrentMaxId() {
		final Ordering<User> ordering = new Ordering<User>() {
			@Override
			public int compare(final User left, final User right) {
				return Ints.compare(left.getId(), right.getId());
			}
		};
		return ordering.max(users).getId();
	}
}
