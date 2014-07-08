package honors.uh.edu;

import honors.uh.edu.errorhandling.AppExceptionMapper;
import honors.uh.edu.errorhandling.GenericExceptionMapper;
import honors.uh.edu.errorhandling.NotFoundExceptionMapper;
import honors.uh.edu.filters.LoggingResponseFilter;
import honors.uh.edu.pojo.UserDetailedView;
import honors.uh.edu.pojo.UsersResource;

import java.lang.annotation.Annotation;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Registers the components to be used by the JAX-RS application
 *
 * @author plindner
 *
 */
public class DashApplicationSetup extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public DashApplicationSetup() {
		// register application resources
		register(UsersResource.class);

		// register filters
		register(RequestContextFilter.class);
		register(LoggingResponseFilter.class);

		// register exception mappers
		register(GenericExceptionMapper.class);
		register(AppExceptionMapper.class);
		register(NotFoundExceptionMapper.class);

		// register features
		register(JacksonFeature.class);
		register(MultiPartFeature.class);
		register(EntityFilteringFeature.class);

		property(EntityFilteringFeature.ENTITY_FILTERING_SCOPE,
				new Annotation[] { UserDetailedView.Factory.get() });
	}
}

