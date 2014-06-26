package honors.uh.edu.repositories.impl.DB;

import honors.uh.edu.pojo.User;
import honors.uh.edu.repositories.contract.DummyRepository;

public class DummyDBRepositoryImpl extends GenericDBRepository<User> implements DummyRepository {

    @Override
    public User getDefaultUser() {
        User user = new User();
        user.setFirstName("JonFromREST");
        user.setLastName("DoeFromREST");
        user.setCity("AustinFromRest");
        user.setHomePhone("phoneNumberFromRest");
        user.setCellPhone("713456789FromRest");
        user.setEmail("blub@FromRest.org");    
    	user.setPicture("PathtoPictureFromRest"); 
        return user;
    }
}
