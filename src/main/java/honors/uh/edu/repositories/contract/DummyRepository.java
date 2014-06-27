package honors.uh.edu.repositories.contract;

import honors.uh.edu.pojo.User;

public interface DummyRepository extends Repository<User> {
	User getDefaultUser();
}
