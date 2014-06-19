package honors.uh.edu.repositories.impl.mock;

import honors.uh.edu.pojo.User;
import honors.uh.edu.repositories.contract.DummyRepository;

public class DummyMockRepositoryImpl extends GenericMockRepository<User> implements DummyRepository {

	@Override
	public User getDefaultUser() {
		final User user = new User();
		user.setFirstName("JonFromREST");
		user.setLastName("DoeFromREST");
		return user;
	}
}
