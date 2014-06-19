package honors.uh.edu.repositories.impl.mock;

import honors.uh.edu.repositories.contract.Repository;

import java.util.List;

public abstract class GenericMockRepository<T> implements Repository<T> {

	@Override
	public List<T> getAll() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public T getById(final int id) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
