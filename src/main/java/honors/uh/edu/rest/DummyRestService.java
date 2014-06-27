package honors.uh.edu.rest;

import honors.uh.edu.pojo.User;
import honors.uh.edu.service.contract.DummyService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

@Path("/dummy")
public class DummyRestService {
	private final DummyService dummyService;

	@Inject
	public DummyRestService(final DummyService DummyService) {
		dummyService = DummyService;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getDefaultDummyInJSON() {
		return dummyService.getDefaultUser();
	}
}