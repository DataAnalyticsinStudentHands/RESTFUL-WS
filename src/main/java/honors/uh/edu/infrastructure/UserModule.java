package honors.uh.edu.infrastructure;

import com.google.inject.AbstractModule;


import honors.uh.edu.repositories.contract.DummyRepository;
import honors.uh.edu.repositories.contract.UserRepository;
import honors.uh.edu.repositories.impl.mock.DummyMockRepositoryImpl;
import honors.uh.edu.repositories.impl.DB.UserDBRepositoryImpl;
import honors.uh.edu.service.contract.DummyService;
import honors.uh.edu.service.contract.UserService;
import honors.uh.edu.service.impl.DummyServiceImpl;
import honors.uh.edu.service.impl.UserServiceImpl;


public class UserModule extends AbstractModule {
	@Override
    protected void configure() {
		//for testing
        bind(DummyRepository.class).to(DummyMockRepositoryImpl.class);
        bind(DummyService.class).to(DummyServiceImpl.class);
        
        //work with DB
        bind(UserRepository.class).to(UserDBRepositoryImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);              
    }
}