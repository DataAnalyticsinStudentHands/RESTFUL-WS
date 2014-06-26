package honors.uh.edu.repositories.impl.DB;

import honors.uh.edu.repositories.contract.Repository;

import java.util.List;

public abstract class GenericDBRepository<T> implements Repository<T> {

    @Override
    public List<T> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public T getById(int id) {
        return (T) null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    
    @Override
    public List<T> getByName(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
