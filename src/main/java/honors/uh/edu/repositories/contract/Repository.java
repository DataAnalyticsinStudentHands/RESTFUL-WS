package honors.uh.edu.repositories.contract;

import java.util.List;

public interface Repository<T> {

    List<T> getAll();

    T getById(int id);
    
    List<T> getByName(String name);
}
