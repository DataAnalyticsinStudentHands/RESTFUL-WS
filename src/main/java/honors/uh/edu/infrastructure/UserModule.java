package honors.uh.edu.infrastructure;

import honors.uh.edu.repositories.contract.DummyRepository;
import honors.uh.edu.repositories.contract.UserRepository;
import honors.uh.edu.repositories.impl.mock.DummyMockRepositoryImpl;
import honors.uh.edu.repositories.impl.mock.UserMockRepositoryImpl;
import honors.uh.edu.service.contract.DummyService;
import honors.uh.edu.service.contract.UserService;
import honors.uh.edu.service.impl.DummyServiceImpl;
import honors.uh.edu.service.impl.UserServiceImpl;

import com.google.inject.AbstractModule;

public class UserModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(DummyRepository.class).to(DummyMockRepositoryImpl.class);
		bind(DummyService.class).to(DummyServiceImpl.class);

		bind(UserRepository.class).to(UserMockRepositoryImpl.class);
		bind(UserService.class).to(UserServiceImpl.class);
	}
}