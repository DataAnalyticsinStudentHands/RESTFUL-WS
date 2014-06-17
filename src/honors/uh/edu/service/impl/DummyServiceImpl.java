package honors.uh.edu.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import honors.uh.edu.pojo.User;
import honors.uh.edu.repositories.contract.DummyRepository;
import honors.uh.edu.service.contract.DummyService;

@Singleton
public class DummyServiceImpl implements DummyService {

    private final DummyRepository dummyRepository;

    @Inject
    public DummyServiceImpl(DummyRepository dummyRepository) {
        this.dummyRepository = dummyRepository;
    }

    @Override
    public User getDefaultUser() {
        Object defaultUser = this.dummyRepository.getDefaultUser();
        return this.dummyRepository.getDefaultUser();
    }

}
