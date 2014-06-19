package honors.uh.edu.service.impl;

import honors.uh.edu.pojo.User;
import honors.uh.edu.repositories.contract.UserRepository;
import honors.uh.edu.service.contract.UserService;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Inject
	public UserServiceImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List getAllUsers() {
		return userRepository.getAll();
	}

	@Override
	public User getById(final int id) {
		return userRepository.getById(id);
	}

	@Override
	public User createNewUser(final User user) {
		final User u = userRepository.create(user);
		return u;
	}

	@Override
	public User update(final User user) {
		return userRepository.update(user);
	}

	@Override
	public void remove(final int id) {
		userRepository.remove(id);
	}

	@Override
	public int getNumberOfUsers() {
		return userRepository.getNumberOfUsers();
	}
}