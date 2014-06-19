package honors.uh.edu.service.impl;

import honors.uh.edu.pojo.User;
import honors.uh.edu.repositories.contract.DummyRepository;
import honors.uh.edu.service.contract.DummyService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DummyServiceImpl implements DummyService {

	private final DummyRepository dummyRepository;

	@Inject
	public DummyServiceImpl(final DummyRepository dummyRepository) {
		this.dummyRepository = dummyRepository;
	}

	@Override
	public User getDefaultUser() {
		final Object defaultUser = dummyRepository.getDefaultUser();
		return dummyRepository.getDefaultUser();
	}

}
